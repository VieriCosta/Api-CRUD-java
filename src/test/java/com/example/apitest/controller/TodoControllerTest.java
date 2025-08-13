package com.example.apitest.controller;

import com.example.apitest.dto.CreateTodoRequest;
import com.example.apitest.model.Todo;
import com.example.apitest.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService service;

    @Test
    void deveListarTodos() throws Exception {
        List<Todo> todos = Arrays.asList(new Todo("A"), new Todo("B"));
        Mockito.when(service.listAll()).thenReturn(todos);

        mockMvc.perform(get("/api/todos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].title", is("A")));
    }

    @Test
    void deveCriarTodo() throws Exception {
        CreateTodoRequest req = new CreateTodoRequest("Aprender testes");
        Todo saved = new Todo("Aprender testes");
        saved.setId(1L);
        Mockito.when(service.create("Aprender testes")).thenReturn(saved);

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/todos/1"))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.title", is("Aprender testes")));
    }

    @Test
    void deveValidarTitleObrigatorio() throws Exception {
        String body = "{}";
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", is("validation_error")));
    }
}