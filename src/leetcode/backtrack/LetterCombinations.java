package leetcode.backtrack;

import java.util.*;

/**
 * @author machenggong
 * @date 2020/08/26
 */
public class LetterCombinations {

    /**
     * 电话号码的字母组合
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>(8) {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index,
                                 StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public static List<String> letterCombinations1(String digits) {
        List<String> result = new ArrayList<>();
        int n = digits.length();
        if (n == 0){
            return result;
        }
        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a","b","c"));
        map.put('3', Arrays.asList("d","e","f"));
        map.put('4', Arrays.asList("g","h","i"));
        map.put('5', Arrays.asList("j","k","l"));
        map.put('6', Arrays.asList("m","n","o"));
        map.put('7', Arrays.asList("p","q","r","s"));
        map.put('8', Arrays.asList("t","u","v"));
        map.put('9', Arrays.asList("w","x","y","z"));
        result = map.get(digits.charAt(0));
        if (n == 1){
            return result;
        }
        int i = 1;
        while (i < n){
            List<String> temp = new ArrayList<>();
            for (String s1 : result) {
                for (String s2 : map.get(digits.charAt(i))) {
                    temp.add(s1+s2);
                }
            }
            result = temp;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }

}
