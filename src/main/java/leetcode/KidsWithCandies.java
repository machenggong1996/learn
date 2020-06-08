package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/06/01
 */
public class KidsWithCandies {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<Boolean>();
        int max = 0;
        for (int candy : candies) {
            max = Math.max(candy, max);
        }
        extraCandies = max - extraCandies;
        for (int candy : candies) {
            list.add(candy >= extraCandies);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(kidsWithCandies(new int[] { 2, 3, 5, 1, 3 }, 3));
    }

}
