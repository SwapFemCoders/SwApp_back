package com.swapp.swapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.swapp.swapp.dto.request.ArticleRequestDTO;
import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.User;

@Mapper(componentModel ="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    Article toEntity (ArticleRequestDTO dto);
    
    @Mapping(source = "reservedId.id", target = "reservedId") 
    @Mapping(source = "creatorId", target = "creatorId")
    ArticleBasicResponseDTO toBasicDTO (Article entity);

    List<ArticleResponseDTO> toResponseAll (List<Article> listArticle);

    UserBasicResponseDTO toUserBasicDTO(User user);

    

}
