package prv.liuyao.alforithm.t002_datastruct;

public class T01_DataStruct {

    /**
     * 体系班第三节课
     *
     * 1 单\双 链表反转
     * 2 单\双 链表 删除给定值
     * 3 栈和队列
     *  分别用双向链表和数组实现
     *  数组队列 pollIndex pushIndex size
     * 4 设计栈pop push getMin都是时间O(1)
     *  数据栈: 6 4 5 7 8 2 4 6 1
     *  最小栈: 6 4 4 4 4 2 2 2 1
     *  两个栈同时 pop push
     *  getMin从最小栈取
     * 5 用队列实现栈
     *  push
     *      队列1(q): 6 5 4 3 2 1
     *      队列2(p):
     *  pop
     *      倒队列
     *      队列1(p):               6拿走
     *      队列2(q): 5 4 3 2 1
     *      队列互换
     * 6 用栈实现队列
     *  push
     *      栈1: 1 2 3 4 5
     *      栈2:
     *  poll
     *      倒栈
     *      栈1:
     *      栈2: 5 4 3 2         1 拿走
     *
     * 7 hash表 HashMap
     *  增删改查 O(1)
     *
     * 8 有序表
     *  TreeMap
     *      firstKey() lastKey()
     *      floorKey(3): <=3 最近的
     *      ceilingKey(3): >=3 最近的
     *  红黑树 avl sb树 跳表
     *  增删改查 O(logN)
     *
     */
}
