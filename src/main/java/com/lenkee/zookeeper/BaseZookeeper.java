package com.lenkee.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by amettursun on 2019/7/23.
 */
public class BaseZookeeper implements Watcher {

    private ZooKeeper zooKeeper;
    private static final int SESSION_TIME_OUT = 2000;
    /**
     * 这里使用CountDownLatch来阻止使用的zooKeeper对象。
     * 当客户端与zooKeeper建立连接之后，Watcher的process()方法会被调用，参数表示一个连接的事件。
     * 在接收到一个连接事件（以Watcher.Event.KeeperState的枚举类型值SyncConnected来表示）时，
     * 我们通过调用CountDownLatch的countDown()方法来递减它的计数器。
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        BaseZookeeper zk = new BaseZookeeper();
        // 连接采用默认端口2181
        zk.connectzooKeeper("127.0.0.1");
        System.out.println(Arrays.toString(zk.getChildren("/").toArray()));

        // 创建完成后持久化
//        String testData = zk.createNode("/test", "testData");
        String data = zk.getData("/test");
        System.out.println(data);

        zk.setData("/test","hello test:"+Math.random());
        System.out.println(zk.getData("/test"));


    }

    @Override
    public void process(WatchedEvent event) {
        if ((event.getState() == Event.KeeperState.SyncConnected)) {
            System.out.println("watch received event");
            countDownLatch.countDown();
        }
    }

    // 连接zooKeeper
    public void connectzooKeeper(String host) throws Exception {
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zooKeeper connection success");
    }

    // 创建节点
    public String createNode(String path, String data) throws KeeperException, InterruptedException {
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    // 获取路径下所有子节点
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, false);
        return children;
    }

    // 获取节点上面的数据
    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    /**
     * 设置节点信息
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    /**
     * 删除节点
     */
    public void deleteNode(String path) throws InterruptedException, KeeperException {
        zooKeeper.delete(path, -1);
    }

    /**
     * 获取创建时间
     */
    public String getCTime(String path) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        return String.valueOf(stat.getCtime());
    }

    /**
     * 获取某个路径下孩子的数量
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException {
        int childenNum = zooKeeper.getChildren(path, false).size();
        return childenNum;
    }

    /**
     * 关闭连接
     */
    public void closeConnection() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }

    }
}
