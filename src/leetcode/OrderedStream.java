package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1656. 设计有序流
 *
 * @author machenggong
 * @since 2022/8/16
 */
public class OrderedStream {

    //存放数据
    private String[] data;
    //指针
    private int ptr;

    public OrderedStream(int n) {
        data = new String[n];
        ptr = 0;
    }

    public List<String> insert(int idKey, String value) {
        data[idKey - 1] = value;
        List<String> ans = new ArrayList<>();
        //找到连续的数据
        while (ptr < data.length && data[ptr] != null) {
            ans.add(data[ptr]);
            ptr++;
        }
        return ans;
    }

    public static void main(String[] args) {
        OrderedStream orderedStream = new OrderedStream(5);
        List<String> list1 = orderedStream.insert(1,"aaaaaa");
        List<String> list2 = orderedStream.insert(2,"bbbbbb");
        List<String> list3 = orderedStream.insert(3,"cccccc");
    }

}
