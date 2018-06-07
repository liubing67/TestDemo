package com.abing.thread;

class THreadDemo04 extends Thread {

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("线程开始。。。。。");
        while (flag) {

        }
        System.out.println("线程结束。。。。。");

    }

    public void setRuning(boolean flag) {
        this.flag = flag;
    }

}

public class Test04 {

    public static void main(String[] args) throws InterruptedException {
        THreadDemo04 tHreadDemo04 = new THreadDemo04();
        tHreadDemo04.start();
        Thread.sleep(3000);
        tHreadDemo04.setRuning(false);
        System.out.println("flag已经更改为false了");
        Thread.sleep(1000);
        System.out.println("flag:" + tHreadDemo04.flag);
    }
}
