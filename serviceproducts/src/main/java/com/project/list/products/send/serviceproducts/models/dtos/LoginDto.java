package com.project.list.products.send.serviceproducts.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Size(max = 50, min = 0)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
