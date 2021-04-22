package t001_xor;

import org.junit.Test;

public class Xor01Main {
    /**
     * ^异或: 相同为0,不同为1,又叫无进位相加
     *  0 ^ N = N
     *  N ^ N = 0
     *  a ^ b = b ^ a
     *  (a^b)^c = a^(b^c)  结合律 可扩展到n个数异或
     */

    @Test // 交换
    public void exchange(){
        int a = 12;
        int b = 14;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }


    @Test // 找奇数次
    public void findOdd(){
        int[] arr = {1, 2, 3, 4, 4, 3, 2, 1, 3, 4, 4};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    @Test // 找到最右侧的1
    public void findRight_1(){
        int a = 13;

        a &= -a;

        System.out.println(a);
    }
}
