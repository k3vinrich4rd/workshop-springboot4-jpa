package com.estudos.course.services.exceptions;

// A classe ResourceNotFound é uma exceção personalizada que estende a classe RuntimeException.
// Ela é usada para indicar que um recurso solicitado não foi encontrado.
// Ao estender RuntimeException, a exceção é uma exceção não verificada (unchecked exception),
// o que significa que não é necessário capturá-la ou declará-la explicitamente no código.
// Isso permite que a exceção seja lançada em qualquer ponto do código sem a necessidade de
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }

}
