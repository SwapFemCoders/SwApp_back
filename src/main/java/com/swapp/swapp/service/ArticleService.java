package com.swapp.swapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;

import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;

public interface ArticleService {

    public ArticleBasicResponseDTO createArticle(Article article);

    public List<ArticleResponseDTO> getAllAvailableArticles();

    public Article getArticleById(int id);

    public void deleteArticle(int id);

    // public Article createArticle (ArticleRequestDTO dto);
    public ArticleBasicResponseDTO getArticleBasicResponseDTOById(int id);

    public List<ArticleResponseDTO> getAllReservedArticlesByReservedId(int reservedId);

    public List<ArticleResponseDTO> getAllAvailableArticlesByCreatorId(int creator_id);

    // public Article reservedArticle(int articleId);

    public Article toggleReservation(int articleId, int authenticatedUserId);

    public Page<ArticleResponseDTO> getArticlesPaged(int page);

}
