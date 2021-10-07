package prv.liuyao.alforithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存LRU算法: 不常用的删除
 *  hash表 + 双向链表
 *  每使用一次缓存,将当前缓存页放到链表最后,这样头结点总是不常用的
 *
 *  put get 都是O(1)复杂度
 *  hash表为了快速找到位置,一遍将缓存放到
 *  
 */
public class LRUCache {

    public static void main(String[] args) {

        LRUCache lRUCache = new LRUCache(3);
        // 缓存是 {1=1}
        lRUCache.put(1, 1); System.out.println("put(1, 1) -> " + lRUCache.toString());
        // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 2); System.out.println("put(2, 2) -> " + lRUCache.toString());
        //
        System.out.print("get(1)=" + lRUCache.get(1) + " -> " + lRUCache.toString() + "\n");
        // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(3, 3); System.out.println("put(3, 3) -> " + lRUCache.toString());
        //
        System.out.print("get(2)=" + lRUCache.get(2) + " -> " + lRUCache.toString() + "\n");
        // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.put(4, 4); System.out.println("put(4, 4) -> " + lRUCache.toString());
        //
        System.out.print("get(1)=" + lRUCache.get(1) + " -> " + lRUCache.toString() + "\n"); // 返回 -1 (未找到)
        //
        System.out.print("get(3)=" + lRUCache.get(3) + " -> " + lRUCache.toString() + "\n"); // 返回 3
        //
        System.out.print("get(4)=" + lRUCache.get(4) + " -> " + lRUCache.toString() + "\n"); // 返回 4

    }

    private int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return map.containsKey(key) ? getNode(key).value : -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node n = map.get(key); // 不更新顺序
//            Node n = getNode(key); // 更新顺序
            n.value = value;
            return;
        }
        Node n = new Node(key, value);
        if (null == head) {
            // fitsr add -> 单元素 部分为环
            head = (tail = n);
            head.next = tail;
            tail.pre = head;
        } else {
            // last add -> 多元素 部分为环
            addLast(n);
        }
        map.put(key, n);
        // removeFirst
        if (map.size() > capacity) {
            map.remove(head.key);
            head = head.next;
        }
    }

    // 将n放到最后
    private void addLast(Node n) {
        n.pre = tail;
        tail.next = n;
        tail = n;
    }

    // 获得节点 并将节点移动到最后
    private Node getNode(int key) {
        Node n = map.get(key);
        // size == 1 || (size > 1 && ~)
        if (tail == n) {
            return n;
        }
        // 换指针
        if (head == n) {
            head = head.next;
        } else {
            n.pre.next = n.next;
            n.next.pre = n.pre;
        }
        // 换顺序
        addLast(n);
        return n;
    }

    @Override
    public String toString() {
        Node n = head;
        StringBuilder sbu = new StringBuilder("{");
        while (n != tail) {
            sbu.append(n.key).append("=").append(n.value).append(", ");
            n = n.next;
        }
        sbu.append(n.key).append("=").append(n.value).append("}");
        return "LRUCache" + sbu.toString();
    }

    private class Node{
        Node pre;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}