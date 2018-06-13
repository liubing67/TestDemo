package com.abing.thread;

import com.sun.org.apache.regexp.internal.RE;

//共享对象
class Res{
    public String name;
    public String sex;
    //为true情况下，允许读，不能写
    //为false情况下 允许写，不能读
    public boolean flag=false;
}//生产线程
class InThread extends Thread{
    public Res res;
    public InThread(Res res){
        this.res=res;
    }

    @Override
    public void run(){
        int count=0;
        while (true) {
            synchronized (res) {
                if (res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    res.name = "小红";
                    res.sex = "女";
                } else {
                    res.name = "小军";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
                res.flag=true;// 标记当前线程为等待
                res.notify();
            }
        }
    }
}
//读取线程
class OutThread extends Thread{
    public Res res;
    public OutThread(Res res){
        this.res=res;
    }
    @Override
    public void run(){
        while (true){
            synchronized (res){
                if (!res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.name+"---------"+res.sex);
                res.flag=false;
                res.notify();
            }

        }
    }
}
public class Test05 {

    public static void main(String[] args){
        Res res=new Res();
        InThread inThread=new InThread(res);
        OutThread outThread=new OutThread(res);
        inThread.start();
        outThread.start();
    }
}
