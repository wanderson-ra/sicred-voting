# Sicred Voting



## Informações Técnicas
* O sistema foi criado usando **Java** (openjdk versão **"11.0.8" 2020-07-14**).
* **Spring Boot** foi usado
* **Mongo DB** foi usado (instância do mongo atlas para facilitar os testes sem ter a ncessidade de subir instância local).
* **Feign Client** foi usado
* **Spring Data Mongo DB** foi usado
* O sistema foi desenvolvido com **[TDD](https://pt.wikipedia.org/wiki/Test_Driven_Development)**
* O sistema foi desenvolvido usando **[Clean Architecture](https://stackoverflow.com/tags/clean-architecture/info)**
* O sistema usa **[Lombok](https://projectlombok.org/)**. Para o desenvolvimento é necessário instalar o plug-in no IDE (caso contrário, haverá erros de compilação).
* O projeto está disponível na plataforma (Front End) **[Heroku](https://challenge-acc-webapp.herokuapp.com/)**.
* **Swagger** Foi usado para documentar a API. Para acessar, use o endereço: https://challenger-acc.herokuapp.com/swagger-ui.html
* Mais de 80% de cobertura de testes de unidade.



#### Instalar dependências e executar testes (necessita maven instalado)

maven:

```
 mvn clean install
```

#### Executar projeto local via linha de comando (Na pasta raiz) com instância do mongo atlas (necessita maven instalado)

porta default: 8080
</br>
</br>
maven:

```
mvn spring-boot:run
```




