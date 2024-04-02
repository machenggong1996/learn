package netease.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void main(String[] args) throws IOException{
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
}

