package leetcode.string;

/**
 * @author machenggong
 * @since 2021/6/29
 */
public class ConvertToTitle {

    /**
     * 168. Excel表列名称
     *
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(10));
    }

}
