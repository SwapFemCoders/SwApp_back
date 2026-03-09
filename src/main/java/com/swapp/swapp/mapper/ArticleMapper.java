package com.swapp.swapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.swapp.swapp.dto.request.ArticleRequestDTO;
import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    Article toEntity (ArticleRequestDTO dto);
    
    ArticleBasicResponseDTO toBasicDTO (Article entity);

    List<ArticleResponseDTO> toResponseAll (List<Article> listArticle);

    

}
