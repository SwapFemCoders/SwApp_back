package com.swapp.swapp.dto.response;

public record UserBasicResponseDTO(

    Integer id,
    String userName,
    String location,
    byte[] picture
) {
}
