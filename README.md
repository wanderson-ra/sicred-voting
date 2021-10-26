# Sicred Voting



## Informações Técnicas
* O sistema foi criado usando **Java** (openjdk versão **"11.0.8" 2020-07-14**).
* **Spring Boot** foi usado
* **Mongo DB** foi usado
* **Feign Client** foi usado para fazer integração com o sistema externo.
* **Spring Data Mongo DB** foi usado
* O sistema foi desenvolvido com **[TDD](https://pt.wikipedia.org/wiki/Test_Driven_Development)**
* O sistema foi desenvolvido usando **[Clean Architecture](https://stackoverflow.com/tags/clean-architecture/info)**
* O sistema usa **[Lombok](https://projectlombok.org/)**. Para o desenvolvimento é necessário instalar o plug-in no IDE (caso contrário, haverá erros de compilação).
* **Swagger** Foi usado para documentar a API. Para acessar, use o endereço: https://sicred-voting.herokuapp.com/swagger-ui.html
* Mais de 80% de cobertura de testes de unidade.

## Ferramentas Usadas nos Testes
* Mockito
* Faker


## Ferramentas Usadas para CI/CD e Qualidade
* CircleCI
* Heroku

#### Instalar dependências e executar testes (necessita maven instalado)

maven:

```
 mvn clean install
```

## Modos de Execução


#### 1 - Executar projeto local via linha de comando com docker-compose (Na pasta raiz, necessita docker e docker-compose instalados)

Porta default: 8080

Swagger: http://localhost:8080/swagger-ui.html

Passo 1:

```
docker-compose build
```

Passo 2:

```
docker-compose up 
```


#### 2 - Executar projeto local via linha de comando (Na pasta raiz, necessita maven instalado e instância do mongo rodando)

Porta default: 8000

Swagger: http://localhost:8000/swagger-ui.html


maven:

```
mvn spring-boot:run -Dspring.profiles.active=local
```




