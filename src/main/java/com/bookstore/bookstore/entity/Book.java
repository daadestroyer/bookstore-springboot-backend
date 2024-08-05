package com.bookstore.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "book_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    @NotEmpty(message = "Please enter book name")
    @Size(min = 5, message = "Minimum length of book name is five")
    private String bookName;

    @NotNull(message = "Please enter book rating")
    @Positive(message = "Book rating must be positive")
    @Max(value = 5, message = "Maximum value allowed is 5")
    private int bookRating;

    @Column(unique = true)
    @NotEmpty(message = "Please enter book code")
    private String bookCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId",referencedColumnName = "authorId", nullable = false)
    @JsonBackReference
    private Author author;
}