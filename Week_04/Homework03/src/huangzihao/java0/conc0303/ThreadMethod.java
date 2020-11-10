package huangzihao.java0.conc0303;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>使用简单新建一个线程的方法
 *
 * @author huangzihao
 * @since 2020/11/9
 */
public class ThreadMethod {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        //使用Atom类型保存结果
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread thread = new Thread(() -> {
            atomicInteger.set(sum());
        });
        thread.start();
        thread.join();


        int result = atomicInteger.get(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

}
