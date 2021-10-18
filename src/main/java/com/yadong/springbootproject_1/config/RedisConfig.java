package com.yadong.springbootproject_1.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
//
//    public static final String DECOLLATOR = ":";
//
//    public static final String APP_PREFIX = "redis";
//
//    public static final String CACHE_NAMES_PREFIX = APP_PREFIX + DECOLLATOR + "cacheNames";
//
//    public static final String CACHE_NAME_FOREVER = CACHE_NAMES_PREFIX + "forever";
//
//    public static final String CACHE_NAME_MINUTES_10 = CACHE_NAMES_PREFIX + "minutes-10";
//
//    public static final String CACHE_NAME_MINUTES_30 = CACHE_NAMES_PREFIX + "minutes-30";
//
//    public static final String CACHE_NAME_HOURS_01 = CACHE_NAMES_PREFIX + "hours-1";
//
//    public static final String CACHE_NAME_HOURS_24 = CACHE_NAMES_PREFIX + "hours-24";
//
//    public static final String CACHE_NAME_HOURS_12 = CACHE_NAMES_PREFIX + "hours-12";
//
//    public static final String CACHE_NAME_DAYS_30 = CACHE_NAMES_PREFIX + "days-30";
//

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.setValueSerializer(stringRedisSerializer);
//        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
