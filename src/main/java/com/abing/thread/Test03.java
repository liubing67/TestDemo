package com.abing.thread;

class Res{

    public static ThreadLocal<Integer> threadLocal =new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public  Integer getNumber() {
        int count=threadLocal.get()+1;
        threadLocal.set(count);
        return count;
    }
}
public class Test03 extends Thread{

    private Res res;
    public Test03(Res res){
        this.res=res;
    }
    @Override
    public void run() {
        for (int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()+","+res.getNumber());
        }
    }

    public static void main(String[] args){
        Res res=new Res();
        Test03 t1=new Test03(res);
        Test03 t2=new Test03(res);
        t1.start();
        t2.start();
    }
}
