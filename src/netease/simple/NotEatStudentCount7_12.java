package netease.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 7-12 无法吃午餐学生数量
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class NotEatStudentCount7_12 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<String> students = new LinkedList<>(Arrays.asList(br.readLine().split(" ")));
        Queue<String> sandwichs = new LinkedList<>(Arrays.asList(br.readLine().split(" ")));

        while (!sandwichs.isEmpty() && !students.isEmpty()) {
            String sandwich = sandwichs.peek();
            if (eat(students, sandwich)) {
                // 被吃了
                sandwichs.poll();
                continue;
            } else {
                break;
            }
        }
        System.out.println(students.size());
    }

    // 给一个三明治到学生队列，看有没有人吃
    public static boolean eat(Queue<String> students, String sandwich) {
        for (int i = 0; i < students.size(); i++) {
            String student = students.poll();
            if (student.equals(sandwich)) {
                return true;
            }
            students.add(student);
        }
        return false;
    }

}
