# beers_api_dio

## Sobre 📰

Este é um desafio de projeto realizado na digitalinnovation.one.

O objetivo desta aplicação é desenvolver testes unitários para validar uma API REST de gerenciamento de estoques de cerveja.

------

## Requisitos do Projeto

* Desenvolver o projeto utilizando Spring-boot
* Utilizar os seguintes frameworks para testes unitários em Java: JUnit, Mockito e Hamcrest.
* Desenvolver testes unitários para validação de funcionalides básicas: criação, listagem, consulta por nome e exclusão de cervejas.
* Utilizar a tecnica de TDD em 2 funcionaliades importantes: incremento e decremento do número de cervejas no estoque.

------

## Tecnologias & bibliotecas utilizadas 💻

- Spring-boot
- Java
- H2
- Maven
- Lombok
- Mapstruct
- Junit 5
- Mockito
- Hamcrest

## Requisitos para executar o projeto

- Java 14 ou versões superiores (foi utilizado para esta aplicação o Java 17).
- Maven 3.8.1 ou versões superiores.

## Como baixar e executar o projeto 💡

### BACK-END APLICAÇÃO
```bash

# Primeiramente clone o repositorio
$ git clone https://github.com/Joaquimlagos/beers_api_dio.git


# Agora, verifique se você tem o maven instalado
$ mvn -v

# Agora digite o seguinte comando para aplicação rodar
$ mvn spring-boot:run 

# Para rodar os testes da aplicação execute o seguinte comando:

$ mvn clean test
```
------

### Testar a aplicação

```bash
# Após o programa estar rodando, ele já estara disponível no seguinte endereço:
$ localhost:8080
# para acessar o banco de dados insira esta url:
$ localhost:8080/h2-console
# lembrando que a JDBC URL vai ser informada quando o programa estivar inicializando, fique atendo no console para pegar o endereço

# Para testar as funcionalidades pro programa utilize a segunte url:
$ http://localhost:8080/api/v1/beers

```



