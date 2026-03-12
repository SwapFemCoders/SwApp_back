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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Tittle is cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 10 and 100 characters ")
    private String title;

    @NotNull(message = "Picture cannot be null")
    @Column(name = "picture", columnDefinition = "bytea")
    private byte[] picture;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @Enumerated
    private ArticleCategory category;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false, referencedColumnName = "id")
    private User creatorId;

    @ManyToOne
    @JoinColumn(name = "reserved_id", nullable = true, referencedColumnName = "id")
    private User reservedId;


    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;
    
    @NotNull(message = "State cannot be null")
    @Enumerated(EnumType.STRING)
    private ArticleState state;

}
