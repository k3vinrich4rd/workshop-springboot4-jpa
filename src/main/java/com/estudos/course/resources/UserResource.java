package com.estudos.course.resources;


import com.estudos.course.entities.User;
import com.estudos.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    //Implementação para findAll
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //PathVariable é uma anotação do Spring que indica que o valor do parâmetro
    // id será obtido a partir da variável de caminho da URL. Ou seja,
    // quando uma requisição for feita para /users/{id},
    // o valor do id será extraído da URL e passado como argumento para o método findById.
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
