package com.swapp.swapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    private String lastname;
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String location;

    @Column(name = "picture", columnDefinition = "bytea") 
    private byte[] picture;

     @Column(nullable = false)
    private int points;

//     @OneToMany(mappedBy="users", cascade= CascadeType.ALL)
//     @JsonIgnore
//     private List<Article> createdArticles = new ArrayList<>();

//     @OneToMany(mappedBy="users", cascade= CascadeType.ALL)
//     @JsonIgnore
//     private List<Article> reservedArticles = new ArrayList<>();


//     @ManyToMany(fetch = FetchType.LAZY)
//     @JoinTable(
//         name = "favorites_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "article_id")
//     )
//     private Set<Article> favorites = new HashSet<>();

}
