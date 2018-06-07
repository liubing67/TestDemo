package com.abing.map;

import java.awt.image.Kernel;

public class LBHashMap<K,V> implements LBMap<K,V> {

    //1.定义table存放HashMap数组元素，默认是没有初始化容器的
    private Node<K,V>[] table=null;
    //2.实际用到table存储容量的大小
    private int size;
    //3.HashMap默认负载因子，负载因子越小，hash冲突几率越低
    private float DEFAULT_LOAD_FACTOR=0.75f;
    //4.table默认初始大小16
    private static int DEFAULT_INITIAL_CAPACITY=16;
    @Override
    public K put(K k, V v) {
        //1判断table的数组大小是否为空（如果为空的情况下，做初始化操作）
        if (table==null){
            table=new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2.hashMap扩容机制为什么要扩容？扩容数组之后，有什么影响？
        //实际存储大小=负载因子*初始容量=DEFAULT_LOAD_FACTOR(0.75)*DEFAULT_INITIAL_CAPACITY(16)=12
        //如果size>12的时候就需要开启扩容数组，扩容数组大小之前两陪
        if (size>(DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR)){
            //需要开始对table进行数组扩容
            resize();
        }
        //3.计算hash值指定下标位置
        int index=getIndex(k,DEFAULT_INITIAL_CAPACITY);
        Node<K,V> node=table[index];
        if (node==null){
            //没有发生hash冲突问题  index冲突
            node=new Node<K, V>(k,v,null);
            size++;
        }else {
            Node<K,V> newNode=node;
            while (newNode!=null){

            }
        }
        return null;
    }
    //对table进行扩容
    private void resize(){
        //1.生成新的table式之前的两倍的大小 DEFAULT_INITIAL_CAPACITY*2
        Node<K,V>[] newTable=new Node[DEFAULT_INITIAL_CAPACITY << 1];
        //重新计算index索引，存放在新的table里面
        for (int i=0;i<table.length;i++){
            //存放在之前的table原来的node
            Node<K,V> oldNode=table[i];
            while (oldNode!=null){
                //赋值为空   为了垃圾回收机制能够回收  将之前的node删除
                table[i]=null;
                //存放在之前的table 原来的key
                K oldK=oldNode.key;
                //重新计算index
                int index=getIndex(oldK,newTable.length);
                //存放在之前的table原来的node next
                Node<K,V> oldNext=oldNode.next;
                //如果index下标在新newTable发生相同的index时候，以链表进行存储
                //原来的node的下一个是最新的，（原来的node存放在新的node下一个）
                oldNode.next=newTable[index];
                //将之前的node赋值给newTable[index]
                newTable[index]=oldNode;
                //判断是否继续循环遍历
                oldNode=oldNext;
            }
        }
        //3.将newTable赋值给老的table
        table=newTable;
        DEFAULT_INITIAL_CAPACITY=newTable.length;
        newTable=null;//赋值为空 为了垃圾回收机制能够回收
    }
    public int getIndex(K k,int length){
        int hashCode=k.hashCode();
        int index=hashCode%length;
        return index;
    }
    @Override
    public K get(K k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    //定义节点
    class Node<K,V> implements Entry<K,V>{
        //存放Map集合Key
        private K key;
        //存放Map集合Value
        private V value;
        //下一个节点Node
        private Node<K,V> next;
        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            //设置新值的返回老的值
            V oldValue=this.value;
            this.value=value;
            return oldValue;
        }
        public Node(K key,V value,Node<K,V> next){
            super();
            this.key=key;
            this.value=value;
            this.next=next;
        }
    }
}
