package com.estudos.course.entities.pk;

import com.estudos.course.entities.Order;
import com.estudos.course.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
// Indica que esta classe representa uma chave composta reutilizável,
// ou seja, um conjunto de campos que, juntos, identificam unicamente
// um registro.
//
// Ela não é uma entidade independente e não possui ciclo de vida próprio.
// Seu papel é ser incorporada em outra entidade, normalmente com @EmbeddedId.
//
// Neste caso, a chave é formada por:
// - order  -> pedido ao qual o item pertence
// - product -> produto associado ao item
//
// Isso significa que um OrderItem é identificado pela combinação
// do pedido + produto.
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    // Parte da chave composta que referencia o pedido.
    // A coluna "order_id" será usada como chave estrangeira
    // para apontar para a entidade Order.
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    // Parte da chave composta que referencia o produto.
    // A coluna "product_id" será usada como chave estrangeira
    // para apontar para a entidade Product.
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(order);
        result = 31 * result + Objects.hashCode(product);
        return result;
    }
}
