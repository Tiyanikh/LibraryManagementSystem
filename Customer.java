package com.RoomOne.LibraryRoom.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Entity
@Table(name = "Customer")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue
    Integer id;
    String login;
    String password;
    String email;
    @OneToMany(targetEntity = Books.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="sp_fk",referencedColumnName ="id" )
    private List<Books> books;
    @OneToMany(targetEntity = BookOrder.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="ap_fk",referencedColumnName ="id" )
    private List<BookOrder> bookOrders;
}
