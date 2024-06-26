package netease.medium;

import java.util.*;

/**
 * 7-4 可以访问所有房间
 *
 * @author machenggong
 * @since 2024/2/28
 */
public class CanVisitAllRooms7_4 {

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        dfs(rooms, 0, visited);
        return visited.size() == rooms.size();
    }

    private static void dfs(List<List<Integer>> rooms, int currentRoom, Set<Integer> visited) {
        if (visited.contains(currentRoom)) {
            return;
        }
        visited.add(currentRoom);
        for (int key : rooms.get(currentRoom)) {
            dfs(rooms, key, visited);
        }
    }

    public static boolean room(List<List<Integer>> rooms){
        Set<Integer> v = new HashSet<>();
        dfs(0, v, rooms);
        return rooms.size() == v.size();
    }

    public static void dfs(int roomIndex, Set<Integer> v, List<List<Integer>> rooms){
        if(v.contains(roomIndex)){
            return;
        }
        v.add(roomIndex);
        for(int r : rooms.get(roomIndex)){
            if(!v.contains(r)){
                dfs(r, v, rooms);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputArr = input.substring(1, input.length() - 1).split(";");
        List<List<Integer>> rooms = new ArrayList<>();

        // 初始化rooms
        for (String keys : inputArr) {
            // 没这段代码 case全错
            if ("[]".equals(keys)) {
                rooms.add(new ArrayList<Integer>());
                continue;
            }
            String[] arr = keys.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            ArrayList<Integer> keyList = new ArrayList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                int key = Integer.parseInt(arr[i]);
                keyList.add(key);
            }
            rooms.add(keyList);
        }

        boolean b = room(rooms);
        System.out.println(b);
        List<List<Integer>> rooms1 = new ArrayList<>();
        String[] A1 =  input.replaceAll("\\[","").replaceAll("\\]","").split(";");
        for(String a1 :A1){
            List<Integer> ro = new ArrayList<>();
            String[] A2 = a1.split(",");
            for(String a2:A2){
                ro.add(Integer.parseInt(a2));
            }
            rooms1.add(ro);
        }
        System.out.print(room(rooms1));
    }

}
