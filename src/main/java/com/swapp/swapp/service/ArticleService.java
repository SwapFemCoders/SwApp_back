package com.swapp.swapp.service;
import java.util.List;

//import com.swapp.swapp.dto.request.ArticleRequestDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;

public interface ArticleService {

    public Article createArticle (Article article);
    public List<Article> getAllAvailableArticles();
    public Article getArticleById(int id);
    public void deleteArticle(int id);
    //public Article createArticle (ArticleRequestDTO dto);
   
    public ArticleResponseDTO getAllArticleResponseDTO();
    
}
