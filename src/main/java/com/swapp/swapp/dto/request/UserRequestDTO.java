package com.swapp.swapp.dto.request;

import jakarta.persistence.Column;

public record UserRequestDTO(
    Integer id,
    String name,
    String lastName,
    String userName,
    String password,
    String email,
    String location,
    byte[] picture
) {

}
