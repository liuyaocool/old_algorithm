#include <stdio.h>

#define ARR_LENGTH(arr) sizeof(arr)/sizeof(arr[0])

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

    printf("测试开始");
    long t = 0;

    int minVal = -100;
    int maxVal = 100;
    int maxM = 40;
    int loopTimes = 10000;

    for (int i = 0; i < loopTimes; i++) {
        int k = randomInt(1, maxM-1);
        int m = randomInt(k+1, maxM);
        int kTimeVal = randomInt(minVal, maxVal);
        int arr[] = randomArr(minVal, maxVal, kTimeVal, k, m);
        int arrsize = ARR_LENGTH(arr);
        
        long tt = 0; //System.currentTimeMillis();
        int res[] = findTimes(arr, arrsize, m);
        // t += System.currentTimeMillis() - tt;

        if (kTimeVal != res[0] || k != res[1]){
            printf(kTimeVal + ", " + res[0]);
            printf(k + ", " + res[1]);
            printf("结果错误");
        }
    }
    printf("测试结束: %ld ms\n", t);
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
int * randomArr(int minVal, int maxVal, int kTimeVal, int k, int m){
    int mTimes = randomInt(1, maxVal - minVal); // m次的数有多少个
    int result[m * mTimes + k]; // 最终的随机数组
    int index = 0;

    // 先把 k次的数加入结果
    while (index < k) {
        result[index++] = kTimeVal;
    }

    // 避免产生重复的数 不必排除0
    // HashSet<Integer> set = new HashSet<>();
    // set.add(kTimeVal);

    for (int i = 0; i < mTimes; i++) {
        int num;
        // do {
            // num = randomInt(minVal, maxVal);
        // } while (set.contains(num));
        // set.add(num);
        for (int j = 0; j < m; j++) {
            result[index++] = num;
        }
    }
    upsetArray(result);
    return result;
}

/**
 * 思路 int[32] 保存每一个二进制位为1出现的次数
 * 如果 二进制位与m取模为0 则说明要找的那个数在此二进制位上为0
 * @param arr
 * @param k
 * @param m
 * @return
 */
int* findTimes(int arr[], int arrsize, int m) {
    int binLen = 32;
    int t[binLen];
    int zeroTimes = 0;
    for (int i = 0; i < arrsize; i++)
    {
        if (arr[i] == 0) {
            zeroTimes++;
        }
        for (int j = 0; j < binLen; j++) {
            t[j] += arr[i] >> j & 1; // 收集二进制位
        }
        /* code */
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
    int res[] = {result, result == 0 ? zeroTimes : k};
    return res;
}