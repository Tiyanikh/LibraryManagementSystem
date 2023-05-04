package com.RoomOne.LibraryRoom.Controller;

import com.RoomOne.LibraryRoom.Service.CustomerService;
import com.RoomOne.LibraryRoom.model.Books;
import com.RoomOne.LibraryRoom.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
public class CustomerController {
    private final CustomerService customerService;

@Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;


    }





    @GetMapping("/register2")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("registerRequest",new Customer());

        return "register_Page2";
    }
    @GetMapping("/allSelector")
    public String Selector() {

        return "selector_page2";
    }

    @GetMapping("/main")
    public String main() {

        return "newMain";
    }
    @GetMapping("/login2")
    public String getLoginPage(Model model)
    {

        model.addAttribute("loginRequest",new Customer());
        return "login_Page2";
    }
    @GetMapping("/allCustomer")
    public String listBook(Model model) {
        model.addAttribute("Books", customerService. getAllBooks());
        return "personal_page2";
    }

    @PostMapping("/register2")
    public String register(@ModelAttribute Customer customer)
    {
        System.out.println("register request:"+customer);
        Customer registeredUser =customerService.registerUser(customer.getLogin(), customer.getPassword(), customer.getEmail());
        return registeredUser == null ? "error_page":"redirect:/login2";
    }
    @PostMapping("/login2")
    public String login(@ModelAttribute Customer customer,Model model)
    {
        System.out.println("login request:" + customer);
      Customer authenticated = customerService.authenticate(customer.getLogin(), customer.getPassword());
        if (authenticated != null) {
            model.addAttribute("UserLogin",authenticated.getLogin());
            return "/welcome_page2";
        } else
        {
            return "error_page";
        }
    }
    @GetMapping("/edit2/{id}")
    public String editBookForm(@PathVariable Long id,Model model)
    {
        model.addAttribute("Books",customerService.getBooksById(id));
        return "edit_books2";
    }
    @PostMapping("/books2/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("Books") Books books, Model model)
    {
        Books existingBooks=customerService.getBooksById(id);
        existingBooks.setId(id);
        existingBooks.setBookName(books.getBookName());
       ;
        customerService. updateOrder(existingBooks);
        return "newMain";
    }

    private CustomerService updateOrder(Long id) {
        return null;
    }

    @GetMapping("/books2/{id}")
    public String deleteMyBooks(@PathVariable Long id)
    {
        customerService.deleteBookById(id);
        return "redirect:/main";
    }
}

