package org.iesdm.thymeleaf_avanzados.service;

import org.iesdm.thymeleaf_avanzados.DAO.UserDAO;
import org.iesdm.thymeleaf_avanzados.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {

        this.userDAO = userDAO;

    }


    public User find(String username){

       return userDAO.findByUsername(username);
    }

}
