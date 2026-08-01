package com.estudos.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // A anotação @JsonFormat é usada para especificar o formato de serialização e desserialização do campo moment.
    // O atributo pattern define o padrão de formatação da data e hora, que neste caso é "yyyy-MM-dd'T'HH:mm:ss'Z'".
    // O atributo timezone define o fuso horário a ser usado na formatação, que neste caso é "GMT" (Greenwich Mean Time).
    // Isso garante que o campo moment seja corretamente convertido para JSON e vice-versa, mantendo o formato e o fuso horário desejados.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;


    // A anotação @ManyToOne indica que a relação entre Order e User é de muitos para um, ou seja, muitos pedidos podem estar associados a um único usuário.
    // A anotação @JoinColumn é usada para especificar a coluna que será usada
    // como chave estrangeira na tabela de pedidos (tb_order) para referenciar a tabela de usuários (tb

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    public Order(Long id, Instant moment, User client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
