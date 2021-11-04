
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "AlgorithmUtil.h"

/**
 * 返回随机长度 值在[-maxValue, maxValue]区间的数组
 * @return 数组使用完后需要使用 free(array_int.arr) 释放内存
 */
struct array_int randomArray(int maxSize, int maxValue)
{
    /* 设置种子 */
    srand( (unsigned)time( NULL ) );
    int size = rand() % maxSize + 1;
	int* arr = (int*) malloc(size * sizeof(int));
    maxValue++;
    for (int i = 0; i < size; i++)
    {
        arr[i] = (rand() % maxValue) - (rand() % maxValue);
        // printf("arr[%d] = %d\n", i, arr[i]);
    }
    struct array_int res = {size, arr};
    return res;
}

// 返回[min, max] 之间随机一个数
int randomInt(int min, int max)
{
    srand( (unsigned)time( NULL ) );
    return rand() % (max - min + 1) + min;
}

// 打乱数组
void upsetArray(int arr[], int len)
{
    srand( (unsigned)time( NULL ) );
    for (int i = 0,j=0; i < len; i++)
    {
        swapArray(arr, i, rand() % len);
    }
}

// 交换数组中两个数
void swapArray(int arr[], int i, int j) 
{
    // printf("swap %d=%d %d=%d\n", i, arr[i], j, arr[j]);
    int temp = arr[i];
    // printf(" tmp = %d\n", temp);
    arr[i] = arr[j];
    arr[j] = temp;
}