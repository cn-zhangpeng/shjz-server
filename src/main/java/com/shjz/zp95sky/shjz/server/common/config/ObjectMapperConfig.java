package com.shjz.zp95sky.shjz.server.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 解决 LocalTime、LocalDateTime、LocalDate 序列化反序列化问题
 */
@Configuration
public class ObjectMapperConfig {

    @Value("${spring.jackson.local-date-time-format:yyyy-MM-dd HH:mm:ss}")
    private String localDateTimeFormat;

    @Value("${spring.jackson.local-date-format:yyyy-MM-dd}")
    private  String localDateFormat;

    @Value("${spring.jackson.local-time-format:HH:mm:ss}")
    private  String localTimeFormat;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //序列化
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(localDateFormat)));
        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimeFormat)));

        //反序列化
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDateFormat)));
        javaTimeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimeFormat)));

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

}
