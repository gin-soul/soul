package com.soul.base.juc.level07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ��ʶExecutorService,�Ķ�API�ĵ�
 * ��ʶsubmit��������չ��execute����������һ������ֵ
 *
 * concurrent vs parallel
 * concurrent, ����, ָ������ύ
 * parallel, ����, ָ����ִ��(�ڶ��cpu��ִ��)
 * �����ǲ������Ӽ�
 */
public class T02_ExecutorService  {
    public static void main(String[] args) {
        ExecutorService e = Executors.newCachedThreadPool();
        e.submit(() -> System.out.println("hello ExecutorService"));
        //ֹͣ
        e.shutdown();
    }
}
