package com.swapp.swapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @Column(name = "picture", columnDefinition = "bytea") 
    private byte[] picture;
    private String description;
    private LocalDate date;
    private String category;

    @ManyToOne
    @JoinColumn (name="creatorId", nullable =false, referencedColumnName = "id")
    private User creatorId;

    @ManyToOne
    @JoinColumn (name="reservedId", nullable =true, referencedColumnName = "id")
    private User reservedId;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Enumerated (EnumType.STRING)
    private ArticleState state;
    
}
