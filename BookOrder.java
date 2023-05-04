package com.RoomOne.LibraryRoom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String bookCategory;
    private String author;
    private String imageUrl;

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                ", author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
    @OneToMany(targetEntity = BookOrder.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="ak_fk",referencedColumnName ="id" )
    private List<Books> books;
}

