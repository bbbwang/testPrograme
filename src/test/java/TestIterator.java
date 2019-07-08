import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by amettursun on 2019/5/16.
 */

public class TestIterator {
    @Test
    public void test(){
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("f");
        list.add("e");
        Iterator it = list.iterator();
        while(it.hasNext()){
            String str = (String) it.next();
            System.out.println(str);
        }
    }
}
