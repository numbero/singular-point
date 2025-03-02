package org.example.singularpointrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作队列模式
 * “雨露均沾”消费者组
 */
@Slf4j
@Component
public class WorkQueueConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work-queue"))
    public void processorOne(String message){
        log.info("processor one: {}", message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work-queue"))
    public void processorTwo(String message){
        log.info("processor two: {}", message);
    }
}
