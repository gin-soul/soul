package com.soul.base.juc.level05;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal�ֲ߳̾�����
 *
 * ThreadLocal��ʹ�ÿռ任ʱ�䣬synchronized��ʹ��ʱ�任�ռ�
 * ������hibernate��session�ʹ�����ThreadLocal�У�����synchronized��ʹ��
 *
 * ��������ĳ������ThreadLocal
 * - set: ThreadLocal.ThreadLocalMap.set(ThreadLocal, yourValue);
 * - ThreadLocal��;
 * ����ʽ����, ��֤ͬһ��Connection
 *
 */
public class T02_ThreadLocal2 {
	// ThreadLocal ���õ�ֵ���̶߳��е�
	// ͨ�� ThreadLocalMap ʵ��, key��ThreadLoacl����(ThreadLoacl����(this)����ָ�����Thread�е�map), value�Ƕ�Ӧ���õ�ֵ
	// ����ʵ���Ͼ������߳��Լ���map��, ʹ�� ThreadLoacl ��Ϊkey, ���õ�ֵ��Ϊvalue, ����һ����ֵ��
	// (��ͬ�̵߳�ֵ����ͬһ��map���Ǹ��Ե�map, ���Բ�ͬ�߳�ֻ�ܶ�ȡ���Լ���)
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			tl.set(new Person());
			tl.get().name = "gin";
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tl.get().name);
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//ÿ����ͬ�߳����Լ��ı���
			System.out.println(tl.get());
			tl.set(new Person());
			System.out.println(tl.get().name);
		}).start();
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


