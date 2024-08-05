package com.bookstore.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "author_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @NotEmpty(message = "Please enter author name")
    private String authorName;

    @NotNull(message = "Please enter author rating")
    @Positive(message = "Author rating must be positive")
    @Max(value = 5, message = "Maximum value allowed is 5")
    private int authorRating;

    @NotEmpty(message = "Please enter email")
    @Email(message = "Please enter a valid email")
    @Column(unique = true)
    private String authorEmail;

    @OneToMany(mappedBy = "author", cascade = CascadeType.  ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @Valid
    @JsonManagedReference
    private List<Book> books;
}