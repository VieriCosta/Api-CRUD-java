package com.example.apitest.service;

import com.example.apitest.dto.UpdateTodoRequest;
import com.example.apitest.exception.NotFoundException;
import com.example.apitest.model.Todo;
import com.example.apitest.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private TodoRepository repository;
    private TodoService service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(TodoRepository.class);
        service = new TodoService(repository);
    }

    @Test
    void createDeveSalvarComTitle() {
        when(repository.save(any(Todo.class))).thenAnswer(inv -> {
            Todo t = inv.getArgument(0);
            t.setId(1L);
            return t;
        });

        Todo saved = service.create("Estudar JUnit");
        assertNotNull(saved.getId());
        assertEquals("Estudar JUnit", saved.getTitle());

        ArgumentCaptor<Todo> captor = ArgumentCaptor.forClass(Todo.class);
        verify(repository).save(captor.capture());
        assertEquals("Estudar JUnit", captor.getValue().getTitle());
    }

    @Test
    void getByIdQuandoNaoExisteLancaNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void updateDeveAlterarCampos() {
        Todo existing = new Todo("Antigo");
        existing.setId(5L);
        when(repository.findById(5L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Todo.class))).thenAnswer(inv -> inv.getArgument(0));

        UpdateTodoRequest req = new UpdateTodoRequest();
        req.setTitle("Novo");
        req.setDone(true);

        Todo updated = service.update(5L, req);
        assertEquals("Novo", updated.getTitle());
        assertTrue(updated.isDone());
    }
}