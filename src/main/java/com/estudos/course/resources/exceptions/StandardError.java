package com.estudos.course.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {

    // A anotação @JsonFormat é usada para especificar o formato de serialização e desserialização de um campo.
    // O atributo shape define a forma como o campo será representado no JSON, neste caso como uma string.
    // O atributo pattern define o padrão de formatação da data e hora, que neste caso é "yyyy-MM-dd'T'HH:mm:ss'Z'".
    // O atributo timezone define o fuso horário a ser usado na formatação, que neste caso é "GMT" (Greenwich Mean Time).
    // Isso garante que o campo timestamp seja corretamente convertido para JSON e vice-versa, mantendo o formato e o fuso horário desejados.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
