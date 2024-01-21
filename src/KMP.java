import java.util.Scanner;

/**
 * @author machenggong
 * @since 2021/5/29
 */
public class KMP {

    public static int[] getValNext(char[] t) {
        int[] nextVal = new int[t.length];
        nextVal[0] = -1;
        int j = 0;
        int k = -1;
        while (j < t.length - 1) {
            if (k == -1 || t[j] == t[k]) {
                ++j;
                ++k;
                if (t[j] == t[k]) {
                    nextVal[j] = nextVal[k];
                } else {
                    nextVal[j] = k;
                }

            } else {
                k = nextVal[k];
            }
        }
        return nextVal;
    }

    public static int kmp_Index(char[] s, char[] t) {
        int[] next = getValNext(t);
        for (int i = 0; i < next.length; i++) {
            System.out.println(next[i]);
        }
        int i = 0;
        int j = 0;
        while (i <= s.length - 1 && j <= t.length - 1) {
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j < t.length) {
            return -1;
        } else {
            return i - t.length;
        }
    }

    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        while (scan.hasNextLine()) {
//            String s = scan.nextLine();
//            String t = scan.nextLine();
//            char[] ss = s.toCharArray();
//            char[] tt = t.toCharArray();
//            if (kmp_Index(tt, ss) == -1) {
//                System.out.println("NO");
//            } else {
//                System.out.println("YES");
//            }
//        }
        System.out.println(~7);

    }


}
