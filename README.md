# Ecommerce Spring Boot

Aplicação criada com uso do [Bootify.io](https://bootify.io/next-steps/).

## Banco de Dados (MySQL)

Trocar o nome e a senha do MySQL no arquivo application.yml (default: root/17091997):

    username: ${JDBC_DATABASE_USERNAME:<<NOME_USUÁRIO>>}
    password: ${JDBC_DATABASE_PASSWORD:<<SENHA>>}

Obs: O banco de dados deve estar rodando antes de iniciar a aplicação Spring Boot.

## Rodando a aplicação

Para rodar a aplicação, basta executar o arquivo principal EcommerceApplication.java.

    src/main/java/io/bootify/my_app/EcommerceApplication.java

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

## Erro ao realizar requisições REST

Caso ocorra algum erro durante as requisições, colocar nos models/domains as seguintes anotações JSON:

domain > User.java

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference
    private Set<Shopping> shoppings;

domain > Product.java

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference
    private Set<Shopping> shoppings;

domain > Shopping.java

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private Product product;

## Links

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
