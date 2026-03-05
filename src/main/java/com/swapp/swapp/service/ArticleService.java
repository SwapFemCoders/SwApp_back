package com.swapp.swapp.service;
import com.swapp.swapp.entity.Article;
import java.util.List;

public interface ArticleService {

    public Article createArticle (Article article);
    public List<Article> getAllAvailableArticles();
    public Article getArticleById(int id);
    public void deleteArticle(int id);

}
