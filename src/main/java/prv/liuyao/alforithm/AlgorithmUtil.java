package prv.liuyao.alforithm;

import java.lang.reflect.Array;
import java.util.Random;

public class AlgorithmUtil {

    public static int[] randomArray(int maxSize, int maxValue){
        // [0,1)    -> Math.random()
        // [0,N)    -> Math.random() * N
        // [0,N-1]  -> (int) (Math.random() * N)
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];// 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] randomArray(int len){
        int[] ret = new int[len];
        final Random random = new Random();
//        len *= 5;
        for (int i = 0; i < ret.length; i++) {
            ret[i] = random.nextInt(len) + 1;
        }
        return ret;
    }

    public static int[] copyArray(int[] arr){
        int[] ret = new int[arr.length];
        System.arraycopy(arr, 0, ret, 0, ret.length);
        return ret;
    }

    public static <T> T[] copyArray(T[] arr){
        T[] ret = (T[]) Array.newInstance(arr[0].getClass(), arr.length);
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    public static boolean checkArray(int[] arr1, int[] arr2){
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("数组不同。");
                return false;
            }
        }
        System.out.println("数组相同。");
        return true;
    }

    public static void swapArray(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(String name, int[] arr){
        System.out.print(name);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    // 返回 [min, max]之间的一个数
    public static int randomInt(int min, int max){
        // 12, 20
        // [0, 1)
        // [0, 9)
        // [12, 21)
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void upsetArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swapArray(arr, i, randomInt(0, arr.length-1));
        }
    }

    public static void main(String[] args) {
        int[] a = randomArray(10);
        int[] copy = copyArray(a);
        int[] correct = copyArray(a);
//        correctSort(correct);
//        SortUtils.quickSort(a, 0, a.length-1);

        printArray("原来数组：", copy);
        printArray("排序之后：", a);
        printArray("正确排序：", correct);
        checkArray(correct, a);
    }
}
