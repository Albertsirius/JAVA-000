### Week09 作业题目（周四）

3. 改造自定义RPC的程序

   - 尝试将服务端写死查找接口实现类变成泛型和反射

     老师原有的代码已经完成

   - 尝试将客户端动态代理改成AOP，添加异常处理

     采用cglib.enhancer实现AOP，见https://github.com/Albertsirius/JAVA-000/blob/main/Week_09/rpc01/rpcfx-core/src/main/java/huangzihao/homework/rpcfx/client/Rpcfx.java

     添加异常类，https://github.com/Albertsirius/JAVA-000/blob/main/Week_09/rpc01/rpcfx-core/src/main/java/huangzihao/homework/rpcfx/api/RpxfxException.java

     封装异常处理，客户端 https://github.com/Albertsirius/JAVA-000/blob/main/Week_09/rpc01/rpcfx-core/src/main/java/huangzihao/homework/rpcfx/client/Rpcfx.java

     服务端，https://github.com/Albertsirius/JAVA-000/blob/main/Week_09/rpc01/rpcfx-core/src/main/java/huangzihao/homework/rpcfx/server/RpcfxInvoker.java

   - 尝试使用Netty+HTTP作为client端传输方式

     Netty+HTTP还没学会怎么写，暂用java 11的httpclient代替，见https://github.com/Albertsirius/JAVA-000/blob/main/Week_09/rpc01/rpcfx-core/src/main/java/huangzihao/homework/rpcfx/client/Rpcfx.java

     

