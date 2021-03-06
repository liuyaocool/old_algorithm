#include <stdio.h>

#define ARR_LENGTH(arr) sizeof(arr)/sizeof(arr[0])

/**
 * 1. 带符号左移 <<：<< 1 相当于 * 2
 * 2. 带符号右移 >>
 * 3. 无符号右移 >>>
 * 4. 与 &：同1为1 否则为0
 * 5. 或 |：有1为1
 * 6. 异或 ^: 相同为0,不同为1,又叫无进位相加
 *      0 ^ N = N
 *      N ^ N = 0
 *      a ^ b = b ^ a
 *      (a^b)^c = a^(b^c)  结合律 可扩展到n个数异或
 */

// 交换两个值
void exchange()
{
    int a = 12;
    int b = 14;

    a = a ^ b;
    b = a ^ b;
    a = a ^ b;

    printf("a = %d\n", a);
    printf("b = %d\n", b);
}

// 只有一个数出现了奇数次 找到
void findOdd()
{
    int arr[] = {1, 2, 3, 4, 4, 3, 2, 1, 3, 4, 4};
    int eor = 0;
    // printf("odd size = %lu\n", sizeof(arr)); 
    int size = ARR_LENGTH(arr);
    for (int i = 0; i < size; i++) {
        eor ^= arr[i];
    }
    printf("odd = %d\n", eor);
}

 void findRight_1(){
    /**
     * ~a : 取反
     *                         ↓
     * a   = 0 1 1 0 1 1 1 0 0 1 0 0 0 0
     * ~a  = 1 0 0 1 0 0 0 1 1 0 1 1 1 1
     * ~a+1= 1 0 0 1 0 0 0 1 1 1 0 0 0 0 = -a
     * ans = 0 0 0 0 0 0 0 0 0 1 0 0 0 0 = a & -a
     */
    int a = 14;
    int b = a & -a;
    printf("right 1 = %d\n", b);
}

// 两个数出现了奇数次 找到
void findOddTwice(){
    int arr[] = {
            1, 1,
            2, 2,
            3, 3, 3, 3,
            4, 4, 4,
            5, 5,
            6
    };
    // 命名奇数次的两个变量为 a,b
    int eor = 0;
    int size = ARR_LENGTH(arr);
    for (int i = 0; i < size; i++) {
        eor ^= arr[i];
    }
    /**
     * 1 全部异或
     * 2 得出 eor = a ^ b (>0)
     * 3 任意选定 eor 某个位置为1的数, 这里规定选最后位置
     *      eor 某个位置为1, 则a b的此位置肯定不同
     *      而找到最后一个1 前边已经知道了怎么做 eor&(-eor)
     * 4 这里假设 最后一个1为 第三位
     * 5 可以将所有的数分为两类
     *      A类: 第三位为1, 假定a在此类 则此类中所有数^ -> a
     *      B类: 第三位为0, 假定b在此类
     * 6 a,b 肯定被分散在这两类中
     * 7 eorp = eor ^ A类
     *      // A类筛选: A中数 & (eor & (-eor)) != 0 , (eor & (-eor))=二进制最右侧1
     *      = eor ^ a
     *      = b
     * 8 则 a = eor ^ eorp
     *
     */
    int eorp = eor;
    int rightOne = eor & -eor;
    for (int i = 0; i < size; i++) {
        // ------- 此处自己coding写的是 >0 ----------
        if ((arr[i] & rightOne) != 0){
            eorp ^= arr[i];
        }
    }
    printf("oddTwice: %d, %d\n", eorp, (eor ^ eorp));
}

int main()
{
    // exchange();
    // findOdd();
    // findRight_1();
    findOddTwice();

    return 0;
}