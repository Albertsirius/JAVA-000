package huangzihao.homework.activeMqDemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Queue消息的消费者监听处理Bean
 *
 * @author huangzihao
 * @since 2021/1/12
 */

@Component
@Slf4j
public class QueueConsumerListener {

    //监听queue-name的queue，使用的containerFactory为queueListener
    @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
    public void readActiveQueu  (String message){
        //监听到有消息，输出
        log.info("Receive Message in Queue: {}", message);
    }
}
