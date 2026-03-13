package com.swapp.swapp.dto.request;

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
