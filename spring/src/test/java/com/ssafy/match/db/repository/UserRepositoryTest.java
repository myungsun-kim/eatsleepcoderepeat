package com.ssafy.match.db.repository;

import com.ssafy.match.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    UserRepository userRepository;

    @Test
    public void crud(){
        User user = new User();
        user.setId(1);
        user.setEmail("qjawlsqjacks@naver.com");
        user.setPassword("123");
        user.setNickname("bjp");
        userRepository.save(user);
    }
}