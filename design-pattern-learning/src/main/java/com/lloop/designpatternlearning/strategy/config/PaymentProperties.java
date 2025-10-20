package com.lloop.designpatternlearning.strategy.config;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @Author lloop
 * @Create 2025/10/20 20:43
 */
@Getter
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = PaymentProperties.PREFIX)
public class PaymentProperties {
    /**
     * 声明模块属性前缀，建议模块根包路径
     */
    public static final String PREFIX = "com.lloop.designpatternlearning.strategy";

    private Map<String, String> types;

}
