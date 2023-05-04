package com.RoomOne.LibraryRoom.Service;
import com.RoomOne.LibraryRoom.Repository.BooksRepository;
import com.RoomOne.LibraryRoom.Repository.UserRepository;
import com.RoomOne.LibraryRoom.model.Books;
import com.RoomOne.LibraryRoom.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private  final BooksRepository booksRepository;
@Autowired
    public UserService(UserRepository userRepository, BooksRepository booksRepository) {
        this.userRepository = userRepository;
    this.booksRepository = booksRepository;
}

public UsersModel registerUser(String login,String password,String email){
if (login==null||password==null)
{
    return null;
}
else {
if (userRepository.findFirstByLogin(login).isPresent())
{
    System.out.println("Duplicate login");
    return null;
}
UsersModel usersModel = new UsersModel();
usersModel.setLogin(login);
usersModel.setPassword(password);
usersModel.setEmail(email);
return userRepository.save(usersModel);
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
    public  UsersModel authenticate(String login,String password){
    return userRepository.findByLoginAndPassword(login,password).orElse(null);
}
    public Books getBooksById(Long id){
        return booksRepository.findBooksById(id).get();
    }
    public Books updateBooks(Books books){

        return booksRepository.save(books);
    }
    public void deleteBookById(Long id){
        booksRepository.deleteById(id);
    }
}
