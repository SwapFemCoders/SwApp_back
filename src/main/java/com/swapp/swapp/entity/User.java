package com.swapp.swapp.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(unique = true, nullable = false, length = 50)
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 200, message = "Password must be between 6 and 50 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(max = 50, message = "Location must not exceed 50 characters")
    private String location;

    @Column(name = "picture", columnDefinition = "bytea")
    private byte[] picture;

    @Min(value = 0, message = "Points cannot be negative")
    @Column(nullable = false)
    private int points;

    @OneToMany(mappedBy="creatorId", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Article> createdArticles = new ArrayList<>();

    @OneToMany(mappedBy="reservedId", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Article> reservedArticles = new ArrayList<>();

}
