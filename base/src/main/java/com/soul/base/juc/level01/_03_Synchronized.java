package com.soul.base.juc.level01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
- ��: ���������, �����̼߳乲����Դͬ��
synchronized hotspotʵ��(û�й淶,��ͬ�����ʵ�ֿ��Բ�ͬ):
 ���������ͷ��(64λ,ǰ��λ(mark word)����Ƿ�����);
 ����һ���̹߳���ʱ: ƫ����->��ƫ���һ���߳�(�ֹ���,��Ϊ�����в���,��δ��������,ֻ�Ǽ�¼�˵�һ���̶߳����id);
 �����˵ڶ����߳�,������Ϊ������(cas): �ڶ����߳�Ĭ���Զ�ѭ���ȴ�10��(�Լ�תȦ��),��ȥ��������Ȩ��(��ʱ���һ���߳��ͷ���,��ô�ڶ����̻߳�ȡִ��Ȩ��);
 10���Ժ�,������Ϊ��������,��ʱ��,�ڶ����߳̾ͻ���cpu��������(����CPUִ��Ȩ��);
 ��ֻ������,���ܽ���,���л����synchronized������߳̾�����,����Ϊ��������,�������ֻ��һ���߳�ִ����,Ҳ��֪ƫ����,��������������;

 ������,���߳��Լ�ѭ��,������linuxϵͳ���ں�,�����û�̬���ں�̬���л�
 �ʺ�ִ��ʱ���,�߳��ٵĳ���(ִ��ʱ�䳤��ᵼ�³�ʱ����������ȴ�;�߳�̫��,�����������cpu����ס)
 ִ��ʱ�䳤,�߳�������,��ϵͳ��(synchronized ��������, �̻߳�ȴ�,��������������cpu��Դ)

 ע��: synchronized ����ʹ�� String(���ܺͱ��˴���ͬһ����,��������(��ͬ�߳�),������(ͬһ���߳�)����)
 Integer(�ı�ֵ�����µĶ���, ���ͳɶ����), Long��Щ���������������

 �ص�:
 ������: �������뱻ͬһ���������� synchronized ����(�����з���, ���෽��), ������������������
 ������(���� -> �� -> ��; �ֹ� -> ����; ���ή��;)
| �洢���� | ��־λ | ���� |
| :-----| :----: | :----|
| �����ϣ�롢����ִ��� | 01 | δ���� |
| ƫ���߳�ID | 01 | ƫ���� |
| ָ������¼��ָ�� | 00 | �������� |
| ָ������������ָ�� | 10 | �������� |
| �� | 11 | GC��� |


volatile
1.�����߳̿ɼ���
 - MESI
 - ����һ����Э��
2.��ָֹ��������(��ֹ��ȡ���ճ�ʼ����ֵ)
 - DCL����
 - Double check lock
 - load fence / store fence ��д����(��������ǰ�Ķ�/д����ȫ��������ټ�����������)

 cas(�����Ż� ����)
 Compare And Set
 cas(V, Expected, NewValue)
 (V-Ҫ�ĵ�ֵ, Expected-������ǰҪ���ĵ�ִֵ��casʱ��ѯ��(get)���Ƕ���, NewValue-��Ҫ��Ҫ�ĵ�ֵ���趨�ɵ�ֵ)
 - if V == E
   V = New
   otherwise try again or fail

 ����Ҫ��һ��a=0 ִ�� a=a+1
 ��ôִ�в���ʱ V=0 , E=0 , NEW=1 �����޸ĳɹ�
 ���ִ�в���ʱ V=0 , E=2 , NEW=1 ���ʾV�������߳��޸���, V != E �����»�ȡ������ִ�з���(V=2 , E=2 , NEW=3)

 ABA����, ��Ҫ��԰�װ����, ��������(�������͵İ�װ����)����ν; ���Ů���Ѹ���������, �ֻ���������, �Ѿ�����ԭ���Ǹ�Ů������
 ԭ��: ��װ����ֻ�Ƚ��˵�ֵַ(�����������ֵ���Ƚ�, ʵ�ʿ����Ѿ��������߳��޸���)
 �߳�1:  ��������: filed = A -> filed = B -> filed = A
 �߳�2:  ��������: A(A����ĳЩ����ֵ���޸�, ����״̬-���)
 ����: �߳�1ִ�е� filed = B ʱ, �߳�2����A��ֵ, �߳�1ԭ���뽫 filed ���ó��ʼ��A(û�Ļ���״̬��), ��ʵ�����õ���A(����״̬-���)


 */
public class _03_Synchronized {

    // volatile ֻ�Ǳ�֤�ɼ�, ��δ�����ݼ���
    private static volatile int withoutSyncCount = 20;
    // ʹ�� AtomicInteger ���Ա�֤������� withoutSyncCount ����( Atomic Ϊ cas ��ʵ�� )
    // ������ӡ��ͬ��, ��Ȼ��������
    //private static AtomicInteger withoutSyncCount = new AtomicInteger(20);

    //��̬���� synchronized ���ñ���
    private static int withStaticSyncCount = 20;

    //��ͨ���� synchronized ���ñ���
    private static int withSyncCount = 100;
    // ʹ�� AtomicInteger ���Ա�֤������� withSyncCount ����( Atomic Ϊ cas ��ʵ�� )
    // ������ӡ��ͬ��, ��Ȼ��������
    //private static AtomicInteger withSyncCount = new AtomicInteger(100);


    public static void main(String[] args) {
        try {
            //δ��ͬ��
            testSync(WithoutSync.class);
            Thread.sleep(500);
            System.out.println("WithoutSync end, t1.withoutSyncCount=" + withoutSyncCount);
            System.out.println("--------");

            //��̬����synchronizedͬ��, class��,һ��,��������
            WithStaticSync t2 = new WithStaticSync();
            testSync(WithStaticSync.class);
            Thread.sleep(500);
            System.out.println("WithStaticSync end, t2.withoutSyncCount=" + withStaticSyncCount);
            System.out.println("--------");

            //��ͨ����synchronizedͬ��, this��,����,�޷�����
            testSync(WithSync.class);
            Thread.sleep(1600);
            System.out.println("WithSync end, t3.withoutSyncCount=" + withSyncCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testSync(Class<? extends Runnable> clazz) throws Exception {
        //�����߳�
        List<Thread> threadList = new LinkedList<>();

        // ��ʽһ
        // ע�����ﴴ��������ʵ�� Runnable �Ķ���, ����ʹ�� synchronized ��this��(�����)��ʧЧ
        // ʹ�� static synchronized �� class ������������ס(һ����)
        for (int i = 0; i < 2; i++) {
            Runnable t = clazz.newInstance();
            threadList.add(new Thread(t, "Thread" + i));
        }

        // ��ʽ��
        /*
        //���� this ��Ҳ����Ч,��Ϊֻ������һ�� Runnable ����
        Runnable t = clazz.newInstance();
        for (int i = 0; i < 2; i++) {
            threadList.add(new Thread(t, "Thread" + i));
        }*/

        //�����߳�
        threadList.forEach(Thread::start);
    }


    //�����δ����
    static class WithoutSync implements Runnable {

        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5);
                    //������ܻᵼ���������ղ�Ϊ 0
                    withoutSyncCount--;
                    //withoutSyncCount.getAndDecrement();
                    Thread.sleep(5);
                    //����ᵼ�´�ӡ�Ŀ����������߳��޸ĵ�ֵ
                    System.out.println(Thread.currentThread().getName() + " count1= " + withoutSyncCount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WithStaticSync implements Runnable {
        public void run() {
            countSync();
        }

        //�����ͬ��synchronized(WithStaticSync.class)
        private static synchronized void countSync() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5);
                    withStaticSyncCount--;
                    Thread.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " count2= " + withStaticSyncCount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WithSync implements Runnable {
        //ʹ��this��ǰ������, ��� WithSync �����˶����this����ʧЧ
        public synchronized void run() {
            // synchronized�� ��������
            testReentry();
        }

        private synchronized void testReentry() {
            try {
                for (int i = 0; i < 50; i++) {
                    //Random random = new Random();
                    //int nextInt = random.nextInt(4) + 1;
                    Thread.sleep(2);
                    withSyncCount--;
                    //withSyncCount.getAndDecrement();
                    Thread.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " count3= " + withSyncCount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
