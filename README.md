# API Test (Java + Spring Boot)

API REST simples de *To-Do* para você praticar **testes** (unitário, de camada web e integração).

## Requisitos
- JDK 17+
- Maven 3.9+
- (Opcional) IntelliJ IDEA Community

## Rodar a API
```bash
mvn spring-boot:run
```
API sobe em `http://localhost:8080`.

## Endpoints principais
- `GET /api/todos` – listar
- `GET /api/todos/{id}` – buscar por id
- `POST /api/todos` – criar (body: `{ "title": "Estudar Java" }`)
- `PUT /api/todos/{id}` – atualizar (body opcional: `{ "title": "...", "done": true }`)
- `DELETE /api/todos/{id}` – remover

### Exemplos com curl
```bash
curl -X POST http://localhost:8080/api/todos -H "Content-Type: application/json" -d '{"title":"Estudar Java"}'
curl http://localhost:8080/api/todos
curl http://localhost:8080/api/todos/1
curl -X PUT http://localhost:8080/api/todos/1 -H "Content-Type: application/json" -d '{"done":true}'
curl -X DELETE http://localhost:8080/api/todos/1
```

## Rodar os testes
```bash
mvn test
```

Tipos de teste inclusos:
- **Unitário** do service com Mockito
- **Teste de camada web** com `@WebMvcTest` e `MockMvc`
- **Integração** com `@SpringBootTest` usando H2 em memória

## Postman
Importe o arquivo `postman/Api Test.postman_collection.json` para testar os endpoints.