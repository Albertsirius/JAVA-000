package huangzihao.homework.rpcfx.demo.provider;

import huangzihao.homework.rpcfx.api.RpcfxRequest;
import huangzihao.homework.rpcfx.api.RpcfxResolver;
import huangzihao.homework.rpcfx.api.RpcfxResponse;
import huangzihao.homework.rpcfx.demo.api.OrderService;
import huangzihao.homework.rpcfx.demo.api.UserService;
import huangzihao.homework.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    @Autowired
    RpcfxInvoker rpcfxInvoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) { return  rpcfxInvoker.invoke(request);}

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) { return new RpcfxInvoker(resolver);}

    @Bean
    public RpcfxResolver creatResolve() { return new DemoResolver(); }

    @Bean(name="huangzihao.homework.rpcfx.demo.api.UserService")
    public UserService createUserService() { return new UserServiceImpl();}

    @Bean(name="huangzihao.homework.rpcfx.demo.api.OrderService")
    public OrderService createOrderService() { return new OrderServiceImpl();}
}
