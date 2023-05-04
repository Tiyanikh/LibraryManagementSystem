package com.RoomOne.LibraryRoom;

import com.RoomOne.LibraryRoom.Repository.BooksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryRoomApplication implements CommandLineRunner {
	private final BooksRepository booksRepository;

	public LibraryRoomApplication(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryRoomApplication.class, args);
	}

	@Override
	public void run(String...args)throws Exception{
	/*BookOrder bookk1=new BookOrder(1L,"Strong love","Romentic","Khosa T","bloaoaj");
		booksRepository.save(bookk1);*/
	}

}
