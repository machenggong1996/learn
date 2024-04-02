package netease.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * 7-11 判断二分图
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Erfentu7_11 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            line = line.substring(1, line.length()-1); // 去掉头尾中括号
            // System.out.println(line);

            String[] parts = line.split(";");
            int[][] graph = new int[parts.length][];
            for(int n=0; n<parts.length; n++){
                String part = parts[n];
                part = part.substring(1, part.length()-1); // 去掉头尾中括号

                String[] edges = part.split(",");
                graph[n] = new int[edges.length];
                for(int e = 0; e<edges.length; e++) {
                    graph[n][e] = Integer.parseInt(edges[e]);
                }
                // System.out.println(n+": "+Arrays.toString(graph[n]));
            }

            boolean result = solve(graph);
            System.out.println(result);
        } catch(Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    static boolean solve(int[][] graph) {
        Set<Integer> visited = new HashSet<>();
        for(int n=0; n<graph.length; n++) {
            if(visited.contains(n)) {
                continue;
            }

            /* 以节点 n 为种子，遍历所属连通子图*/
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();

            LinkedList<Integer> list = new LinkedList<>(); // 连通子图中发现的节点
            set1.add(0);
            list.add(n);
            while(!list.isEmpty()) {
                int from = list.remove();
                visited.add(from);

                for(int to : graph[from]) {
                    if(to < from) { // 只访问 from < to，无需反向访问（因为是无向图）
                        continue;
                    }

                    // System.out.println("f: "+from+", t: "+to);

                    // from 属于 set1，则 to 只能属于 set2
                    if(set1.contains(from)) {
                        if(set1.contains(to)){
                            return false;
                        }
                        set2.add(to);
                    }

                    // from 属于 set2，同理
                    if(set2.contains(from)) {
                        if(set2.contains(to)){
                            return false;
                        }
                        set1.add(to);
                    }

                    // System.out.println("set1: "+set1);
                    // System.out.println("set2: "+set2);
                    list.add(to);
                }
            }
        }
        return true;
    }

}
