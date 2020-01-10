package dp;

/**
 * Created by machenggong on 2020/1/6.
 */
public class ReverseStringSequence {

    public static String reverseStringSequence(String str) {
        if (str == null) {
            return str;
        }
        char[] seq = str.toCharArray();
        int length = seq.length;
        // 定义两个指针记录要反转单词的起始位置
        int start = 0;
        int end = 0;
        // 这里一定要含有等于，因为要判断是否是最后一个单词，从而可以处理最后一个单词
        while (end <= length) {
            // 当已经遍历到字符串的最后一个字符，或者当前字符是空格时
            // 则对空格前的单词进行反转，即"am"反转为"ma"
            // 一定要把判断是否是结尾放在前面，否则seq[end]会报错，因为数组的有效索引是从0开始的
            // 反转后修改单词的起始指针为空格的下一个字符
            // 如果不符合条件，则移动指针继续判断下一个字符
            if (end == length || seq[end] == ' ') {
                reverse(seq, start, end - 1);
                start = end + 1;
            }
            end++;
        }
        // 反转这个数组
        reverse(seq, 0, length - 1);
        return new String(seq);
    }

    private static void reverse(char[] seq, int start, int end) {
        while (start < end) {
            char temp = seq[start];
            seq[start] = seq[end];
            seq[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String result = reverseStringSequence("I am a student.");
        System.out.println(result);
    }

}
