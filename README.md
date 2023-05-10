# Ecommerce Spring Boot

Aplicação criada com uso do [Bootify.io](https://bootify.io/next-steps/).

## Banco de Dados (MySQL)

Trocar o nome e a senha do MySQL no arquivo application.yml (default: root/17091997):

    username: ${JDBC_DATABASE_USERNAME:<<NOME_USUÁRIO>>}
    password: ${JDBC_DATABASE_PASSWORD:<<SENHA>>}

## Rodando a aplicação

Para rodar a aplicação, basta executar o arquivo principal MyAppApplication.

    src/main/java/io/bootify/my_app/EcommerceApplication.java

## Swagger

Acesse o Swagger pelo seguinte endereço:

    http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

## Paginação (User e Product)

User:

    http://localhost:8080/users?pageNumber=0&pageSize=2

Product:

    http://localhost:8080/products?pageNumber=0&pageSize=2

TODO: Paginação Shopping

## Modificar os métodos de paginação

controller > HomeController:

User:

    @GetMapping("/users")
    public List<User> getPaginatedUsers(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return userService.filterUsers(pageNumber, pageSize);
    }

Product:

    @GetMapping("/products")
    public List<Product> getPaginatedProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return productService.filterProducts(pageNumber, pageSize);
    }

Obs: Trocar pageNumber e pageSize pelo nome dos métodos desejados 

TODO: Pesquisar outros métodos de paginação

## Erro no Timestamp

Caso ocorra algum erro no dateCreated e no lastUpdated, se não gerar automaticamente o timestamp, substituir nos models de cada entidade/tabela:

dateCreated:

    @CreationTimestamp
    @Column(nullable = true, updatable = false)
    private OffsetDateTime dateCreated;

lastUpdated:

    @UpdateTimestamp
    @Column(nullable = true)
    private OffsetDateTime lastUpdated;

## Erro ao realizar consultas em paginação

Caso ocorra algum erro durante a paginação (Ex: StackOverflowError - Infinite Recursion), colocar nos models/domains as seguintes anotações JSON:

domain > User.java

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Shopping> shoppings;

domain > Product.java

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Shopping> shoppings;

domain > Shopping.java

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

Obs: Comentar esses trechos para desativar as referências a outras entidades, caso queira mostrar entidades puras

TODO: Pesquisar um método melhor para remover essas referências

## Links

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
