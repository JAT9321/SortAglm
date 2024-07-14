package org.example;

// leetcode 146 LRU缓存

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class LRUCache {


    class DLinkedNode {
        int key;
        int vaule;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int vaule) {
            this.key = key;
            this.vaule = vaule;
        }
    }

    Map<Integer, DLinkedNode> cache = new HashMap<>();
    int size = 0;
    int capacity = 0;
    DLinkedNode head, tail;


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 伪头部和尾部节点，方便插入删除
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {


        DLinkedNode dLinkedNode = cache.get(key);
        // key值不存在
        if (dLinkedNode == null) {
            return -1;
        }
        // key值存在，再把他在双向链表中的位置移动到头部
        moveToHead(dLinkedNode);
        return dLinkedNode.vaule;
    }


    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        // 如果 key 不存在，创建一个新的节点
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            //添加到cache中
            cache.put(key, newNode);
            //添加到双向链表中
            addToHead(newNode);
            this.size++;
            // 如果超出容量，删除双向链表的尾部节点，并且删除哈希表缓存中的项
            if (this.size > capacity) {
                DLinkedNode tailNode = removeTail();
                cache.remove(tailNode.key);
                this.size--;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.vaule = value;
            moveToHead(node);
        }
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }


    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    private void addToHead(DLinkedNode newNode) {
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
    }


    private void moveToHead(DLinkedNode dLinkedNode) {
        removeNode(dLinkedNode);
        addToHead(dLinkedNode);
    }
}
