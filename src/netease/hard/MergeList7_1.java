package netease.hard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/3/13
 */
public class MergeList7_1 {

    static class Node {
        int address;
        int data;
        int next;
        Node(int address, int data, int next) {
            this.address = address;
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        int l1_initAddress = Integer.parseInt(input[0]);
        int l2_initAddress = Integer.parseInt(input[1]);
        int N = Integer.parseInt(input[2]);

        // 维护4个变量
        ArrayList<Node> list1 = new ArrayList<>(); // L1链表
        ArrayList<Node> list2 = new ArrayList<>(); // L2链表
        ArrayList<Node> ans = new ArrayList<>(); // 存储最终答案的链表
        Node[] nums = new Node[100000];

        // 初始化nums数组
        for(int i = 0; i < N; i++) {
            String[] line = in.nextLine().split(" ");
            int address = Integer.parseInt(line[0]);
            int data = Integer.parseInt(line[1]);
            int next = Integer.parseInt(line[2]);
            // 这里浪费了很多存储空间，待优化
            nums[address] = new Node(address, data, next);
        }
        // 初始化L1
        while(l1_initAddress != -1) {
            list1.add(nums[l1_initAddress]);
            l1_initAddress = nums[l1_initAddress].next;
        }
        // 初始化L2
        while(l2_initAddress != -1) {
            list2.add(nums[l2_initAddress]);
            l2_initAddress = nums[l2_initAddress].next;
        }
        if(list1.size() >= 2*list2.size()) {
            int index1 = 0;
            int index2 = 0;
            for(int i = 0; i < list1.size(); i++) {
                ans.add(list1.get(i));
                index1++;
                if(index1 % 2 == 0 && index2 < list2.size()) {
                    ans.get(ans.size() - 1).next = list2.get(list2.size() - 1 - index2).address;
                    ans.add(list2.get(list2.size() - 1 - index2));
                    if(i + 1 < list1.size()) {
                        ans.get(ans.size() - 1).next = list1.get(i + 1).address;
                    }
                    index2++;
                }
            }
        } else {
            int index1 = 0;
            int index2 = 0;
            for(int i = 0; i < list2.size(); i++) {
                ans.add(list2.get(i));
                index1++;
                if(index1 % 2 == 0 && index2 < list1.size()) {
                    ans.get(ans.size() - 1).next = list1.get(list1.size() - 1 - index2).address;
                    ans.add(list1.get(list1.size() - 1 - index2));
                    if(i + 1 < list2.size()) {
                        ans.get(ans.size() - 1).next = list2.get(i + 1).address;
                    }
                    index2++;
                }
            }
        }
        // 输出结果
        for(int i = 0; i < ans.size() - 1; i++) {
            System.out.printf("%05d %d %05d\n", ans.get(i).address, ans.get(i).data, ans.get(i).next);
        }
        System.out.printf("%05d %d -1", ans.get(ans.size() - 1).address, ans.get(ans.size() - 1).data);
    }

}
