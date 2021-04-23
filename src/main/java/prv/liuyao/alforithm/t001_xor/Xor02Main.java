package prv.liuyao.alforithm.t001_xor;

import java.util.HashMap;
import java.util.HashSet;

import static prv.liuyao.alforithm.AlgorithmUtil.*;

/**
 * a出现了n次, 其他都出现了m次, 且m>1,n<m, 找到a, 空间O(1), 时间O(n)
 */
public class Xor02Main {
    public static void main(String[] args) {
//
//        int[] arr1 = {
//                1, 1, 1,
//                2, 2, 2,
//                3, 3,
//                4, 4, 4,
//                5, 5, 5,
//                6, 6, 6,
//        };
//        upsetArray(arr1);
//        int nn = 2, mm = 3;
//        System.out.println(findTimesByHash(arr1, nn, mm));
//        System.out.println(findTimes(arr1, nn, mm));

        System.out.println("测试开始");

        int minVal = -100;
        int maxVal = 100;
        int maxM = 20;
        int times = 100000;

        for (int i = 0; i < times; i++) {
            int n = randomInt(1, maxM-1);
            int m = randomInt(2, maxM);
            n = n >= m ? m - 1 : n;
            int[] arr = randomArr(minVal, maxVal, n, m);
            int currentNum = findTimesByHash(copyArray(arr), n, m);
            int res = findTimes(arr, n, m);
            if (currentNum != res){
                System.out.println(currentNum);
                System.out.println(res);
                System.out.println("结果错误");
            }
        }
        System.out.println("测试结束");
    }

    public static int[] randomArr(int minVal, int maxVal, int n, int m){
        int mTimes = randomInt(1, maxVal - minVal); // m次的数有多少个
        int[] result = new int[m * mTimes + n];
        int index = 0;

        // 避免产生重复的数 不必排除0
        HashSet<Integer> set = new HashSet<>();

        int num = randomInt(minVal, maxVal);
        set.add(num);
        for (int i = 0; i < n; i++) {
            result[index++] = num;
        }

        for (int i = 0; i < mTimes; i++) {
            do {
                num = randomInt(minVal, maxVal);
            } while (set.contains(num));
            for (int j = 0; j < m; j++) {
                result[index++] = num;
            }
        }
        upsetArray(result);
        return result;
    }

    public static int findTimesByHash(int[] arr, int n, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: arr){
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num: map.keySet()) {
            if (map.get(num) == n) {
                return num;
            }
        }
        return Integer.MAX_VALUE;
    }

    // 思路 int[32] 保存每一个二进制位为1出现的次数
    // 如果 二进制位与m取模为0 则说明要找的那个数在此二进制位上为0
    public static int findTimes(int[] arr, int n, int m) {
        int binLen = 32;
        int[] t = new int[binLen];
        for (int num: arr) {
            for (int j = 0; j < binLen; j++) {
                t[j] += num >> j & 1; // 收集二进制位
            }
        }
        int result = 0;
        for (int i = 0; i < binLen; i++) {
            if (t[i] % m != 0){
                result |= 1 << i;
            }
        }
        return result;
    }
}
