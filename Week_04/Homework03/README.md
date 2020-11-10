### 说明
四种方法实现：
1. 采用新建线程，join方法等待新建线程计算结果 - ThreadMethod
2. 采用加锁方法，通过线程wait方法等待计算结果 - SychronizedMethod
3. 采用FutureTask，利用Future的get方法等待计算结果 - FutureWithThreadMethod
4. 在3的基础上使用线程池 - FutureWithExecutorMethod
5. 采用CountDownLatchMethod - CountDownLatchMethod
6. 采用CompletableFuture - CompletatableFutureMethod

线程间等待的场景，采用Future或者CompletableFuture更加方便