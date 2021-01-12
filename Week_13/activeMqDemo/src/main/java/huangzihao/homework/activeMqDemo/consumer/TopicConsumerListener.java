package huangzihao.homework.activeMqDemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Topic消息的消费者监听处理Bean
 *
 * @author huangzihao
 * @since 2021/1/12
 */

@Component
@Slf4j
public class TopicConsumerListener {

    //监听topic，使用的containerFactory为topicListener
    @JmsListener(destination = "${spring.activemq.topic}", containerFactory = "topic")
    public void readActiveQueu  (String message){
        //监听到有消息，输出
        log.info("Receive Message on Topic: {}", message);
    }
}
