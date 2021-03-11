package bishi.wangyi;

/**
 * @author machenggong
 * @date 2021/3/11
 * @description
 */
public class Test {

    /**
     * 郑秀行对所有人说： (11:43 上午)
     *  把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     */

    public static int minValue(int[] arr){
        int l = 0;
        int h = arr.length-1;
        int res = 0;
        while (l<h){

            int mid = (h-l)/2+l;
            if(arr[l]<arr[mid]){
                l = mid+1;
            }else if(arr[l]>arr[mid]){
                h= mid;
            }else {
                h--;
            }
        }

        return arr[l];
    }

    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3};
        System.out.println(minValue(arr));
    }

}
