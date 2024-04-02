package netease.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

/**
 * 7-6 最小数
 *
 * @author machenggong
 * @since 2024/2/28
 */
public class MinNumber7_6 {

    public static String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        Integer[] numArr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numArr[i] = nums[i];
        }
        Arrays.sort(numArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String order1 = a + "" + b;
                String order2 = b + "" + a;
                return order1.compareTo(order2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer num : numArr) {
            sb.append(num);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        // 寻找第一个负数
        Optional<String> first = Arrays.stream(arr).filter(s -> s.charAt(0) == '-').findFirst();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).sorted((o1, o2) -> {
            if (o1.charAt(0) == '-') {
                return -1;
            }
            if (o2.charAt(0) == '-') {
                return 1;
            } else {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return first.isPresent() ? s2.compareTo(s1) : s1.compareTo(s2);
            }
        }).forEach(sb::append);
        System.out.println(sb);
    }

}
