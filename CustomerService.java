package com.RoomOne.LibraryRoom.Service;

import com.RoomOne.LibraryRoom.Repository.BooksRepository;
import com.RoomOne.LibraryRoom.Repository.CustomerRepository;

import com.RoomOne.LibraryRoom.model.Books;
import com.RoomOne.LibraryRoom.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private  final CustomerRepository customerRepository;
    private final BooksRepository booksRepository;
  
@Autowired
    public CustomerService(CustomerRepository customerRepository, BooksRepository booksRepository) {
        this.customerRepository = customerRepository;
        this.booksRepository = booksRepository;
   
}

    public Customer registerUser(String login, String password, String email){
        if (login==null||password==null)
        {
            return null;
        }
        else {
            if (customerRepository.getFirstByLogin(login).isPresent())
            {
                System.out.println("Duplicate login");
                return null;
            }
            Customer customer = new Customer();
            customer.setLogin(login);
            customer.setPassword(password);
            customer.setEmail(email);
            return customerRepository.save(customer);
        }

    }
    public Books addBooks(String bookName, String bookCategory, String author, String imageUr) {
        Books books = new Books();
        books.setBookName(bookName);
        books.setBookCategory(bookCategory);
        books.setAuthor(author);
        books.setImageUrl(imageUr);
        return booksRepository.save(books);
    }
    public Object getAllBooks(){

        return booksRepository.findAll();
    }
    public Customer authenticate(String login, String password){
        return customerRepository.getByLoginAndPassword(login,password).orElse(null);
    }
    public Books getBooksById(Long id){
        return booksRepository.findBooksById(id).get();
    }


    public void updateOrder(Books existingBooks) {
    }

    public void deleteBookById(Long id) {
    }
}
