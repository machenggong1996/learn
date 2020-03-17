package leetcode.string;

/**
 * Created by mache on 2020/3/17.
 */
public class CountCharacters {

    public static int countCharacters(String[] words, String chars) {
        int[] map = new int[26];
        for (char c : chars.toCharArray()) {
            map[c - 'a']++;
        }

        int ans = 0;
        for (String word : words) {
            int[] arr = new int[26];
            for (char cc : word.toCharArray()) {
                arr[cc - 'a']++;
            }
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                // 保证存量足够构成单词 字母不重复使用
                if (arr[word.charAt(i) - 'a'] > map[word.charAt(i) - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans += word.length();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
    }

}
