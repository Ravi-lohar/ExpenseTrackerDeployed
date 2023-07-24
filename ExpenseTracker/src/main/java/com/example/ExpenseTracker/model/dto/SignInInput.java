package com.example.ExpenseTracker.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInInput {
    @Email
    private String email;
    @NotBlank
    private String password;
}
