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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;

    @NotEmpty(message = "please enter author name")
    private String authorName;

    @NotNull(message = "please enter author rating")
    @Positive(message = "author rating must in positive")
    @Max(value = 5, message = "maximum value allowed is 5")
    private int authorRating;

    @NotEmpty(message = "please enter email")
    @Email(message = "please enter valid email")
    private String authorEmail;

}
