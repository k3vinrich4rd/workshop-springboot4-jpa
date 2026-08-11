package com.estudos.course.services;

import com.estudos.course.entities.User;
import com.estudos.course.repositories.UserRepository;
import com.estudos.course.services.exceptions.ResourceNotFoundException;
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
        // O método orElseThrow é usado para lidar com o caso em que o usuário não é encontrado no banco de dados.
        // Se o usuário não for encontrado, ele lança uma exceção ResourceNotFoundException com uma mensagem personalizada.
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // O método save é responsável por salvar um novo usuário no banco de dados.
    // Ele recebe um objeto User como parâmetro, que é enviado no corpo da requisição (request body) em formato JSON.
    // A anotação @RequestBody indica que o objeto User será desserializado a partir do corpo da requisição.
    // O método chama o repositório UserRepository para salvar o usuário e retorna o objeto User salvo.

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User user) {
        //O método getOne é usado para obter uma referência à entidade User
        // com o ID especificado, sem realmente buscar os dados do banco de dados.
        User entity = repository.getOne(id);
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
