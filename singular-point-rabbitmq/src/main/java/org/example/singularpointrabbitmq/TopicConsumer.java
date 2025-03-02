package org.example.singularpointrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic", type = "topic"),
            key = {"org.example.abc"},
            value = @Queue
    ))
    public void processorOne(String message) {
        log.info("processorOne: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic", type = "topic"),
            key = {"org.example.*"},
            value = @Queue
    ))
    public void processorTwo(String message) {
        log.info("processorTwo: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic", type = "topic"),
            key = {"org.example.#"},
            value = @Queue
    ))
    public void processorThree(String message) {
        log.info("processorThree: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic", type = "topic"),
            key = {"org.#"},
            value = @Queue
    ))
    public void processorFour(String message) {
        log.info("processorFour: {}", message);
    }
}
