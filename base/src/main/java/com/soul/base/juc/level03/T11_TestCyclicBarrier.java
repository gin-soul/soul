package com.soul.base.juc.level03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T11_TestCyclicBarrier {
    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);

        //ѭ������, ÿ���ȴ����߳����ﵽ�趨ֵ, ��һ���Ϳ��Բ��ٵȴ���ʼִ��ҵ��
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("���ˣ�����"));

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("���ˣ�����");
            }
        });*/

        for(int i=0; i<100; i++) {

                new Thread(()->{
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            
        }
    }
}
