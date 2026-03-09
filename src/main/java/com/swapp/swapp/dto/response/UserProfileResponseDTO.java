package com.swapp.swapp.dto.response;

public record UserProfileResponseDTO(
    Integer id,
    String name,
    String lastName,
    String userName,
    String email,
    String location,
    byte[] picture
) {

}
