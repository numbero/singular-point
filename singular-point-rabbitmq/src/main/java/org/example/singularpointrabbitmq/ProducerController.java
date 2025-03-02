package org.example.singularpointrabbitmq;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rabbitmq")
public class ProducerController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 简单模式
     * @param message
     */
    @PostMapping("/send")
    public void send(@RequestParam String message) {
        String routingKey = "hello";
        rabbitTemplate.convertAndSend(routingKey, message);
    }

    /**
     * 工作队列模式
     * @param message
     */
    @PostMapping("/work-queue/send")
    public void sendWorkQueue(@RequestParam String message) {
        // 工作队列 “雨露均沾”
        //String routingKey = "work-queue";
        // 工作队列 “能者多劳”
        String routingKey = "work-queue-extend";
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(routingKey, String.format("Message [%d]: %s", i, message));
        }
    }

    /**
     * 广播模式
     * @param message
     */
    @PostMapping("/fanout/send")
    public void sendFanout(@RequestParam String message) {
        String exchange = "fanout";
        rabbitTemplate.convertAndSend(exchange, "", message);
    }

    /**
     * 路由模式
     * @param message
     */
    @PostMapping("/direct/send")
    public void sendDirect(@RequestParam String routingKey,
                           @RequestParam String message) {
        String exchange = "direct";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * 动态路由模式
     * @param message
     */
    @PostMapping("/topic/send")
    public void sendTopic(@RequestParam String routingKey,
                          @RequestParam String message) {
        String exchange = "topic";
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
