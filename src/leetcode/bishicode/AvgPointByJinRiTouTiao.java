package leetcode.bishicode;

import java.util.ArrayList;

/**
 * Created by machenggong on 2020/3/19.
 */
public class AvgPointByJinRiTouTiao {

    public static void main(String[] args) {
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(1, 1));
        list.add(new Point(1, 3));
        list.add(new Point(5, 3));
        list.add(new Point(5, 5));
        list.add(new Point(10, 5));
        list.add(new Point(10, 1));

        /*打印多少份*/
        int num = 13;
        /*总长度*/
        double sum = 0;

        /*计算从第0个点到当前点的总长度*/
        for (int i = 1; i < list.size(); i++) {
            /*当前点*/
            Point cur = list.get(i);
            /*上一个点*/
            Point last = list.get(i - 1);

            double length = distance(cur, last);
            /*计算长度*/
            cur.setSumLength(sum += length);
        }

        /*设置第0个点的总长度==周长*/
        list.get(0).setSumLength(sum += distance(list.get(0), list.get(list.size() - 1)));
        /*为计算方便，把第0个点加到list最后，这样list中有两个0号元素*/
        list.add(list.get(0));

        /*计算平均长度*/
        double avg = sum / num;
        System.out.println("总长度：" + sum);
        System.out.println("平均长度" + sum / num);

        /*平均长度的整倍数，即为等分点*/
        double[] avgArray = new double[num];
        for (int i = 0; i < num; i++) {
            avgArray[i] = (i + 1) * avg;
        }

        /*下一次开始比对的point的索引，这样避免不必要的比对*/
        int pointIndex = 1;
        for (int j = 0; j < num; j++) {
            double target = avgArray[j];
            for (int i = pointIndex; i < list.size(); i++) {
                Point cur = list.get(i);
                if (cur.getSumLength() > target) {
                    double diff = cur.getSumLength() - target;
                    Point pre = list.get(i - 1);
                    /*水平线,当前点与上一个点Y坐标的大小关系不确定*/
                    if (pre.getX() == cur.getX()) {
                        if (pre.getY() < cur.getY()) {
                            System.out.println("(" + cur.getX() + ",    " + (cur.getY() - diff) + ")");
                        } else {
                            System.out.println("(" + cur.getX() + ",    " + (cur.getY() + diff) + ")");
                        }
                    } else {/*垂直线，当前点与上一个点X坐标的大小关系不确定*/
                        if (pre.getX() < cur.getX()) {
                            System.out.println("(" + (cur.getX() - diff) + ",     " + cur.getY() + ")");
                        } else {
                            System.out.println("(" + (cur.getX() + diff) + ",     " + cur.getY() + ")");
                        }
                    }

                    /*下一回从当前的点开始遍历扫描，
                     *之所以不从下一个点开始，是因为有可能当前点和下一点中间存在多个要打印的点*/
                    pointIndex = i;
                    break;
                }
            }
        }
    }

    /**
     * 两个点的距离
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(Point p1, Point p2) {
        return Math.pow(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2), 0.5);
    }

    static class Point {
        int x;
        int y;

        /*从第0个点，到当前点的总长度
        * 第1个点总长度最小，
        * 第0个点总长度最大==周长*/
        double sumLength;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public double getSumLength() {
            return sumLength;
        }

        public void setSumLength(double sumLength) {
            this.sumLength = sumLength;
        }
    }

}
