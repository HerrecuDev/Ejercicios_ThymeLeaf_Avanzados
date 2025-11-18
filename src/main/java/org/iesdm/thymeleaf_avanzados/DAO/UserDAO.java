package org.iesdm.thymeleaf_avanzados.DAO;


import org.iesdm.thymeleaf_avanzados.model.User;

public interface UserDAO {


    /*
    * user la password debe venir encriptada
    * @return user con id actualizado
    *
    *
    * */


    User create(User user);



    /*Username unico en bbdd*/
    /*return use con el usuario y password*/

    User findByUsername(String username);

}
