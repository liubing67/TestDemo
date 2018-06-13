package com.abing.thread;

public class ReorderExample {
    volatile int a=0;
    volatile boolean flag=false;
    //写入线程
    public void writer(){
        a=1;
        flag=true;
    }
    //读取线程
    public void reader(){
        if (flag){
            int i=a*a;
        }
    }
}
