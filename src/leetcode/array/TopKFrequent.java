package leetcode.array;

import java.util.*;

/**
 * Created by machenggong on 2020/3/22.
 */
public class TopKFrequent {

    public static List<Integer> topKFrequent(int[] nums, int k) {

        // 利用哈希表统计每个数出现的频率，key是nums中的数，value是该数出现的频率
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 优先队列中放的是nums中的num，通过传入匿名Comparator类来重写比较方法
        // 比较方法中用map.get(num)得到频率去比较
        // Java的优先队列默认使用的是小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        // 利用频率比较，将key（即nums中的元素）放入小顶堆中，每次碰到频率更高的就替换
        for (int key : map.keySet()) {
            // 优先队列没装满k个就继续装
            if (pq.size() < k) {
                pq.add(key);
            }
            // 装满之后就开始比较大小进行替换
            else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 遍历完成后优先队列中放的就是频率最高的k个数，拿出返回List即可
        List<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{1, 2, 2, 3, 3, 3}, 2));
    }

}
