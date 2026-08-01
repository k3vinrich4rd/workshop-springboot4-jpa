package com.estudos.course.repositories;

import com.estudos.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A interface UserRepository estende JpaRepository, que é uma interface do Spring Data JPA que fornece métodos para realizar operações de CRUD (Create, Read, Update, Delete) em entidades do tipo User. O segundo parâmetro Long indica o tipo do identificador da entidade User.
// Essa interface não precisa de implementação, pois o Spring Data JPA fornece automaticamente a implementação em tempo de execução.
// Tirando a necessidade de colocar a anotação @Repository para indicar que é um componente Spring
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
