package huangzihao.java0.conc0303;

/**
 * <p>采用Sychronized对临界区加锁，计算值线程进入前加锁，计算结果完成后释放；获得值线程等待计算结果
 *
 * @author huangzihao
 * @since 2020/11/10
 */
public class SychronizedMethod {

    //保存中间结果
    private Integer value = null;

    public int getValue() throws InterruptedException {
        synchronized (this) {
            //如果还没设置中间值，线程等待，并释放锁
            while (value == null)
                wait();
        }
        return value;
    }

    public void setValue(int value) {
        synchronized (this) {
            this.value = value;
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        final SychronizedMethod sychronizedMethod = new SychronizedMethod();
        Thread thread = new Thread( () -> {
            sychronizedMethod.setValue(sum());
        });
        thread.start();

        int result = sychronizedMethod.getValue(); //这是得到的返回值

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
