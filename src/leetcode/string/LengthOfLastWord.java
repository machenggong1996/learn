package leetcode.string;

/**
 * @author machenggong
 * @date 2021/3/16
 * @description
 */
public class LengthOfLastWord {

    /**
     * 58. 最后一个单词的长度
     *
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World "));
    }


}
