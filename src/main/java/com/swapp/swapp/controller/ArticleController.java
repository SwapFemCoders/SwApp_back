package com.swapp.swapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.service.ArticleService;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

   
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;

    }

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Article> createArticle(@RequestPart("article") Article article, @RequestPart("file")MultipartFile file) throws IOException {
        article.setPicture(file.getBytes());
        Article newArticle = articleService.createArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getAllAvailableArticles() {
        List<ArticleResponseDTO> articles = articleService.getAllAvailableArticles();
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

}