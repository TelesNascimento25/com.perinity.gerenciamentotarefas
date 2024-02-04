# com.perinity.gerenciamentotarefas

Esse projeto usa o Quarkus, the Supersonic Subatomic Java Framework.

Para rodar a aplicação você deve:
```maven build
goals: quarkus:dev
```

# Swagger-ui

Foi disponibilizado o Swagger para os endpoints:

http://localhost:suaPorta/q/swagger-ui

# Necessidade de adição EndPoint Departamentos (post/departamentos)

Devido as circunstâncias fornecidas senti a necessidade de ter um post/departamentos

É necessário que primeiro adicione os departamentos

no body a requisição deve ter: 

{
    "titulo": "Desenvolvimento"
}

para em seguida poder adicionar uma pessoa.

#Postman Testes

Disponibilizei numa pasta separada os testes que fiz no postman para adicionar os endpoints.
está na pasta Postman dentro do projeto.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

You can then execute your native executable with: `./target/com.perinity.gerenciamentotarefas-1.0.0-SNAPSHOT-runner`

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

