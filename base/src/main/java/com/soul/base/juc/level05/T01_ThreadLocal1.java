package com.soul.base.juc.level05;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal�ֲ߳̾�����
 */
public class T01_ThreadLocal1 {

	volatile static Person p = new Person();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//�ڱ������߳�1��ʱ�޸�, 2����ӡֵ
			System.out.println(p.name);
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//�޸Ļ�Ӱ�������߳�, �����ÿ���̶߳�ռ, ��ô��Ҫʹ�� ThreadLocal
			p.name = "lisi";
		}).start();
	}
}

class Person {
	String name = "zhangsan";
}
