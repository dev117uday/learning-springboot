package com.example.mongocrud.exception;

public class TodoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TodoException(String message) {
		super(message);
	}

	public static String NotFoundException(String id) {
		return "todo with id : " + id + " not found";
	}

	public static String ToDoAlreadyExists(String id) {
		return "todo with id : " + id + " already exists";
	}

}
