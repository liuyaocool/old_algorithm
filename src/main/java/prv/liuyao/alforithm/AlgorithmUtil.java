package prv.liuyao.alforithm;

public class AlgorithmUtil {

    public static int[] randomArr(int maxSize, int maxValue){
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

    public static int[] copyArray(int[] arr){
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

}
