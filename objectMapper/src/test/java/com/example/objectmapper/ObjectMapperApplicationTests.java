package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-----------");

        //text Json -> Object
        // Object -> text Json

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("========ObjectMapper is reference to getting Method===========");
        User user = new User("steve", 10, "01020527958");
        String text = objectMapper.writeValueAsString(user);
        System.out.println(text);
        System.out.println("==================== End Of WriteValue =======================");

        System.out.println("====ObjectMapper is reference to default Constructor==========");
        User objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
        System.out.println("===================End Of ReadValue ==========================");

    }

}
