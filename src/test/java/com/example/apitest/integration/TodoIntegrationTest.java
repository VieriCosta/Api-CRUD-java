package com.example.apitest.integration;

import com.example.apitest.dto.CreateTodoRequest;
import com.example.apitest.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void fluxoCompletoCriarEBuscar() {
        String base = "http://localhost:" + port + "/api/todos";

        CreateTodoRequest req = new CreateTodoRequest("Integração");
        ResponseEntity<Todo> createResp = rest.postForEntity(base, req, Todo.class);
        assertEquals(HttpStatus.CREATED, createResp.getStatusCode());
        Long id = createResp.getBody().getId();
        assertNotNull(id);

        ResponseEntity<Todo> getResp = rest.getForEntity(base + "/" + id, Todo.class);
        assertEquals(HttpStatus.OK, getResp.getStatusCode());
        assertEquals("Integração", getResp.getBody().getTitle());
    }
}