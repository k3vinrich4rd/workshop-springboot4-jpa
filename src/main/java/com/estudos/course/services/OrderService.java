package com.estudos.course.services;

import com.estudos.course.entities.Order;
import com.estudos.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// A anotação @Service indica que a classe é um componente de serviço do Spring, ou seja, ela contém a lógica de negócios da aplicação.
// O Spring irá gerenciar essa classe como um bean, permitindo que ela seja injetada em outras classes quando necessário.
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
