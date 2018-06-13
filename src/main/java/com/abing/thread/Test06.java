package com.abing.thread;


public class Test06 {

    public static void main(String[] args){
        Res res=new Res();
        InThread inThread=new InThread(res);
        OutThread outThread=new OutThread(res);
        inThread.start();
        outThread.start();
    }
}
