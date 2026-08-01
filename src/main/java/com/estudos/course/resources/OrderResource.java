package com.estudos.course.resources;


import com.estudos.course.entities.Order;
import com.estudos.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    //Implementação para findAll
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //PathVariable é uma anotação do Spring que indica que o valor do parâmetro
    // id será obtido a partir da variável de caminho da URL. Ou seja,
    // quando uma requisição for feita para /Orders/{id},
    // o valor do id será extraído da URL e passado como argumento para o método findById.
    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
