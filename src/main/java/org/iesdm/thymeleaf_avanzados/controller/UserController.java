package org.iesdm.thymeleaf_avanzados.controller;

import org.iesdm.thymeleaf_avanzados.DAO.UserDAO;
import org.iesdm.thymeleaf_avanzados.model.Piramide;
import org.iesdm.thymeleaf_avanzados.model.User;
import org.iesdm.thymeleaf_avanzados.model.Usuario;
import org.iesdm.thymeleaf_avanzados.service.UserService;
import org.iesdm.thymeleaf_avanzados.util.HashUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;

@Controller

@RequestMapping("/registro")
public class UserController {

    private HashUtil hashUtil;
    //DEclaramos el UserService  y su constructor
    private UserService userService;

    public UserController(UserService userService, HashUtil hashUtil) {
        this.userService = userService;
        this.hashUtil = hashUtil;
    }


    //Los Gets preparan una plantilla pra el formulario del html:
    @GetMapping("")
    private String recogerDatosUsuario(Model model , @ModelAttribute Usuario usuario){


        model.addAttribute("usuario" , usuario);

        return "registro";
    }

    @PostMapping("")
    private String controlRegistro(Model model , @ModelAttribute Usuario usuario) throws NoSuchAlgorithmException {

        //Para el login :

        String username = usuario.getUsuario();
        String password = usuario.getPassword();




        if (username.isEmpty()){

            return "registro";

        }else if(password.isEmpty()){


            return "registro";


        } else{

            try{
                User usuarioEncontrado = userService.find(username);

                if (usuarioEncontrado.getPassword().equals(hashUtil.hashPassword(password))) {

                    model.addAttribute("usuario", username);
                    model.addAttribute("password", password);
                    return "acceso";
                } else {


                    return  "registro";
                }

            }catch (DataAccessException error){

                return "registro";
            }


        }
    }


    //Una vez accedemos y aceptamos pasamos a pedir el numero de elementos de la piramide :


    //Genero el Get :

    @GetMapping("/piramide")
    public String recibeAltura(Model model , @ModelAttribute Piramide piramide){

        model.addAttribute("Altura" , piramide);

        return "piramide";

    }

    @PostMapping("/piramide")
    public String reciboNumeroElementos(Model model , @ModelAttribute Piramide piramide){

        int altura = piramide.getAltura();


        if ( altura > 0){


            model.addAttribute("altura" , altura);

            return "pintarPiramide";

        }else{

            model.addAttribute("piramide" , "La altura de la piramide debe ser mayor que 0");
             return "piramide";

        }

    }



}
