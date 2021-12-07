package com.study.hard.config.RedisConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
*  RedisAutoConfiguration 类中，可以看出 SpringBoot 对 Redis 做自动化配置的时候，在容器中注入了 redisTemplate 和 stringRedisTemplate
*  RedisTemplate<Object, Object> 表示，key 的类型为 Object，value 的类型为 Object,stringRedisTemplate的 K、V 都是String
*
*  我们往往需要的是 RedisTemplate<String, Object>，key 采用 StringRedisSerializer 序列化方式，value 采用 Jackson2JsonRedisSerializer 序列化方式
*  系统序列化的方式不同会导致 redis 的 key 乱码 ！！！
* */

@Configuration
public class RedisConfig {
    /**
     * RedisTemplate配置 1 :Jackson2JsonRedisSerializer
     */
    /*@Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }*/

    /*
    * 对于 --反序列化非泛型数组对象
    * Jackson2JsonRedisSerializer：序列化带泛型的数据时，会以 map 的结构进行存储，反序列化是不能将 map 解析成对象，会报错
    *
    * GenericJackson2JsonRedisSerializer：序列化时，会保存序列化的对象的包名和类名，反序列化时以这个作为标示就可以反序列化成指定的对象
    *
    *
    * */

    /*
    * RedisTemplate配置 2 :GenericJackson2JsonRedisSerializer
    * */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer gj = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer srs = new StringRedisSerializer();
        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(srs);
        redisTemplate.setValueSerializer(gj);
        //设置hash类型数据序列化规则
        redisTemplate.setHashKeySerializer(srs);
        redisTemplate.setHashValueSerializer(gj);
        redisTemplate.setDefaultSerializer(gj);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
