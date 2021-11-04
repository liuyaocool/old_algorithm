
struct array_int
{
    int len;
    int* arr;
};

// random init function, you must execute this function before other this source`s func
void random_init();

/**
 * 返回随机长度 值在[-maxValue, maxValue]区间的数组
 * @return 数组使用完后需要使用 free(array_int.arr) 释放内存
 */
struct array_int randomArray(int mxSize, int maxValue);

/**
 * 返回随机长度 值在[-maxValue, maxValue]区间的数组
 */
void randomArrayFill(int arr[], int size, int maxSize, int maxValue);

// 返回[min, max] 之间随机一个数
int randomInt(int min, int max);

// 打乱数组
void upsetArray(int arr[], int len);

// 交换数组中两个数
void swapArray(int arr[], int i, int j);

// 
int* malloc_int(int size);

// give init value to arr every one
void arr_init(int arr[], int len, int val);