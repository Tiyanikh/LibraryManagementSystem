package com.RoomOne.LibraryRoom.Controller;

import com.RoomOne.LibraryRoom.Service.UserService;
import com.RoomOne.LibraryRoom.model.Books;
import com.RoomOne.LibraryRoom.model.UsersModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Transactional

public class UserController
{
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("registerRequest",new UsersModel());

        return "register_Page";
    }
    @GetMapping("/allSelector2")
    public String Selector() {

        return "selector_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model)
    {

        model.addAttribute("loginRequest",new UsersModel());
        return "login_Page";
    }
    @GetMapping("/all")
    public String listBook(Model model) {
        model.addAttribute("Books", userService. getAllBooks());
        return "personal_page";
    }
    @GetMapping("/books/new")
    public String CreateBookForm(Model model)
    {
       Books books=new Books();
        model.addAttribute("Books",books);
        return"create_books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id,Model model)
    {
        model.addAttribute("Books",userService.getBooksById(id));
        return "edit_books";
    }
    @PostMapping("/books/{id}")
public String updateBooks(@PathVariable Long id,@ModelAttribute("Books")Books books,Model model)
{
    Books existingBooks=userService.getBooksById(id);
    existingBooks.setId(id);
    existingBooks.setBookName(books.getBookName());
    existingBooks.setBookCategory(books.getBookCategory());
    existingBooks.setAuthor(books.getAuthor());
    existingBooks.setImageUrl(books.getImageUrl());
    userService.updateBooks(existingBooks);
    return "redirect:/all";
}
    @PostMapping("/books")
    public String SaveBooks(@ModelAttribute("Books")Books books)
    {
        Books addedBook=userService.addBooks(books.getBookName(),books.getBookCategory(),books.getAuthor(),books.getImageUrl());
        return addedBook==null? "error_page":"redirect:/all";
    }

@PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
{
    System.out.println("register request:"+usersModel);
    UsersModel registeredUser = userService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
      return registeredUser == null ? "error_page":"redirect:/login";
}
@GetMapping("/books/{id}")
public String deleteMyBooks(@PathVariable Long id)
{
    userService.deleteBookById(id);
    return "redirect:/all";
}

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel,Model model)
    {
        System.out.println("login request:" + usersModel);
        UsersModel authenticated = userService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("UserLogin",authenticated.getLogin());
            return "/welcome_page";
        } else
        {
            return "error_page";
        }
    }

    }
