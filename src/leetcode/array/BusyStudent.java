package leetcode.array;

/**
 * @author machenggong
 * @since 2022/8/19
 */
public class BusyStudent {

    /**
     * 1450. 在既定时间做作业的学生人数
     *
     * @param startTime
     * @param endTime
     * @param queryTime
     * @return
     */
    public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
