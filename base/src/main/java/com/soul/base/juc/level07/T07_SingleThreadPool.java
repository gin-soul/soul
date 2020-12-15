package com.soul.base.juc.level07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool {
	public static void main(String[] args) {
		//��������Ķ�������Ϊ Integer.MAX_VALUE ���ܻᵼ��OOM
		//һ���̵߳��̳߳�, ���Ա���˳��ִ��(�ж���, �����������, ���Լ�new Thread����)
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i=0; i<5; i++) {
			final int j = i;
			service.execute(()->{
				
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
			
	}
}
