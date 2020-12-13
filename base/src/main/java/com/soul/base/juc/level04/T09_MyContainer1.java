package com.soul.base.juc.level04;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 *
 * ʹ��wait��notify/notifyAll��ʵ��
 *
 */
public class T09_MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; //���10��Ԫ��
	static int count = 0;
	
	
	public synchronized void put(T t) {
		//Ϊʲô��while��������if
		//���������� notifyAll, ���뱣֤ÿ���̶߳��ж���, ��ֹ����(notifyAll)��ֱ��ִ�к���ҵ��
		while(lists.size() == MAX) {
			try {
				this.wait(); //effective java
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		//֪ͨ�������߳̽�������
		//ʵ�ʽ��������е��߳�
		this.notifyAll();
	}
	
	public synchronized T get() {
		T t = null;
		while(lists.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count --;
		//֪ͨ�����߽�������
		//ʵ�ʽ��������е��߳�
		this.notifyAll();
		return t;
	}
	
	public static void main(String[] args) {
		T09_MyContainer1<String> c = new T09_MyContainer1<>();
		//�����������߳�
		for(int i=0; i<10; i++) {
			new Thread(()->{
				//ÿ������������5��
				for(int j=0; j<5; j++) System.out.println(c.get());
			}, "c" + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//�����������߳�
		for(int i=0; i<2; i++) {
			new Thread(()->{
				//ÿ������������25��
				for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j + " ,middle count=" + count);
			}, "p" + i).start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count end=" + count);
	}
}
