package prv.liuyao.alforithm.t002_datastruct;

/**
 * 递归
 *  任何递归都一定可以改成非递归的方法 ✔️
 * 分析时间复杂的
 *  master公式 T(N) = a * T(N/b) + O(n^d);
 *      a,b,d常数
 *      先决条件: 子规模 一致
 *          如 取数组最大值,分两半,左侧递归,右侧递归,左右等规模
 *      log_b-a < d -> O(N^d)
 *      log_b-a > d -> O(N^(log_b-a))
 *      log_b-a == d -> O(N^d * log_2-N)
 *
 */
public class T02_Recursion {
}
