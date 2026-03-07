package com.swapp.swapp.dto.response;

import java.time.LocalDate;

import com.swapp.swapp.entity.ArticleState;
import com.swapp.swapp.entity.ArticleStatus;

public record ArticleBasicResponseDTO(

Integer id,
String title,
byte[] picture,
String description,
LocalDate date,
String creator,
ArticleStatus status,
ArticleState state
    
) {

}
