
package com.soul.base.juc.level02;

import java.util.concurrent.TimeUnit;

/**
 * �����⣺ģ�������˻�
 * ��ҵ��д��������
 * ��ҵ�������������
 * �����в��У�
 *
 * ���ײ���������⣨dirtyRead��
 */
public class _05_Account {
	String name;
	double balance;
	
	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			//�����˻�������2�����
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		this.balance = balance;
	}
	//�����˻���� �� �˻�����ѯ����Ҫ����Ϊͬ������, �������
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	
	public static void main(String[] args) {
		_05_Account a = new _05_Account();
		new Thread(()->a.set("zhangsan", 100.0)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//���߳̽��ȴ�1��, set�����ȴ�2��, �ʻ��ȶ�ȡ��Ĭ��ֵ
		System.out.println(a.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//���set����, ��ȡ�������õ�ֵ
		System.out.println(a.getBalance("zhangsan"));
	}
}
