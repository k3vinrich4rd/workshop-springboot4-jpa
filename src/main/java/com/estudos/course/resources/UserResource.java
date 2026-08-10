package com.estudos.course.resources;


import com.estudos.course.entities.User;
import com.estudos.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    // O método save é responsável por salvar um novo usuário no banco de dados.
    // Ele recebe um objeto User como parâmetro, que é enviado no corpo da requisição (request body) em formato JSON.
    // A anotação @RequestBody indica que o objeto User será desserializado a partir do corpo da requisição.
    // O método chama o serviço UserService para salvar o usuário e retorna uma resposta HTTP com o status 200 (OK)
    //  e o objeto User salvo no corpo da resposta.
    // O ResponseEntity é uma classe do Spring que representa a resposta HTTP completa
    // , permitindo definir o status, cabeçalhos e corpo da resposta.
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User obj = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User entity = service.update(id, user);
        return ResponseEntity.ok().body(entity);
    }

}
