package com.abing.list;

import java.util.Arrays;

public class ABArrayList<E> implements ABList<E> {
    // ArrayList底层采用数组存放
    transient Object[] elementData;

    //定义默认容量大小
    private static final int DEFAULT_CAPACITY = 10;

    // 记录实际ArrayList大小
    private int size;
    public ABArrayList(){
        this(DEFAULT_CAPACITY);
    }
    public ABArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }


    public void add(E e) {
        //1.判断实际存放的数据容量是否大于elementData容量
        ensureExplicitCapacity(size+1);
        //2 .使用下标进行赋值
        elementData[size++]=e;

    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }
    E elementData(int index) {
        return (E) elementData[index];
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public E remove(int index) {
        //1.使用下标查询该值是否存在
        E e=get(index);
        //计算删除元素后面的长度
        int numMoved=size-index-1;
        //删除原理  使用arraycopy往前移动数据，将最后一个变为空
        if (numMoved>0)
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        elementData[--size]=null;
        return e;
    }

    @Override
    public boolean remove(E e) {
        for (int i=0;i<elementData.length;i++){
            E ob=elementData(i);
            if (ob.equals(e)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(int index, E e) {
        //判断实际存放的数量容量是否大于elementdata容量
        ensureExplicitCapacity(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index]=e;
        size++;
    }

    public void ensureExplicitCapacity(int minCapacity){
        if (size==elementData.length){
            int oldCapacity=elementData.length;
            //新数据容量大小  oldCapacity>>1  相当与  oldCapacity/2
            int newCapacity=oldCapacity+(oldCapacity>>1);
            //如果初始容量为1的时候，那么他扩容的大小为多少呢？
            if (newCapacity-oldCapacity<0){
                newCapacity=minCapacity;//最少保证容量和minCapacity一样
            }
            //将老数组的值赋值到新数组里面去
            elementData=Arrays.copyOf(elementData,newCapacity);
        }
    }
    public void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("越界啦!");
    }
}
