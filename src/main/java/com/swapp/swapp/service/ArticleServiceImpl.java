package com.swapp.swapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swapp.swapp.dto.request.ArticleRequestDTO;
import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.ArticleState;
import com.swapp.swapp.entity.ArticleStatus;
import com.swapp.swapp.mapper.ArticleMapper;
import com.swapp.swapp.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper =articleMapper;
    }

    @Override
    public Article createArticle(Article article) {
        article.setStatus(ArticleStatus.AVAILABLE);
        
        return articleRepository.save(article);

    }

    @Override
    public List<ArticleResponseDTO> getAllAvailableArticles() {
       List<Article> list = articleRepository.findByStatus(ArticleStatus.AVAILABLE);
       return articleMapper.toResponseAll(list);
       
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
            throw new RuntimeException("The article does not exist");
        articleRepository.delete(optionalArticle.get());
    }

    @Override
    public ArticleBasicResponseDTO getArticleBasicResponseDTOById(int id) {
    Optional<Article> optionalArticle = articleRepository.findById(id);
    if (optionalArticle.isEmpty())
        throw new RuntimeException("The article does not exist");
    
    return articleMapper.toBasicDTO(optionalArticle.get());
    }
}


