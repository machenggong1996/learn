package netease.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 7-17 交换和
 *
 * @author machenggong
 * @since 2024/2/27
 */
public class SwapArrayValues7_17 {

// 本题有：aCount - a + b = bCount - b + a 所以，只需要对每个a找对应的b就行

    public static void main1(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String[] aStrs = scanner.nextLine().split(" ");
        int[] a = new int[aStrs.length];
        int aCount = 0;
        for(int i=0; i<aStrs.length; i++) {
            a[i] = Integer.parseInt(aStrs[i]);
            aCount += a[i];
        }

        String[] bStrs = scanner.nextLine().split(" ");
        int bCount = 0;
        int[] b = new int[bStrs.length];
        Set<Integer> bSet = new HashSet<>(); // a确定了，b顺序是无所谓，所以可以用set
        for(int i=0; i<bStrs.length; i++) {
            b[i] = Integer.parseInt(bStrs[i]);
            bCount += b[i];
            bSet.add(b[i]);
        }

        int diff = aCount-bCount;
        // // aCount - bCount = (a + b) * 2  所以一定是偶数
        if((diff % 2) != 0) {
            return;
        }
        diff = diff / 2;
        Set<Integer> filterA = new HashSet<>(); // a不能重复
        for(int i=0; i<a.length; i++) {
            if(filterA.contains(a[i])) continue;
            filterA.add(a[i]);
            int target = a[i] - diff;
            if(bSet.contains(target)) {
                System.out.println(a[i] + " " + target);
            }
        }

    }

    /**
     * 不会全对了
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str1 = in.nextLine().split(" ");
        int[] arr1 = new int[str1.length];
        int ta = 0;
        for(int i = 0; i< str1.length; i++){
            arr1[i] = Integer.parseInt(str1[i]);
            ta = ta + arr1[i];
        }
        int tb = 0;
        String[] str2 = in.nextLine().split(" ");
        int[] arr2 = new int[str2.length];
        for(int i = 0; i< str2.length; i++){
            arr2[i] = Integer.parseInt(str2[i]);
            tb = tb + arr2[i];
        }
        int diff = ta - tb;
        if((diff%2) != 0){
            return;
        }
        diff = diff/2;
        Arrays.sort(arr1);
        Set<Integer> filterA = new HashSet<>();
        for(int i = 0; i< arr1.length; i++){
            for(int j = 0; j< arr2.length; j++){
                if(arr1[i] - arr2[j] == diff){
                    if(filterA.contains(arr1[i])){
                        continue;
                    }
                    // 去重
                    filterA.add(arr1[i]);
                    System.out.println(arr1[i] + " " + arr2[j]);
                }
            }
        }
    }
}

