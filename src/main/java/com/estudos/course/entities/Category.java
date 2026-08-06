package com.estudos.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relação ManyToMany: uma Category pode estar associada a vários Product,
    // e um Product pode estar associado a várias Category.

    // mappedBy = "categories" indica que esta classe não é a dona da relação.
    // O atributo "categories" existe na classe Product e é nela que o relacionamento
    // é configurado como lado proprietário (owning side).

    // Nesta classe Category, o JPA apenas espelha o relacionamento.
    // Isso significa que a tabela de junção é controlada pela classe Product.

    // Como a relação aparece nos dois lados (Product e Category), ela é bidirecional.
    @JsonIgnore // Evita referência circular durante a serialização para JSON.
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient // A anotação @Transient é usada para indicar que o campo products
    // não deve ser persistido no banco de dados. Isso significa que o JPA não
    // irá criar uma coluna correspondente a esse campo na tabela de categorias.
    // O motivo para isso é que a relação entre categorias e produtos é mapeada
    // na classe Product, e não na classe Category. Portanto, não é necessário armazenar a lista de produtos
    // em cada categoria no banco de dados, pois essa informação já está disponível na tabela de produtos.
    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
