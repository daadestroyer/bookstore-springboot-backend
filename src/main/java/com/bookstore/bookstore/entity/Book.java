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
    @Size(min = 5 , message = "minimum length of book name is five")
    private String bookName;

    @NotNull(message = "please enter book rating")
    @Positive(message = "book rating must in positive")
    @Max(value = 5, message = "maximum value allowed is 5")
    private int bookRating;

    @Column(unique = true)
    @NotEmpty(message = "Please enter book code")
    private String bookCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    private Author author;

}
