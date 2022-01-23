package com.example.mongocrud.controller;

import com.example.mongocrud.model.ToDoDTO;
import com.example.mongocrud.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/todo")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping
    public ToDoDTO getToDo() {
        return new ToDoDTO();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllToDos() {
        List<ToDoDTO> todoList = toDoRepository.findAll();
        if (todoList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(todoList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoDTO> getSingleTodoById(@PathVariable String id) {
        Optional<ToDoDTO> toDoDTOOptional = toDoRepository.findById(id);
        if (toDoDTOOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(toDoDTOOptional.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping()
    public ResponseEntity<?> createTodo(@RequestBody ToDoDTO toDoDTO) {
        try {
            toDoDTO.setCreatedAt(LocalDate.now());
            var todoSaved = toDoRepository.save(toDoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(todoSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody ToDoDTO todo) {
        Optional<ToDoDTO> toDoDTOOptional = toDoRepository.findById(id);
        if (toDoDTOOptional.isPresent()) {
            var todoToSave = toDoDTOOptional.get();
            todoToSave.setDescription(
                    todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());

            todoToSave.setIsCompleted(
                    todo.getIsCompleted() != null ? todo.getIsCompleted() : todoToSave.getIsCompleted());

            todoToSave.setTodo(
                    todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());

            todoToSave.setUpdatedAt(LocalDate.now());
            var savedToDo = toDoRepository.save(todoToSave);
            return ResponseEntity.status(HttpStatus.OK).body(savedToDo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable String id) {
        try {
            toDoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
