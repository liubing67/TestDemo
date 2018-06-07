package com.abing.thread;

public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo01 threadDemo01=new ThreadDemo01();
        Thread thread1=new Thread(threadDemo01,"窗口1");
        Thread thread2=new Thread(threadDemo01,"窗口2");
        thread1.start();
        thread2.start();
    }

}
class ThreadDemo01 implements Runnable{

    private static int count=100;
    @Override
    public void run() {
        while (count>0){
            try {
                Thread.sleep(100);
            }catch (Exception e){

            }
            sale();
        }
    }
    public static void sale(){
        if (count>0){
            System.out.println(Thread.currentThread().getName()+",出售"+(100-count+1)+"张票");
            count--;
        }
    }

}
