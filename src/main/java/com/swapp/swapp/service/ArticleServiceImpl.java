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
import com.swapp.swapp.entity.User;
import com.swapp.swapp.mapper.ArticleMapper;
import com.swapp.swapp.mapper.UserMapper;
import com.swapp.swapp.repository.ArticleRepository;
import com.swapp.swapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper, UserRepository userRepository, UserMapper userMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper =articleMapper;

        this.userMapper = userMapper;
        this.userRepository = userRepository;
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

    //Articulos reservados por un ID X que se le pasa a la función
    @Override
    public List<ArticleResponseDTO> getAllReservedArticlesByReservedId(int reservedId) {
       User user = userRepository.findById(reservedId).orElseThrow();
       List<Article> list = articleRepository.findByReservedId(user);
       return articleMapper.toResponseAll(list);
         
    }

    //Articulos que yo cree y están disponibles
    @Override
    public List <ArticleResponseDTO> getAllAvailableArticlesByCreatorId(int creatorId){
        User user = userRepository.findById(creatorId).orElseThrow();
        List <Article> list = articleRepository.findByCreatorIdAndStatus(user, ArticleStatus.AVAILABLE);
        return articleMapper.toResponseAll (list);
    }

    //Articulos que yo cree y alguien más los reservó
}


