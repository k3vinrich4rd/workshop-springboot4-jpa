package com.estudos.course.entities;

import com.estudos.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
// OrdemItem representa um item dentro de um pedido.
// Como a relação entre Order e Product possui atributos próprios
// (quantity e price), ela não pode ser um simples @ManyToMany.
// Por isso, OrderItem existe como entidade intermediária.
public class OrderItem implements Serializable {

    @EmbeddedId
    // A chave primária desta entidade é composta.
    // Em vez de um único "id" simples, usamos OrderItemPK,
    // que contém os dois campos que juntos identificam um item:
    // - order   → qual pedido
    // - product → qual produto
    // O @EmbeddedId diz ao JPA para tratar OrderItemPK como chave composta.
    // Sempre instancie OrderItemPK por conta da sua chave ser composta.
    private final OrderItemPK id = new OrderItemPK();

    // Quantidade do produto neste item do pedido.
    private Integer quantity;

    // Preço unitário do produto no momento da compra.
    // Armazenado aqui porque o preço do produto pode mudar futuramente.
    private Double price;

    public OrderItem() {
    }

    // Construtor principal.
    // Recebe os dados do item e os armazena:
    // - order e product são guardados dentro da chave composta (id)
    // - quantity e price são atributos diretos do item
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    // Os getters/setters de Order e Product delegam para dentro da chave composta (id),
    // pois Order e Product não são campos diretos de OrdemItem,
    // eles vivem dentro do objeto OrderItemPK.

    @JsonIgnore // Evita referência cíclica na serialização JSON.
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
