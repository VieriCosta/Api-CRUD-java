package com.example.apitest.service;

import com.example.apitest.dto.UpdateTodoRequest;
import com.example.apitest.exception.NotFoundException;
import com.example.apitest.model.Todo;
import com.example.apitest.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> listAll() {
        return repository.findAll();
    }

    public Todo getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Todo id " + id + " não encontrado"));
    }

    @Transactional
    public Todo create(String title) {
        Todo todo = new Todo(title);
        return repository.save(todo);
    }

    @Transactional
    public Todo update(Long id, UpdateTodoRequest req) {
        Todo todo = getById(id);
        if (req.getTitle() != null) {
            todo.setTitle(req.getTitle());
        }
        if (req.getDone() != null) {
            todo.setDone(req.getDone());
        }
        return repository.save(todo);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Todo id " + id + " não encontrado");
        }
        repository.deleteById(id);
    }
}