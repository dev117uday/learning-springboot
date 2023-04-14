package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private final UUID id;

    @NotNull(message = "username should not be null")
    private final String name;

    @Email
    private String email;

    @Min(0)
    @Max(100)
    private int age;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
