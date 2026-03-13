package com.swapp.swapp.dto.response;

import com.swapp.swapp.entity.ArticleCategory;

public record ArticleResponseDTO(
    Integer id,
    String title,
    byte[] picture,
    ArticleCategory category
) {
}
