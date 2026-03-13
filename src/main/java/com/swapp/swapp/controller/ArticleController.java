package com.swapp.swapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.User;
import com.swapp.swapp.exception.UnauthorizedException;
import com.swapp.swapp.service.ArticleService;
import com.swapp.swapp.service.UserService;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ArticleResponseDTO>> getArticlesPaged(
            @RequestParam(defaultValue = "0") int page) {
        Page<ArticleResponseDTO> articles = articleService.getArticlesPaged(page);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArticleBasicResponseDTO> createArticle(@RequestPart("article") Article article,
            @RequestPart("file") MultipartFile file) throws IOException {
        User creator = userService.getUserById(getAuthenticatedUserId());
        article.setCreatorId(creator);

        article.setPicture(file.getBytes());
        ArticleBasicResponseDTO newArticle = articleService.createArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getAllAvailableArticles() {
        List<ArticleResponseDTO> articles = articleService.getAllAvailableArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleBasicResponseDTO> getArticleById(@PathVariable int id) {
        ArticleBasicResponseDTO article = articleService.getArticleBasicResponseDTOById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<ArticleResponseDTO>> getAllReservedArticles() {
        int userId = getAuthenticatedUserId();
        List<ArticleResponseDTO> articles = articleService.getAllReservedArticlesByReservedId(userId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/user/available")
    public ResponseEntity<List<ArticleResponseDTO>> getAllAvailableArticlesByCreatorId() {
        int userId = getAuthenticatedUserId();
        List<ArticleResponseDTO> articles = articleService.getAllAvailableArticlesByCreatorId(userId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleBasicResponseDTO> updateArticle(
            @PathVariable int articleId,
            @RequestPart("article") Article article,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        ArticleBasicResponseDTO updated = articleService.updateArticle(articleId, article, file);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{articleId}/reserve")
    public ResponseEntity<Article> toggleReservation(@PathVariable int articleId) {
        int userId = getAuthenticatedUserId();
        Article toggled = articleService.toggleReservation(articleId, userId);
        return new ResponseEntity<>(toggled, HttpStatus.OK);
    }

    private Integer getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            throw new UnauthorizedException("Invalid or expired session");
        }
        if (!(auth.getDetails() instanceof Integer)) {
            throw new UnauthorizedException("invalid session");
        }
        return (Integer) auth.getDetails();
    }

}