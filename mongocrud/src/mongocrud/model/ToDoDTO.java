package com.example.mongocrud.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "learn")
public class ToDoDTO {
	
	@Id
	private String id;
	
	@NotNull(message = "todo cannot be null")
	private String todo;
	private String description;
	private Boolean isCompleted;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
