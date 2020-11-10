package huangzihao.java0.conc0303;


import java.util.concurrent.CountDownLatch;

/**
 * <p>使用countDownLatch
 *
 * @author huangzihao
 * @since 2020/11/9
 */
public class CountDownLatchMethod extends Thread{

    private CountDownLatch countDownLatch;
    //定义成员变量，保存sum的值
    private volatile int value;

    public CountDownLatchMethod(CountDownLatch countDownLatch) { this.countDownLatch = countDownLatch; }

    public int getValue() { return value; }

    //继承Thread类，override run方法
    @Override
    public void run() {
        value = sum();
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CountDownLatchMethod countDownLatchMethod = new CountDownLatchMethod(countDownLatch);
        Thread thread = new Thread(countDownLatchMethod);
        thread.start();
        countDownLatch.await();

        int result = countDownLatchMethod.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
