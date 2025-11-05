package com.bootcamp.demo.project_stock_data.config;

import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Configuration
@EnableCaching
public class RedisConfig {
    private RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    RedisConfig(RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }
  
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }
  
  // getter
  public <T> T read(String key, Class<T> clazz) throws JsonProcessingException {
    // Step 1: Read from redis
    String jsonForRead = this.redisTemplate.opsForValue().get(key);
    // Step 2: Serialize String to java object
    if (jsonForRead == null)
      return null;
    return this.objectMapper.readValue(jsonForRead, clazz);
  }

  // setter
  public <T> void write(String key, T value, Duration duration) throws JsonProcessingException {
    String jsonForWrite = this.objectMapper.writeValueAsString(value);
    this.redisTemplate.opsForValue().set(key, jsonForWrite, duration);
  }
  
  
}
