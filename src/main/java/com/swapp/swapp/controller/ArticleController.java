package com.swapp.swapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.service.ArticleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

   
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article newArticle = articleService.createArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllAvailableArticles() {
        List<Article> articles = articleService.getAllAvailableArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable int id) {
       Article article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/available")
    public ResponseEntity<ArticleResponseDTO> getAllAvailableArticlesToShow() {
        ArticleResponseDTO dto = articleService.getAllArticleResponseDTO();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
}