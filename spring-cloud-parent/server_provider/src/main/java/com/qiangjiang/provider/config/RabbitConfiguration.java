package com.qiangjiang.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Autowired
    private RabbitMqProps rabbitMqProps;

    @Bean
    public Queue userQueue(AmqpAdmin amqpAdmin){
        String queueName = rabbitMqProps.getUserQuene();
        Queue queue = new Queue(queueName,true);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue userQueueA(AmqpAdmin amqpAdmin){
        String queueName = "dev_user_queue_a";
        Queue queue = new Queue(queueName,true);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public DirectExchange userExchange(AmqpAdmin amqpAdmin){
        String exchangeName = rabbitMqProps.getUserExchange();
        DirectExchange exchange = new DirectExchange(exchangeName);
//        exchange.setDelayed(true);
        amqpAdmin.declareExchange(exchange);
        return exchange;
    }

    /**
     * 绑定队列
     * @param amqpAdmin
     * @return
     */
    @Bean
    public Binding userBinding(AmqpAdmin amqpAdmin){
        return BindingBuilder.bind(userQueue(amqpAdmin)).to(userExchange(amqpAdmin)).with("dev_user_key");
    }

    /**
     * 绑定队列a
     * @param amqpAdmin
     * @return
     */
    @Bean
    public Binding userBindingA(AmqpAdmin amqpAdmin){
        return BindingBuilder.bind(userQueueA(amqpAdmin)).to(userExchange(amqpAdmin)).with("dev_user_key");
    }
}
