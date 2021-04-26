package prv.liuyao.alforithm.t003_sort;

/**
 * 归并排序 O(N*logN)
 *  如: 数组 先左半部分有序f(arr,l,r) 再右半部分有序 最后merge
 *  master = 2 * T(N/2) + O(N);
 *  两半为 T(N/2), O(N) 为merge
 *
 * 1 递归
 *
 * 2 迭代版
 *  定义步长=1 遇到步长为左组 再遇到步长为右组 进行merge
 *  步长*2 再merge
 *  步长*2 再merge
 *  ...
 *
 *  例如9个数 {3,2,4,6,7,1,9,5,8}
 *  步长=1: 3-2 4-6 7-1 9-5 8 -> 23 46 17 59 8
 *  步长=2: 23-46   17-59   8 -> 2346  1579  8
 *  步长=4: 2346-1579       8 -> 12345679    8
 *  步长=8: 12345679-8        -> 123456789
 *
 *  注意 int 有越界问题 步长<Integer.MAX 步长*2>Integer.MAX
 *
 * 面试题
 *  1 小核 每个位置的数左侧比他小的数累加 再计算累加的累加
 *  2 逆序对
 *  3 >=
 *
 *  进度 第四节 54分
 */
public class T01_Merge {

}