package huangzihao.java0.conc0303;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>使用FutureTask封装，没有使用线程池
 *
 * @author huangzihao
 * @since 2020/11/9
 */
public class FutureWithThreadMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>( () -> sum());
        //新启一个线程
        Thread thread = new Thread(futureTask);
        thread.start();
        int result = futureTask.get();

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
