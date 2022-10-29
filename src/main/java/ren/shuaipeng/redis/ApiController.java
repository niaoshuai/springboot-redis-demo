package ren.shuaipeng.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API
 */
@RequestMapping
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getRedisData")
    public String getRedisData() {
        String count = stringRedisTemplate.opsForValue().get("count");
        return count;
    }

    @PostMapping("/setRedisData")
    public String setRedisData() {
        stringRedisTemplate.opsForValue().increment("count");
        return "OK";
    }
}
