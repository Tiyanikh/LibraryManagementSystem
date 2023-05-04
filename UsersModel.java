package com.RoomOne.LibraryRoom.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Entity
@Table(name = "Users")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsersModel {
    @Id
    @GeneratedValue
    Integer id;
    String login;
    String password;
    String email;
   @OneToMany(targetEntity = Books.class,cascade = CascadeType.ALL)
   @JoinColumn(name ="cp_fk",referencedColumnName ="id" )
  private List<Books> books;

}
