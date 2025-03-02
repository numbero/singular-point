package org.example.singularpointrabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("workQueueExtendRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(@Autowired ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 确认类型
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 拒绝策略：被消费者拒绝的消息会被重新入队 true
        factory.setDefaultRequeueRejected(true);
        // 预取计数：指消费者从队列中一次可以获取的消息数量
        factory.setPrefetchCount(1);
        return factory;
    }
}
