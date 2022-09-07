package leetcode.string;

/**
 * @author machenggong
 * @since 2022/9/7
 */
public class ReorderSpaces {

    /**
     * 1592. 重新排列单词间的空格
     *
     * @param text
     * @return
     */
    public static String reorderSpaces(String text) {
        int length = text.length();
        String[] words = text.trim().split("\\s+");
        // 空格长度
        int cntSpace = length;
        for (String word : words) {
            cntSpace -= word.length();
        }
        StringBuilder sb = new StringBuilder();
        if (words.length == 1) {
            sb.append(words[0]);
            for (int i = 0; i < cntSpace; i++) {
                sb.append(' ');
            }
            return sb.toString();
        }
        int perSpace = cntSpace / (words.length - 1);
        int restSpace = cntSpace % (words.length - 1);
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                for (int j = 0; j < perSpace; j++) {
                    sb.append(' ');
                }
            }
            sb.append(words[i]);
        }
        for (int i = 0; i < restSpace; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorderSpaces(" practice   makes   perfect"));
    }

}
