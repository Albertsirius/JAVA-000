package huangzihao.homework.activeMqDemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActivMQ配置类
 * @author huangzihao
 * @since 2021/1/12
 */
@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.queue-name")
    private String queueName;

    @Value("${spring.activemq.topic")
    private String topic;

    @Bean
    @ConfigurationProperties(prefix = "spring.activmq")
    public ConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(){
        return new JmsMessagingTemplate(connectionFactory());
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topic);
    }

    //Queue模式
    @Bean("queueListner")
    public JmsListenerContainerFactory queueJmsListenerContainerFactory(){
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = genJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setPubSubDomain(false);
        return simpleJmsListenerContainerFactory;
    }

    //Topic模式
    public JmsListenerContainerFactory topicJmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = genJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setPubSubDomain(true);
        return simpleJmsListenerContainerFactory;
    }

    private SimpleJmsListenerContainerFactory genJmsListenerContainerFactory() {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory());
        return simpleJmsListenerContainerFactory;
    }

}
