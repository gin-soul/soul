
package com.soul.base.juc.level03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock������ָ��Ϊ��ƽ��
 * ��֤ÿ���̶߳����õ���, ����FIFO��һ�������Ľ��������Ҳ�����Ƚ��ȳ���
 * ReenTrantLock Ҳ�����ö���ʵ�ֵĹ�ƽ���ͷǹ�ƽ��
 *
 */
public class T09_ReentrantLock5 extends Thread {

    //����Ϊtrue��ʾΪ��ƽ������Ա�������
	private static ReentrantLock lock=new ReentrantLock(true);
    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"�����");
            }finally{
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T09_ReentrantLock5 rl=new T09_ReentrantLock5();
        Thread th1= new Thread(rl);
        Thread th2= new Thread(rl);
        th1.start();
        th2.start();
    }
}
