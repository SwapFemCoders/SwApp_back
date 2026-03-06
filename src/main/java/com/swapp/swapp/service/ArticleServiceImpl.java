package com.swapp.swapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//import com.swapp.swapp.dto.request.ArticleRequestDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.ArticleState;
import com.swapp.swapp.entity.ArticleStatus;
import com.swapp.swapp.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article createArticle(Article article) {
        article.setStatus(ArticleStatus.AVAILABLE);
        
        return articleRepository.save(article);

    }

    @Override
    public List<Article> getAllAvailableArticles() {
        return articleRepository.findByStatus(ArticleStatus.AVAILABLE);
    }

    @Override
    public Article getArticleById(int id) {
         Optional <Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty())
            throw new RuntimeException("The article does not exist");
        return optionalArticle.get();

    }

    @Override
    public void deleteArticle(int id) {
        Optional <Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty())
            throw new RuntimeException("No existe el articulo");
        articleRepository.delete(optionalArticle.get());
    }

    /* @Override("/article")
    public Article createArticle(ArticleRequestDTO dto) {
        Article article =new article();
        article.setTitle (dto.title());
        
    } */

    @Override
    public ArticleResponseDTO getAllArticleResponseDTO() {
        
        List<Article> articles = getAllAvailableArticles();

        
        List<Integer> ids = articles.stream()
                .map(Article::getId)
                .toList();
        List<String> titles = articles.stream()
                .map(Article::getTitle)
                .toList();
        List<String> pictures = articles.stream()
                .map(Article::getPicture)
                .toList();

        return new ArticleResponseDTO(ids, titles, pictures);
    }

}
