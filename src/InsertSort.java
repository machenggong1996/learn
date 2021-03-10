/**
 * Created by machenggong on 2020/2/11.
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] ins = {2, 3, 5, 1, 23, 6, 78, 34};
        int[] ins2 = sort1(ins);
        for (int in : ins2) {
            System.out.println(in);
        }
    }

    public static int[] sort1(int[] ins){
        for(int i=1; i<ins.length; i++){
            for(int j=i; j>0; j--){
                if(ins[j]<ins[j-1]){
                    int temp = ins[j-1];
                    ins[j-1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        return ins;
    }

    public static int[] sort(int[] ins) {

        for (int i = 1; i < ins.length; i++) {
            int temp = ins[i];//保存每次需要插入的那个数
            int j;
            for (j = i; j > 0 && ins[j - 1] > temp; j--) {//这个较上面有一定的优化
                ins[j] = ins[j - 1];//吧大于需要插入的数往后移动。最后不大于temp的数就空出来j
            }
            ins[j] = temp;//将需要插入的数放入这个位置
        }
        return ins;
    }

}
