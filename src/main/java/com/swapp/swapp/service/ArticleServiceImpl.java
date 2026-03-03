package com.swapp.swapp.service;

import org.springframework.stereotype.Service;

import com.swapp.swapp.entity.Article;
import com.swapp.swapp.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save (article);

    }

}
