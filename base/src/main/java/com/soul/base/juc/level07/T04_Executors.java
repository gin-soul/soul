package com.soul.base.juc.level07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��ʶExecutors, �̳߳صĹ���
 */
public class T04_Executors {
	public static void main(String[] args) {
		//Executors
		//����ͨ�� ThreadPoolExecutor �������̳߳�, �������һϵ������
		//�߳�������Ϊ Integer.MAX_VALUE ���ܻ���������߳�, ����OOM
		ExecutorService executorService1 = Executors.newCachedThreadPool();

		//��������Ķ�������Ϊ Integer.MAX_VALUE ���ܻᵼ��OOM
		ExecutorService executorService2 = Executors.newSingleThreadExecutor();
		ExecutorService executorService3 = Executors.newFixedThreadPool(10);


	}
}
