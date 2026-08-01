package com.estudos.course.repository;

import com.estudos.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// A interface UserRepository estende JpaRepository, que é uma interface do Spring Data JPA que fornece métodos para realizar operações de CRUD (Create, Read, Update, Delete) em entidades do tipo User. O segundo parâmetro Long indica o tipo do identificador da entidade User.
// Essa interface não precisa de implementação, pois o Spring Data JPA fornece automaticamente a implementação em tempo de execução.
public interface UserRepository extends JpaRepository<User, Long> {

}
