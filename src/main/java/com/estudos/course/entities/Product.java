package com.estudos.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //Set porque não pode ter repetição de categorias, e o Set é uma coleção que não permite elementos duplicados.
    //Coleção instanciada como HashSet, que é uma implementação concreta da interface Set, que armazena os elementos em uma tabela hash, permitindo acesso rápido aos elementos.

    @ManyToMany
    //A anotação @JoinTable é usada para definir a tabela de junção que será usada para mapear a relação ManyToMany entre Product e Category.
    //A tabela de junção é uma tabela intermediária que contém as chaves estrangeiras das duas entidades envolvidas na relação ManyToMany.
    //A anotação @JoinTable permite que você configure o nome da tabela de junção e os nomes das colunas que representam as chaves estrangeiras das entidades envolvidas na relação.
    //joinColumns = @JoinColumn(name = "product_id") define a coluna que representa a chave estrangeira da entidade Product na tabela de junção.
    //inverseJoinColumns = @JoinColumn(name = "category_id") define a coluna que representa a chave estrangeira da entidade Category na tabela de junção.
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn (name = "product_id"),
            inverseJoinColumns = @JoinColumn (name = "category_id")

    )
    private Set<Category> categories = new HashSet<>();

    //Não se coloca o setCategories porque não se quer que a lista de categorias
    // seja substituída, mas sim que se adicione ou remova categorias da lista existente.
    // Não se coloca a coleção de categorias no construtor porque a lista de categorias
    // é inicializada como um HashSet vazio, e não se quer que a lista seja substituída por uma nova lista ao criar um novo produto.
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Product() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
