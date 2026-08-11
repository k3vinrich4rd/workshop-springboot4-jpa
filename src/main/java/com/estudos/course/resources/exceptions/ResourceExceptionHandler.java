package com.estudos.course.resources.exceptions;


import com.estudos.course.services.exceptions.DatabaseException;
import com.estudos.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// A anotação @ControllerAdvice é usada para definir uma classe que irá fornecer
// tratamento global de exceções para todos os controladores (controllers) da aplicação.
// Ela permite capturar exceções lançadas em qualquer ponto do código e tratá-las de forma centralizada,
// evitando a necessidade de duplicar o tratamento de exceções em cada controlador individualmente.
@ControllerAdvice
public class ResourceExceptionHandler {

    // O método resourceNotFound é um manipulador de exceção que será chamado quando uma exceção do tipo ResourceNotFound for lançada.
    // Ele recebe como parâmetros a exceção lançada (e) e o objeto HttpServletRequest (request),
    // que contém informações sobre a requisição HTTP que causou a exceção.
    // O método cria um objeto StandardError, que é uma classe personalizada para representar
    //  informações de erro, e retorna uma resposta HTTP com o status 404
    //  (Not Found) e o objeto StandardError no corpo da resposta.

    // A anotação @ExceptionHandler é usada para indicar que o método resourceNotFound é um manipulador de exceção.
    // Ela informa ao Spring que, quando uma exceção do tipo ResourceNotFound for lançada,
    // o método resourceNotFound deve ser chamado para tratar essa exceção.
    // Isso permite que o Spring redirecione automaticamente a exceção para o método apropriado,
    // evitando a necessidade de capturar e tratar a exceção manualmente em cada ponto do código onde ela pode ocorrer.
    //ResourceNotFound.class é a exceção personalizada que será tratada por esse método.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    // O método database é um manipulador de exceção que será chamado quando uma exceção do tipo DatabaseException for lançada.
    // Ele recebe como parâmetros a exceção lançada (e) e o objeto HttpServletRequest (request), que contém informações sobre a requisição HTTP que causou a exceção.
    // O método cria um objeto StandardError, que é uma classe personalizada para representar informações de erro,
    //  e retorna uma resposta HTTP com o status 400 (Bad Request) e o objeto Standard
    // Error no corpo da resposta.
    @ExceptionHandler
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
