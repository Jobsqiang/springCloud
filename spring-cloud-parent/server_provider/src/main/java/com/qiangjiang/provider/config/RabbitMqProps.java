package com.qiangjiang.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "iron.notify.rabbitmq")
public class RabbitMqProps {

    private String userQuene;
    private String userExchange;
}


