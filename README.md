API Test (Java + Spring Boot)
API REST simples de To-Do, ideal para prática de testes unitários, de camada web e de integração.

🚀 Requisitos
JDK 17+
Maven 3.9+
(Opcional) IntelliJ IDEA Community ou outra IDE de sua preferência

⚡ Como rodar a API
No terminal, dentro do diretório do projeto:

bash
Copiar
Editar
mvn spring-boot:run
A API será iniciada em:

arduino
Copiar
Editar
http://localhost:8080
🔗 Endpoints principais
Método	Endpoint	Descrição	Body
GET	/api/todos	Listar todos os To-Dos	-
GET	/api/todos/{id}	Buscar To-Do por ID	-
POST	/api/todos	Criar novo To-Do	{ "title": "Estudar Java" }
PUT	/api/todos/{id}	Atualizar To-Do	{ "title": "...", "done": true } (opcional)
DELETE	/api/todos/{id}	Remover To-Do	-

💻 Exemplos com curl
Criar um To-Do:

bash
Copiar
Editar
curl -X POST http://localhost:8080/api/todos \
-H "Content-Type: application/json" \
-d '{"title":"Estudar Java"}'
Listar To-Dos:

bash
Copiar
Editar
curl http://localhost:8080/api/todos
Buscar por ID:

bash
Copiar
Editar
curl http://localhost:8080/api/todos/1
Atualizar To-Do:

bash
Copiar
Editar
curl -X PUT http://localhost:8080/api/todos/1 \
-H "Content-Type: application/json" \
-d '{"done":true}'
Deletar To-Do:

bash
Copiar
Editar
curl -X DELETE http://localhost:8080/api/todos/1
🧪 Rodar os testes
bash
Copiar
Editar
mvn test
Tipos de testes inclusos:

Unitário: Service com Mockito

Camada web: @WebMvcTest e MockMvc

Integração: @SpringBootTest usando H2 em memória

📂 Postman
Para testar os endpoints no Postman, importe:

pgsql
Copiar
Editar
postman/Api Test.postman_collection.json
✨ Dicas extras (opcional)
A API já vem configurada com JWT para autenticação (pasta security)

Estrutura do projeto organizada por pacotes: controller, service, repository, model, dto, exception

