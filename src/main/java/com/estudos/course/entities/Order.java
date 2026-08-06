package com.estudos.course.entities;

import com.estudos.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    // A enumeração OrderStatus é usada para representar o status do pedido,
    // que pode ser um dos valores definidos na enumeração (WAITING_PAYMENT, PAID, SHIPPED, DELIVERED, CANCELED).
    // O campo orderStatus é armazenado como um inteiro no banco de dados, mas é representado como uma enumeração na aplicação.
    private Integer orderStatus;

    // A anotação @ManyToOne indica que a relação entre Order e User é de muitos para um, ou seja, muitos pedidos podem estar associados a um único usuário.
    // A anotação @JoinColumn é usada para especificar a coluna que será usada
    // como chave estrangeira na tabela de pedidos (tb_order) para referenciar a tabela de usuários (tb
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    //items é uma coleção do tipo Set que armazena os itens do pedido.
    // A anotação @OneToMany indica que a relação entre Order e OrderItem
    // é de um para muitos, ou seja, um pedido pode ter muitos itens.
    // id.order é o nome do atributo que faz referência à chave primária
    // composta da entidade OrderItem, que é a associação entre Order e Product.
    @OneToMany(mappedBy = "id.order")
    private final Set<OrderItem> items = new HashSet<>();

    public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
        this.id = id;
        this.moment = moment;
        this.client = client;
        // O método setOrderStatus(orderStatus) é chamado para definir o valor do campo orderStatus com base no valor da enumeração OrderStatus fornecido como parâmetro.
        setOrderStatus(orderStatus);
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


    // O método getOrderStatus() retorna o valor da enumeração OrderStatus correspondente ao código armazenado no campo orderStatus.
    // Ele utiliza o método estático valueOf(int code) da enumeração OrderStatus para converter o código inteiro em um valor da enumeração.
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    // O método setOrderStatus(OrderStatus orderStatus) recebe um valor da enumeração
    //  OrderStatus como parâmetro e armazena o código correspondente no campo orderStatus.
    // Ele utiliza o método getCode() da enumeração OrderStatus para obter o código inteiro
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
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

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
