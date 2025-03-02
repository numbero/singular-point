package org.example.singularpointrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作队列模式
 * “能者多劳”消费者组
 */
@Slf4j
@Component
public class WorkQueueExtendConsumer {

    @RabbitListener(containerFactory = "workQueueExtendRabbitListenerContainerFactory",
            queuesToDeclare = @Queue("work-queue-extend"))
    public void processorOne(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error", e);
        }
        log.info("processorOne: {}", message);
    }

    @RabbitListener(containerFactory = "workQueueExtendRabbitListenerContainerFactory",
            queuesToDeclare = @Queue("work-queue-extend"))
    public void processorTwo(String message) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            log.error("error", e);
        }
        log.info("processorTwo: {}", message);
    }

}
