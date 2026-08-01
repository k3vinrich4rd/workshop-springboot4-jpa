package com.estudos.course.config;

import com.estudos.course.entities.User;
import com.estudos.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

// A anotação @Configuration indica que a classe é uma classe de configuração do Spring, ou seja, ela contém definições de beans e configurações específicas para o aplicativo.
// A anotação @Profile("test") indica que essa configuração será aplicada apenas quando o perfil "test"
// estiver ativo. Isso serve para definir configurações específicas para diferentes ambientes, como desenvolvimento
@Configuration
@Profile("test")

//CommandLineRunner é uma interface do Spring Boot que permite executar código específico após a inicialização do aplicativo.
// A classe TestConfig implementa essa interface, o que significa que o método run será executado automaticamente quando o aplicativo for iniciado.
public class TestConfig  implements CommandLineRunner {

    // A anotação @Autowired é usada para injetar automaticamente a dependência do UserRepository na classe TestConfig.
    // Isso significa que o Spring irá fornecer uma instância do UserRepository para ser usada dentro da classe TestConfig,
    // permitindo que você acesse os métodos de persistência de dados relacionados à entidade User.
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
