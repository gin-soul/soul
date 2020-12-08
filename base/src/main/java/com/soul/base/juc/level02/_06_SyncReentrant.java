
package com.soul.base.juc.level02;

import java.util.concurrent.TimeUnit;

/**
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö������.
 * Ҳ����˵synchronized��õ����ǿ������
 * �����Ǽ̳����п��ܷ��������Σ�������ø����ͬ������
 */
public class _06_SyncReentrant {
	//��ʾ synchronized �Ǹ���������
	synchronized void m() {
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		new TT().m();
	}

}

class TT extends _06_SyncReentrant {
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}
