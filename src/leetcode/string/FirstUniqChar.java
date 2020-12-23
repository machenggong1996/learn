package leetcode.string;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class FirstUniqChar {

    /**
     * 字符串中的第一个唯一字符
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        int[] a = new int[26];
        for (int i = 0; i < c.length; i++) {
            a[c[i] - 'a'] = a[c[i] - 'a'] + 1;
        }
        for(int i = 0; i < c.length; i++){
            if(a[c[i]-'a']==1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }

}
