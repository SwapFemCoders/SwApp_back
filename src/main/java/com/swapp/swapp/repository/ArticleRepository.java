package com.swapp.swapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.ArticleStatus;
import com.swapp.swapp.entity.User;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Integer>{

    List<Article> findByStatus(ArticleStatus status);
    Page<Article> findByStatus(ArticleStatus status, Pageable pageable);
    List<Article> findByReservedId(User reservedId);
    List<Article> findByCreatorIdAndStatus(User creatorId, ArticleStatus status);
}

//ESTO ES PARA BORRAR


