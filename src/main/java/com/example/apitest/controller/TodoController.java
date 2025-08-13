package com.example.apitest.controller;

import com.example.apitest.dto.CreateTodoRequest;
import com.example.apitest.dto.UpdateTodoRequest;
import com.example.apitest.model.Todo;
import com.example.apitest.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // Listar todos sem filtro (sem paginação)
    @GetMapping("/all")
    public List<Todo> listAll() {
        return service.listAll();
    }

    // Listagem com filtros e paginação
    @GetMapping
    public Page<Todo> list(
            @RequestParam(required = false) Boolean done,
            @RequestParam(required = false) String title,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return service.search(done, title, pageable);
    }

    @GetMapping("/{id}")
    public Todo get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody CreateTodoRequest request) {
        Todo created = service.create(request.getTitle());
        return ResponseEntity.created(URI.create("/api/todos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
