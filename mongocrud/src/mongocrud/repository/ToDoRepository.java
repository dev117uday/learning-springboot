package com.example.mongocrud.repository;

import java.util.Optional;

import com.example.mongocrud.model.ToDoDTO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDoDTO, String> {

	@Query("{ 'todo' : ?0 }")
	Optional<ToDoDTO> findByTodo(String todo);

}
