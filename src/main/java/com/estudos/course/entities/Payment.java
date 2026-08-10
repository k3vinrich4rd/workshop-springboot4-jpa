package com.estudos.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;


    // A anotação @OneToOne indica que a relação entre Payment e Order é de um para um,
    // ou seja, cada pagamento está associado a um único pedido.
    // A anotação @MapsId é usada para indicar que a chave primária da entidade Payment é a mesma chave primária da entidade Order.
    // Isso significa que o campo id da entidade Payment será mapeado para o campo id da entidade
    // Order, estabelecendo uma relação de compartilhamento de chave primária entre as duas entidades.
    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment() {
    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
