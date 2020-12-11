package com.soul.base.juc.level04;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ������
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 *
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ����
 * ���ң������if �� break֮�䱻����̴߳�ϣ��õ��Ľ��Ҳ����ȷ��
 * ����ô���أ�
 *
 * ע��: volatile������Ҫ��������ֵ(���ñ�����޸������߳̿ɼ�, ��������������޸������̲߳��ɼ�)
 *
 */
public class T02_WithVolatile {

	//�������volatile���ܹ���֪�����ݱ仯, ����size��������ͬ������, ���ִ�й���, ��ȡ���Ŀ��ܻ��Ǿ�ֵ
	// volatile ���ܸ�֪������������
	//volatile List lists = new LinkedList();

	//����Ҫ����һ��ͬ���ļ���
	volatile List lists = Collections.synchronizedList(new LinkedList<>());

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {

		T02_WithVolatile c = new T02_WithVolatile();
		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);

				//���ٵȴ�, t2���ܶ�ȡ�������µ�ֵ����������
				/*try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		}, "t1").start();
		
		new Thread(() -> {
			while(true) {
				//����� volatile, ��size����Ϊͬ������, ����׼ȷ�ɼ�
				if(c.size() == 5) {
					break;
				}
			}
			System.out.println("t2 ����");
		}, "t2").start();
	}
}
