package org.iesdm.thymeleaf_avanzados.controller;

import org.iesdm.thymeleaf_avanzados.DAO.UserDAO;
import org.iesdm.thymeleaf_avanzados.model.User;
import org.iesdm.thymeleaf_avanzados.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/registro")
public class UserController {

    @GetMapping("/registro")
    private String recogerDatosUsuario(Model model , @ModelAttribute Usuario usuario){


        model.addAttribute("Datos" , usuario);

        return "/registro";
    }

    @PostMapping("/registro")
    private String controlRegistro(Model model , @ModelAttribute Usuario usuario){


        return "/registro";
    }



    //Para el login :




}
