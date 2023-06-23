# Ecommerce Spring Boot

Aplicação criada com uso do [Bootify.io](https://bootify.io/next-steps/).

## Banco de Dados (MySQL)

Trocar o nome e a senha do MySQL no arquivo application.yml (default: root/17091997)

    username: ${JDBC_DATABASE_USERNAME:<<NOME_USUÁRIO>>}
    password: ${JDBC_DATABASE_PASSWORD:<<SENHA>>}

Obs: O banco de dados deve estar rodando antes de iniciar a aplicação Spring Boot

## Rodando a aplicação

Para rodar a aplicação, basta executar o arquivo principal EcommerceApplication.java

    src/main/java/io/bootify/my_app/EcommerceApplication.java

## Swagger

Para consultar as rotas e os modelos das entidades, basta navegar até o endereço

    http://localhost:8080/swagger-ui/index.html

Para modificar as rotas disponíveis no Swagger, alterar no arquivo application.yml

    springdoc:
        pathsToMatch: /**

## Conta de Administrador e de Manager

Modificar no arquivo application.properties

    ADMIN_EMAIL=adminroot@email.com
    MANAGER_EMAIL=manager@email.com

## Links

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
