package com.RoomOne.LibraryRoom.Repository;


import com.RoomOne.LibraryRoom.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository <Customer,Integer>{
   

    Optional<Customer> getFirstByLogin(String login);

    Optional<Customer> getByLoginAndPassword(String login, String password);
}
