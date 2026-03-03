package com.swapp.swapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.swapp.swapp.entity.Article;
import com.swapp.swapp.service.ArticleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping ("/api/v1/articles")
public class ArticleController {

private final ArticleService articleService;

public ArticleController(ArticleService articleService){
this.articleService = articleService;

}

@PostMapping
public ResponseEntity<Article> createArticle (@RequestBody Article article ){
Article newArticle= articleService.createArticle(article);
return new ResponseEntity <> (newArticle, HttpStatus.CREATED);

}


}