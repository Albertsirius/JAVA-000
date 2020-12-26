package huangzihao.homework.rpcfx.demo.consumer;

import huangzihao.homework.rpcfx.client.Rpcfx;
import huangzihao.homework.rpcfx.demo.api.Order;
import huangzihao.homework.rpcfx.demo.api.OrderService;
import huangzihao.homework.rpcfx.demo.api.User;
import huangzihao.homework.rpcfx.demo.api.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxClientApplication {

    public static void main(String[] args) {

        UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
        User user = userService.findUserById(1);
        System.out.println("find user id = 1 from server: " + user.getName());

        OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));

    }
}
