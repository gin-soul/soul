
package com.soul.base.juc.level02;

import java.util.concurrent.TimeUnit;

/**
 * ��Ҫ���ַ���������Ϊ��������
 * ������������У�m1��m2��ʵ��������ͬһ������
 * ����������ᷢ���ȽϹ�������󣬱������õ���һ����⣬�ڸ�����д����������ַ�����Hello����
 * �����������Դ�룬���������Լ��Ĵ�����Ҳ������"Hello",��ʱ����п��ܷ����ǳ����������������
 * ��Ϊ��ĳ�������õ�����ⲻ�����ʹ����ͬһ����
 */
public class T07_DoNotLockString {

	static String s1 = "Hello";
	static String s2 = "Hello";

	static void m1() {
		synchronized(s1) {
			System.out.println("m1 start");

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("m1 end");
		}
	}

	static void m2() {
		synchronized(s2) {
			System.out.println("m2 start");

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("m2 end");
		}
	}

	public static void main(String[] args) {
		//��Ҫ���ַ���������Ϊ��������, ������ֵ�����
		T07_DoNotLockString.m1();
		T07_DoNotLockString.m2();
	}

}
