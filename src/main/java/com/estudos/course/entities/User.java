package com.estudos.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
// Para que a classe seja serializável, ou seja, que seus objetos possam ser convertidos em bytes para serem transmitidos pela rede ou armazenados em arquivos.
public class User implements Serializable {

    // Anotação @serial para indicar que a classe é serializável e para definir um identificador único para a versão da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // A anotação @OneToMany indica que a relação entre User e Order é de um para muitos, ou seja, um usuário pode ter muitos pedidos.
    // O atributo mappedBy é usado para indicar que a relação é bidirecional e que o lado "muitos"
    // da relação (Order) é o responsável por manter a referência ao lado
    // Esse muitos para um é mapeado pelo atributo "client" na classe Order, que é a referência ao usuário associado a cada pedido.
    // Para evitar problemas de serialização e desserialização, como loops infinitos, quando a classe User é convertida para JSON.
    //Lazy loading é uma estratégia de carregamento de dados em que os dados relacionados a uma entidade
    // são carregados apenas quando são realmente necessários, e não no momento em que a entidade principal é carregada.
    // Isso pode melhorar o desempenho da aplicação, evitando consultas desnecessárias ao banco de dados.

    // A anotação @JsonIgnore é usada para ignorar a propriedade "orders" durante a serialização e desserialização do objeto User.
    // Assim quando um usuário é convertido para JSON, a propriedade "orders" não será incluída na representação JSON do usuário.
    // Só os atributos do usuário serão incluídos, evitando a referência circular entre User e Order.
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private final List<Order> orders = new ArrayList<>();

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
