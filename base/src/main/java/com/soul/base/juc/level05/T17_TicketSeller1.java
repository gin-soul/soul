package com.soul.base.juc.level05;

import java.util.ArrayList;
import java.util.List;

/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 *
 *
 * ��������ĳ�����ܻ������Щ���⣿
 * ����(���һ��Ʊ����̻߳�����, ����remove��)
 * ���ܻ��״� java.lang.ArrayIndexOutOfBoundsException
 * ����:1.���������ͬ�� 2.�жϺ��Ƴ��߼���Ҫͬ��(������������Ҫ��֤ԭ����)
 */
public class T17_TicketSeller1 {
	static List<String> tickets = new ArrayList<>();
	
	static {
		for(int i=0; i<10000; i++) tickets.add("Ʊ��ţ�" + i);
	}
	
	
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(tickets.size() > 0) {
					System.out.println("������--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
