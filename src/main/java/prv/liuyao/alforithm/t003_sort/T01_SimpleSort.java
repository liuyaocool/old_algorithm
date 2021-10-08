package prv.liuyao.alforithm.t003_sort;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;

import static prv.liuyao.alforithm.AlgorithmUtil.*;

public class T01_SimpleSort {


    /**
     * 插入排序：有点像摸牌，前边已经排好了，新牌从后往前排插入
     *  0~1 ：1位置与前边比较，前一个比自己大 往前换，直到前边<=自己 停止
     *  0~2 ：2位置与前边比较，。。。
     *  0~3 ：3位置与前边比较，。。。
     *  ...
     *
     * @param arr
     * @return
     */
    static int[] insertSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            //
            for (int j = i; j > 0 && arr[j-1] > arr[j]; j--) {
                swapArray(arr, j-1, j);
            }
        }
        return arr;
    }

    static void testSort(String sortName, int loopSize, Consumer<int[]> sortMethod) {
        for (int i = 0; i < loopSize; i++) {
            int[] original = randomArray(100);
            int[] mySort = copyArray(original);
            int[] sort = copyArray(original);

            sortMethod.accept(mySort);
            Arrays.sort(sort);

            PrintStream print = checkArray(sort, mySort) ? System.out : System.err;

            print.println("原始数组：" + arrayToString(original));
            print.println("正确排序：" + arrayToString(sort));
            print.println(sortName + "：" + arrayToString(mySort));
            print.println("--------------------");
        }
    }

    public static void main(String[] args) {
        testSort("插入排序", 20, T01_SimpleSort::insertSort);
    }

}
