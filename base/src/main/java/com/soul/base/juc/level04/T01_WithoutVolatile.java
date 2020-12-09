
package com.soul.base.juc.level04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * �����⣺
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 *
 * �����������������������������
 */
public class T01_WithoutVolatile {

	//û�� volatile �̼߳��޷���֪�����ݱ仯
	List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		T01_WithoutVolatile c = new T01_WithoutVolatile();

		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		new Thread(() -> {
			while(true) {
				//ֻ�����߳�ִ��ʱ��ȡ���� lists һ�ݿ�������, ÿ��ִ�в���鿴ֵ�Ƿ��б仯
				if(c.size() == 5) {
					break;
				}
			}
			System.out.println("t2 ����");
		}, "t2").start();
	}
}
