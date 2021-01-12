package huangzihao.homework.activeMqDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 *
 * @author huangzihao
 *
 */

@SpringBootApplication
@EnableJms //启动消息队列
public class ActiveMqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqDemoApplication.class, args);
	}

}
