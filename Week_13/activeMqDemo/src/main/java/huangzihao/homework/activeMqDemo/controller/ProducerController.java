package huangzihao.homework.activeMqDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 生产者通过Restful请求产生消息
 *
 * @author huangzihao
 * @since 2021/1/12
 */

@RestController
@Slf4j
public class ProducerController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    //queue的请求是，发送QUEUE的消息，请求体为消息内容
    @PostMapping("/queue/test")
    public String sendQueue(@RequestBody final String str){
        log.info("Produce Message: {} and send to queue", str);
        sendMessage(queue, str);
        return "Success";
    }

    //topic的请求时，发送Topic的消息，请求体为消息内容
    @PostMapping("/topic/test")
    public String sendTopic(@RequestBody final String str){
        log.info("Produce Message for topic: {}", str);
        sendMessage(topic, str);
        return "Success";
    }

    private void sendMessage(final Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
