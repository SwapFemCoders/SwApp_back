package com.swapp.swapp.dto.request;



public record UserRequestDTO(
    Integer id,
    String name,
    String lastName,
    String userName,
    String password,
    String email,
    String location
) {

}
