## Por que OrderItem existe?

A relação entre `Order` e `Product` é muitos-para-muitos,
mas possui atributos próprios (`quantity` e `price`).
Por isso não pode ser um simples `@ManyToMany` — precisa de uma entidade intermediária.

## Por que a chave é composta?

Um item de pedido é único pela combinação:
- **qual pedido** (`order_id`)
- **qual produto** (`product_id`)

Exemplo no banco:

| order_id | product_id | quantity | price |
|----------|------------|----------|-------|
| 1        | 10         | 2        | 99.9  |
| 1        | 11         | 1        | 49.9  |
| 2        | 10         | 3        | 99.9  |

## Papel das classes

| Classe         | Papel                                                         |
|----------------|---------------------------------------------------------------|
| `Order`        | Cabeçalho do pedido                                           |
| `Product`      | Produto do catálogo                                           |
| `OrderItem`    | Linha do pedido (entidade intermediária com dados próprios)   |
| `OrderItemPK`  | Chave composta: identifica o item pelo par order + product    |

## Delegação nos getters/setters

`Order` e `Product` não são campos diretos de `OrderItem`.
Eles vivem dentro de `OrderItemPK (id)`.
Por isso os getters/setters delegam:

```java
// Para acessar o pedido:
orderItem.getOrder()     →  id.getOrder()

// Para acessar o produto:
orderItem.getProduct()   →  id.getProduct()