package org.iesdm.thymeleaf_avanzados.DAO;


import org.iesdm.thymeleaf_avanzados.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class userDAOImplSQL implements UserDAO {

    private JdbcTemplate jdbcTemplate;


    //Inyeccion de JDBcTemplate por Contructor:
    public userDAOImplSQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user){

        String sql = """
                insert into user (username,password)
                values (                  ?,       ?);
                
                
                """;

    /*
        jdbcTemplate.update(sql ,
                                cliente.getNombre(),
                                cliente.getApellido1(),
                                cliente.getApellido2(),
                                cliente.getCiudad(),
                                cliente.getCategoria());
    */

        /*Usando el valor del ID :*/
        String[] ids = {"id"};

        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {

            PreparedStatement ps  = con.prepareStatement(sql , ids);

            ps.setString(1 , user.getUsername());
            ps.setString(2 , user.getPassword());


            return ps;

        }, keyholder);

        user.setId(keyholder
                .getKey()
                .longValue());


        return null;
    }

    @Override
    public User findByUsername(String username) {
        String sql = """
                
                select * from user where username = ?
                
                """;


      return jdbcTemplate.queryForObject(sql ,(ResultSet rs , int rowNum) -> User.builder()

                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build()
                ,username);


    }
}
