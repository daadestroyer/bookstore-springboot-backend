package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
    private String bookRating;

    @Column(unique = true)
    @NotEmpty(message = "Please enter book code")
    private String bookCode;

}
