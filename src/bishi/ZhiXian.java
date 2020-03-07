package bishi;

/**
 * Created by machenggong on 2020/3/5.
 */
public class ZhiXian {

    public static void main(String[] args) {

        ZhiXian zhiXian = new ZhiXian();

        zhiXian.code();

    }

    static Map[] mapsChar = new Map[26];

    static Map[] mapsInt = new Map[10];

    public static int getMapValue(char key, Map[] maps) {
        for (int i = 0; i < maps.length; i++) {
            Map map = maps[i];
            if (map.key == key) {
                return map.value;
            }
        }
        return 0;
    }

    public void code() {
        char[] s = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        char[] n = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


        for (int i = 0, j = 1; i < s.length; i++, j++) {
            if (j == 10) {
                j = 1;
            }
            Map map = new Map(s[i], j);
            mapsChar[i] = map;
        }

        for (int i = 0; i < n.length; i++) {
            Map map = new Map(n[i], i);
            mapsInt[i] = map;
        }


        String codeStr = "abc1234567def890";

        //最后一个字符开始 奇数位和
        int t1 = 0;

        for (int i = codeStr.length() - 1; i > 0; i--) {

            // 寻找最后一个字符
            char ch = codeStr.charAt(i);

            int x = getMapValue(ch, mapsChar);
            int y = getMapValue(ch, mapsInt);

            if (x == 0) {
                continue;
            } else {
                t1 = t1 + x + y;
                //找到最后一个字符
                for (int j = i; j > 0; ) {
                    j = j - 2;
                    ch = codeStr.charAt(j);
                    x = getMapValue(ch, mapsChar);
                    y = getMapValue(ch, mapsInt);
                    t1 = t1 + x + y;
                }
                break;
            }
        }


        //t2 最后一位数字开始偶数位求和
        int t2 = 0;
        for (int i = codeStr.length() - 1; i > 0; i--) {

            char ch = codeStr.charAt(i);

            int x = getMapValue(ch, mapsChar);
            int y;

            if (x != 0) {
                continue;
            } else {
                //找到最后一个数字
                for (int j = i; j > 0; ) {
                    j--;
                    ch = codeStr.charAt(j);
                    x = getMapValue(ch, mapsChar);
                    y = getMapValue(ch, mapsInt);
                    x = 2 * x;
                    if (x > 9) {
                        x = x - 9;
                    }
                    y = 2 * y;
                    if (y > 9) {
                        y = y - 9;
                    }
                    t2 = t2 + x + y;
                    j--;
                }
                break;
            }
        }

        int total = t1 + t2;

        System.out.println(total);

        if ((total % 10) > 0) {
            System.out.println("error");
        } else {
            System.out.println("ok");
        }


    }


    /**
     * 项目组问题
     */

    public void program() {
        int[] p = new int[]{10, 7, 5, 4};

        for (int i = 0; i < 120; i++) {
            int[] arrnew = getMaxValueAndIndex(p);
            p[arrnew[1]] = arrnew[0] - p.length + 1;
            for (int j = 0; j < p.length; j++) {
                if (j != arrnew[1]) {
                    p[j] = p[j] + 1;
                }
            }

        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
    }

    public static int[] getMaxValueAndIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int maxIndex = 0;
        int[] arrNew = new int[2];
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[maxIndex] < arr[i + 1]) {
                maxIndex = i + 1;
            }
        }
        arrNew[0] = arr[maxIndex];
        arrNew[1] = maxIndex;
        return arrNew;
    }

    class Map {

        char key;

        int value;

        public Map(char key, int value) {
            this.key = key;
            this.value = value;
        }


    }

    public int coin(int n, int m) {
        int[] coin = new int[]{1, 2, 5, 10};

        int[] zh = new int[n];



        return -1;
    }

}
