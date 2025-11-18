package org.iesdm.thymeleaf_avanzados;

import lombok.extern.slf4j.Slf4j;


import org.iesdm.thymeleaf_avanzados.DAO.UserDAO;
import org.iesdm.thymeleaf_avanzados.model.User;
import org.iesdm.thymeleaf_avanzados.util.HashUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
@Slf4j
@SpringBootTest
class ThymeLeafAvanzadosApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserDAO userDAO;
    @Autowired
    HashUtil hashUtil;

    @Test
    void createUserAdminTest(){

        String useername = "admin";
        String password = "123456";
        String passwordHashed = null;
        try {
           passwordHashed = hashUtil.hashPassword(password);
           log.info("passwordHashed  = {}" , passwordHashed);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        User user = User.builder()
                .username(useername)
                .password(passwordHashed)
                .build();

        userDAO.create(user);

        Assertions.assertTrue(user.getId() > 0);

    }

}
