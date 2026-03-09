package com.swapp.swapp.dto.request;

import jakarta.persistence.Column;

public record UserRequestDTO(
    Integer id,
    String name,
    String lastName,
    String userName,
    String email,
    String password,
    String location
) {

}
