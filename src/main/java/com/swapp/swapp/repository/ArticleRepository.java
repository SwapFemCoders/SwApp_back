package com.swapp.swapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.swapp.swapp.entity.Article;
import com.swapp.swapp.entity.ArticleStatus;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Integer>{

    List<Article> findByStatus(ArticleStatus status);
    List <Article> findByReservedId (Integer reservedId);
}



