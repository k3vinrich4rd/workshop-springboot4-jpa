package com.estudos.course.services;

import com.estudos.course.entities.User;
import com.estudos.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// A anotação @Service indica que a classe é um componente de serviço do Spring, ou seja, ela contém a lógica de negócios da aplicação.
// O Spring irá gerenciar essa classe como um bean, permitindo que ela seja injetada em outras classes quando necessário.
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    // O método save é responsável por salvar um novo usuário no banco de dados.
    // Ele recebe um objeto User como parâmetro, que é enviado no corpo da requisição (request body) em formato JSON.
    // A anotação @RequestBody indica que o objeto User será desserializado a partir do corpo da requisição.
    // O método chama o repositório UserRepository para salvar o usuário e retorna o objeto User salvo.

    public User insert(User user) {
        return repository.save(user);
    }
}
