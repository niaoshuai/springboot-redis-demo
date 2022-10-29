package ren.shuaipeng.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

@Configuration
public class AppConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(REPLICA_PREFERRED)
                .build();
//        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("127.0.0.1", 6379);

        RedisStaticMasterReplicaConfiguration serverConfig =
                new RedisStaticMasterReplicaConfiguration("127.0.0.1", 6379);
        serverConfig.setDatabase(0);
        serverConfig.setPassword("123456");

        serverConfig.addNode("127.0.0.1",6380);
        serverConfig.addNode("127.0.0.1",6381);
        serverConfig.addNode("127.0.0.1",6382);
        System.out.println("serverConfig = " + serverConfig);
        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }
}
