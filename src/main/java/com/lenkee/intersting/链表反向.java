package com.lenkee.intersting;

/**
 * Created by amettursun on 2019/8/1.
 */
public class 链表反向 {
    /**
     * 这是我刚毕业时遇到的面试题，如何把链表反向，
     * 当时口头回答都挺好，可是编码能力不行，不能用代码实现
     */
    public static final int scope = 20;
    public static void main(String[] args) {
        // 创建一个单链表
        SingleList startNode = new SingleList();
        SingleList node = startNode;
        for (int i = 0; i < scope; i++) {
            SingleList temp = new SingleList();
            node.data=(i+"");
            node.nextNode = temp;
            if (i<scope-1)
                node = temp;
        }
        node.nextNode = null;
        // 打印链表
        node = startNode;
        pirntList(node);

        System.out.println("-------");
        // 反转
        SingleList reverse = reverse(startNode);
        pirntList(reverse);

    }

    private static void pirntList(SingleList node) {
        while (node.hasNext()){
            System.out.print(node.data+"    ");
            node = node.nextNode;
        }
        System.out.println();
    }

    public static SingleList reverse(SingleList startNode){
        SingleList node = startNode; // 当前node
        SingleList preNode = null;  // 记录上一个node
        SingleList nexNode = node;
        while (node!=null){
            nexNode = node.nextNode;
            node.nextNode = preNode;
            preNode = node;
            if (nexNode == null)
                break;
            node = nexNode;
        }

        return node;
    }

}

class SingleList{
    SingleList nextNode;
    String data;

    public boolean hasNext(){
        return nextNode == null?false:true;
    }

}
