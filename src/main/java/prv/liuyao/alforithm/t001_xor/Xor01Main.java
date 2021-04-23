package prv.liuyao.alforithm.t001_xor;

public class Xor01Main {
    /**
     * ^异或: 相同为0,不同为1,又叫无进位相加
     *  0 ^ N = N
     *  N ^ N = 0
     *  a ^ b = b ^ a
     *  (a^b)^c = a^(b^c)  结合律 可扩展到n个数异或
     */
    public static void main(String[] args) {
        exchange();
        findOdd();
        findRight_1();
        findOddTwice();
    }

    // 交换
    public static void exchange(){
        int a = 12;
        int b = 14;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }


    // 只有一个数出现了奇数次 找到
    public static void findOdd(){
        int[] arr = {1, 2, 3, 4, 4, 3, 2, 1, 3, 4, 4};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 找到最右侧的1
    public static void findRight_1(){
        /**
         * a   = 0 1 1 0 1 1 1 0 0 | 1 0 0 0 0
         * ~a  = 1 0 0 1 0 0 0 1 1 | 0 1 1 1 1
         * ~a+1= 1 0 0 1 0 0 0 1 1 | 1 0 0 0 0 = -a
         * ans = 0 0 0 0 0 0 0 0 0 | 1 0 0 0 0 = a & -a
         */
        int a = 16;
        int b = a & -a;
        System.out.println(b);
    }

    // 两个数出现了奇数次 找到
    public static void findOddTwice(){
        int[] arr = {
                1, 1,
                2, 2,
                3, 3, 3, 3,
                4, 4, 4,
                5, 5,
                6
        };
        // 命名奇数次的两个变量为 a,b
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        /**
         * 1 全部异或
         * 2 得出 eor = a ^ b (>0)
         * 3 任意选定 eor 某个位置为1的数, 这里规定选最后位置
         *      因为找到最后一个1 前边已经知道了怎么做 eor&(-eor)
         * 4 这里假设 最后一个1为 第三位
         * 5 可以将所有的数分为两类
         *      A类: 第三位为1
         *      B类: 第三位为0
         * 6 a,b 肯定被分散在这两类中, 假定a在A类中
         * 7 eorp = eor ^ A类 // 数 & (eor & (-eor)) != 0
         *      = eor ^ a
         *      = b
         * 8 则 a = eor ^ eorp
         *
         */
        int eorp = eor;
        int rightOne = eor & -eor;
        for (int i = 0; i < arr.length; i++) {
            // ------- 此处自己coding写的是 >0 ----------
            if ((arr[i] & rightOne) != 0){
                eorp ^= arr[i];
            }
        }
        System.out.println("num1: " + eorp);
        System.out.println("num2: " + (eor ^ eorp));

    }

}
