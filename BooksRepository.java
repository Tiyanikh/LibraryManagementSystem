package com.RoomOne.LibraryRoom.Repository;

import com.RoomOne.LibraryRoom.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories
public interface BooksRepository extends JpaRepository<Books,Long> {

    Optional<Books> findBooksById(Long id);


    default Optional<Books> findBooksByName(String bookName) {
        return null;
    }

    void deleteById(Long id);
}
