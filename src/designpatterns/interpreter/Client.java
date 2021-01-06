package designpatterns.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author machenggong (chenggong@shanshu.ai)
 * @date 2021/1/5
 * @description
 */
public class Client {

    public static void main(String[] args) {
        Calculator calculator = new Calculator("a+b");
        HashMap<String, Integer> map = new HashMap<>(16);
        map.put("a", 10);
        map.put("b", 20);
        System.out.println(calculator.run(map));
    }

    public static String getExpStr() throws IOException {
        System.out.println("请输入表达式：");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static HashMap<String, Integer> getValue(String expStr) throws IOException {
        HashMap<String, Integer> map = new HashMap<>(16);
        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {

            }
        }
        return map;
    }

}
