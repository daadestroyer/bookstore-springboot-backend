package com.bookstore.bookstore.entity;

import jakarta.persistence.*;
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
    private String bookName;
    private String bookRating;

    private String bookCode;

}
