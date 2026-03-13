package com.swapp.swapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.swapp.swapp.dto.response.ArticleBasicResponseDTO;
import com.swapp.swapp.dto.response.ArticleResponseDTO;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.ArticleStatus;
import com.swapp.swapp.entity.User;
import com.swapp.swapp.mapper.ArticleMapper;
import com.swapp.swapp.repository.ArticleRepository;
import com.swapp.swapp.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper =articleMapper;

        this.userRepository = userRepository;
    }

    @Override
    public ArticleBasicResponseDTO createArticle(Article article) {
        article.setStatus(ArticleStatus.AVAILABLE);
        articleRepository.save(article);
        return articleMapper.toBasicDTO(article);

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

    @Override
    public List<ArticleResponseDTO> getAllReservedArticlesByReservedId(int reservedId) {
       User user = userRepository.findById(reservedId).orElseThrow();
       List<Article> list = articleRepository.findByReservedId(user);
       return articleMapper.toResponseAll(list);
         
    }

    @Override
    public List <ArticleResponseDTO> getAllAvailableArticlesByCreatorId(int creatorId){
        User user = userRepository.findById(creatorId).orElseThrow();
        List <Article> list = articleRepository.findByCreatorIdAndStatus(user, ArticleStatus.AVAILABLE);
        return articleMapper.toResponseAll (list);
    }

    @Override
public Article toggleReservation(int articleId, int userId) {
    Optional<Article> opt = articleRepository.findById(articleId);
    if (opt.isEmpty()) throw new RuntimeException("The article does not exist");
    Article article = opt.get();

    if (article.getStatus().equals(ArticleStatus.AVAILABLE)) {
        User authenticatedUser = userRepository.findById(userId)
       .orElseThrow(() -> new RuntimeException("The user does not exist"));
        article.setReservedId(authenticatedUser);
        article.setStatus(ArticleStatus.RESERVED);
    } else if (article.getStatus().equals(ArticleStatus.RESERVED)
            && article.getReservedId() != null
            && article.getReservedId().getId().equals(userId)) {
        article.setReservedId(null);
        article.setStatus(ArticleStatus.AVAILABLE);
    } else {
        throw new RuntimeException("Cannot toggle reservation for this article");
    }

    return articleRepository.save(article);
}
    @Override
    @Transactional
    public ArticleBasicResponseDTO updateArticle(int id, Article article, MultipartFile file) {
        Article existingArticle = getArticleById(id);

        existingArticle.setTitle(article.getTitle());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setCategory(article.getCategory());
        existingArticle.setState(article.getState());
        existingArticle.setDate(article.getDate());

        if (file != null && !file.isEmpty()) {
            try {
                existingArticle.setPicture(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error saving image");
            }
        }
        Article saved = articleRepository.save(existingArticle);
        return articleMapper.toBasicDTO(saved); 
    }

   @Override
    public Page<ArticleResponseDTO> getArticlesPaged(int page) {
        Pageable pageable = PageRequest.of(page, 30, Sort.by("date").descending());
        Page<Article> pageArticles = articleRepository.findByStatus(ArticleStatus.AVAILABLE, pageable);
        return pageArticles.map(articleMapper::toResponseDTO);
    }

}




