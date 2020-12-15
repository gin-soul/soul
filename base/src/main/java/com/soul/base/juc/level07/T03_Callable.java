package com.soul.base.juc.level07;

import java.util.concurrent.*;

/**
 * ��ʶCallable����Runnable��������չ
 * ��Callable�ĵ��ã������з���ֵ
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        //�첽
        Future<String> future = service.submit(c);
        //����, ��;���Դ���һЩҵ���߼�
        System.out.println(future.get());

        service.shutdown();
    }

}
