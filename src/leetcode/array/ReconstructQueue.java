package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/11/16
 */
public class ReconstructQueue {

    /**
     * 根据身高重建队列 从高到低考虑
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，
     * 其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     * @param people
     * @return
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {

            @Override
            public int compare(int[] person1, int[] person2) {
                //1. 先按照身高排序 身高从高到低 再按照人数排序 人数从低到高
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            //插在对应位置 和上面排序有关联
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }


    public static void main(String[] args) {
        //[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

}
