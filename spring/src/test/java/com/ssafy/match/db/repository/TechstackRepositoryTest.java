package com.ssafy.match.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.match.db.entity.Techstack;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TechstackRepositoryTest {

    @Autowired
    TechstackRepository techstackRepository;

    @BeforeEach
    void setUp() {
        Techstack techstack1 = new Techstack("Java");
        techstackRepository.save(techstack1);
        Techstack techstack2 = new Techstack("Spring");
        techstackRepository.save(techstack2);
    }

    @Test
    void findAll() {
        List<Techstack> list = techstackRepository.findAll();

        for (Techstack stack: list) {
            System.out.print(stack.getName() + " ");
        }
    }
}