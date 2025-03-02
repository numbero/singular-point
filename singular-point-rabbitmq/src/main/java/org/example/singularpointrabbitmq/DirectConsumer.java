package org.example.singularpointrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectConsumer {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "direct", type = "direct"),
            key = {"org.example", "org.example.a"},
            value = @Queue
    ))
    public void processorOne(String message) {
        log.info("direct-consumer-one: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "direct", type = "direct"),
            key = {"org.example"},
            value = @Queue
    ))
    public void processorTwo(String message) {
        log.info("direct-consumer-two: {}", message);
    }
}
