package prv.liuyao.alforithm.t001_bit;

import java.util.HashSet;

import static prv.liuyao.alforithm.AlgorithmUtil.*;

/**
 * a出现了k次, 其他都出现了m次, 且m>1,m>k, m给定, 找出a k. 空间O(1), 时间O(n)
 * 思路:
 *  1. 所有数 二进制位为1的 累加到 int[32] 中
 *  2. int[32]某一位 如果是m的整数倍,则说明 此位置a的二进制为是0, 不是整数倍 a而二进制位为1
 *  3. int[32] 遍历 %m>0 = 1, 则得到a的二进制, 得a
 */
public class Eor02Main {
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
        long t = 0;

        int minVal = -100;
        int maxVal = 100;
        int maxM = 40;
        int loopTimes = 10000;

        for (int i = 0; i < loopTimes; i++) {
            int k = randomInt(1, maxM-1);
            int m = randomInt(k+1, maxM);
            int kTimeVal = randomInt(minVal, maxVal);
            int[] arr = randomArr(minVal, maxVal, kTimeVal, k, m);

            long tt = System.currentTimeMillis();
            int[] res = findTimes(arr, m);
            t += System.currentTimeMillis() - tt;

            if (kTimeVal != res[0] || k != res[1]){
                System.out.println(kTimeVal + ", " + res[0]);
                System.out.println(k + ", " + res[1]);
                System.out.println("结果错误");
            }
        }
        System.out.println("测试结束: " + t + " ms");
    }

    /**
     * 随机数组
     * @param minVal
     * @param maxVal
     * @param kTimeVal 要找的数
     * @param k
     * @param m
     * @return [随机数组]
     */
    public static int[] randomArr(int minVal, int maxVal, int kTimeVal, int k, int m){
        int mTimes = randomInt(1, maxVal - minVal); // m次的数有多少个
        int[] result = new int[m * mTimes + k]; // 最终的随机数组
        int index = 0;

        // 先把 k次的数加入结果
        while (index < k) {
            result[index++] = kTimeVal;
        }

        // 避免产生重复的数 不必排除0
        HashSet<Integer> set = new HashSet<>();
        set.add(kTimeVal);

        for (int i = 0; i < mTimes; i++) {
            int num;
            do {
                num = randomInt(minVal, maxVal);
            } while (set.contains(num));
            set.add(num);
            for (int j = 0; j < m; j++) {
                result[index++] = num;
            }
        }
        upsetArray(result);
        return result;
    }

    /**
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    // 思路 int[32] 保存每一个二进制位为1出现的次数
    // 如果 二进制位与m取模为0 则说明要找的那个数在此二进制位上为0
    public static int[] findTimes(int[] arr, int m) {
        int binLen = 32;
        int[] t = new int[binLen];
        int zeroTimes = 0;
        for (int num: arr) {
            if (num == 0) {
                zeroTimes++;
            }
            for (int j = 0; j < binLen; j++) {
                t[j] += num >> j & 1; // 收集二进制位
            }
        }
        int result = 0;
        int k = 0;
        for (int i = 0; i < binLen; i++) {
            int i1 = t[i] % m;
            if (i1 != 0){
                result |= 1 << i; // | 有1为1
                k = i1;
            }
        }
        // 查找的数为0 是边界条件
        return new int[]{result, result == 0 ? zeroTimes : k};
    }
}
