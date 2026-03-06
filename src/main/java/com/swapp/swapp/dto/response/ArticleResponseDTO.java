package com.swapp.swapp.dto.response;

public record ArticleResponseDTO(
    Integer id,
    String title,
    byte[] picture
) {

}
