package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
@ConfigurationProperties("config")
@RefreshScope
@ToString
public class Config {
    private String profile;
}
