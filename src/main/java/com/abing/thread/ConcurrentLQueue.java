package com.abing.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLQueue {

    //阻塞式队列最大的好处，能够防止队列容易溢出，防止丢失数据
    public static void main(String[] args){
        //非阻塞式队列 ，无界队列
        ConcurrentLinkedQueue<String> concurrentLQueue=new ConcurrentLinkedQueue();
        concurrentLQueue.offer("abing");
        concurrentLQueue.offer("张三");
        concurrentLQueue.offer("李四");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        concurrentLQueue.offer("王五");
        System.out.println(concurrentLQueue.size());
        //获取队列，只能获取一个队列
        System.out.println(concurrentLQueue.poll());
        System.out.println(concurrentLQueue.poll());
        System.out.println(concurrentLQueue.poll());
        System.out.println(concurrentLQueue.poll());

        //获取队列的个数
        System.out.println(concurrentLQueue.size());

    }
}
