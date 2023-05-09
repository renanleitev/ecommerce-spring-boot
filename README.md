# MyApp

Aplicação criada com uso do [Bootify.io](https://bootify.io/next-steps/).

## Rodando a aplicação

Para rodar a aplicação, basta executar o arquivo principal EcommerceApplication.

    src/main/java/io/bootify/my_app/EcommerceApplication.java

## Swagger

Acesse o Swagger pelo seguinte endereço:

    http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

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

## Links

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
