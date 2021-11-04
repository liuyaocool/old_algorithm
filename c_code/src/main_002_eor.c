#include <stdio.h>
#include <stdlib.h>
#include "utils/AlgorithmUtil.h"

#define ARR_LENGTH(arr) sizeof(arr)/sizeof(arr[0])

/**
 * a出现了k次, 其他都出现了m次, 且m>1,m>k, m给定, 找出a k. 空间O(1), 时间O(n)
 * 思路:
 *  1. 所有数 二进制位为1的 累加到 int[32] 中
 *  2. int[32]某一位 如果是m的整数倍,则说明 此位置a的二进制为是0, 不是整数倍 a的二进制位为1
 *  3. int[32] 遍历 %m>0 = 1, 则得到a的二进制, 得a
 * @param arr
 * @param k
 * @param m
 * @param resData put result [a, k]
 */
void findTimes(int arr[], int arrsize, int m, int resData[]) {
    int binLen = 32;
    int t[32] = {0};  // fixed length arr, can default 0 use this mode
    int zeroTimes = 0;
    
    for (int i = 0; i < arrsize; i++)
    {
        if (arr[i] == 0) {
            zeroTimes++;
        }
        for (int j = 0; j < binLen; j++) {
            t[j] += arr[i] >> j & 1; // 收集二进制位
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
    resData[0] = result;
    resData[1] = result == 0 ? zeroTimes : k;
}

void main() {
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
//        printf(findTimesByHash(arr1, nn, mm));
//        printf(findTimes(arr1, nn, mm));

    random_init();
    
    printf("测试开始\n");
    long t = 0;

    int minVal = -100;
    int maxVal = 100;
    int valSize = maxVal - minVal + 1;
    int maxM = 40;
    int loopTimes = 1000;
    // 最终的随机数组
    int* randArr = malloc_int(maxM * valSize);
    // total num times, et [-100, 100] 201 numbers
    int repeat[valSize]; 

    for (int i = 0; i < loopTimes; i++) {
        int k = randomInt(1, maxM-1);
        int m = randomInt(k+1, maxM);
        int a = randomInt(minVal, maxVal); // need find

        // ↓↓↓↓↓↓↓↓↓↓↓↓↓ generate random array ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        int mTimes = randomInt(1, maxVal - minVal); // m次的数有多少个
        int arrsize = m * mTimes + k;
        int index = 0;
        // 先把 k次的数加入结果
        while (index < k) {
            randArr[index++] = a;
        }
        arr_init(repeat, valSize, 0);
        // printf("arrsize=%d\n", arrsize);
        // 避免产生重复的数 不必排除0
        for (int i = 0; i < mTimes; i++) {
            int num;
            do {
                num = randomInt(minVal, maxVal);
                // printf("repeat[num + minVal]=%d\n", repeat[num + minVal]);
            } while (1 == repeat[num - minVal]);
            repeat[num - minVal] = 1;
            // printf("randArr[%d] = %d\n", index, num);
            for (int j = 0; j < m; j++) {
                randArr[index++] = num;
            }
        }
        upsetArray(randArr, arrsize);
        // for (int i = 0; i < arrsize; i++)
        // {
        //     printf("randArr[%d] = %d\n", i, randArr[i]);
        // }
        // ↑↑↑↑↑↑↑↑↑↑↑ generate random array ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        
        long tt = 0; //System.currentTimeMillis();
        int res[2];
        findTimes(randArr, arrsize, m, res);
        // t += System.currentTimeMillis() - tt;

        if (a != res[0] || k != res[1]){
            printf("结果错误: a: <%d, %d>, k:<%d, %d>\n", a, res[0], k, res[1]);
        }
    }
    free(randArr);
    printf("测试结束: %ld ms\n", t);
}
