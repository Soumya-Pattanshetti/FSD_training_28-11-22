package com.ItemService.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ItemService.Entity.OrderDemo;
import com.ItemService.service.UserServices;

@RestController
//@SecurityRequirement(name = "security_scheme")
@CrossOrigin(value="http://localhost:4200/")
public class UserController {
	@Autowired
	UserServices userService;
	@Autowired
	private OrderDemo bookClient;

	@GetMapping("/home")
	public String getHomePage() {

		return "Welcome To Digital Book Home Page"; // text
	}

	@GetMapping("/booksearch")
	public List<Book> getAllBooks() {
		return bookClient.getBooks();
	}

	@PostMapping("/register")
	public String addUser(@RequestBody UserInfo user) throws UserExceptions {
		System.out.println( user.getUserName() +user.getPassword()+user.getRole());
	if (null != user.getUserName() && null != user.getPassword() && null != user.getRole()
				&& !user.getUserName().isBlank() && user.getPassword().isBlank() && user.getRole().isBlank()) {
		return userService.addUser(user);

		} else
			throw new UserExceptions("missing User details Retry with correct details");
	}

	@PutMapping("/updateUser")
	public String updateUser(@RequestBody UserInfo user) throws UserExceptions{
		if (null != user.getUserName() && null != user.getPassword() && null != user.getRole()
				&& !user.getUserName().isBlank() && user.getPassword().isBlank() && user.getRole().isBlank()) {
			return userService.updateUser(user);
		} else
			throw new UserExceptions("missing User details Retry with correct details");
	}
	@PutMapping("/userinfo/{userNaem}")
	public UserInfo getUserData(@PathVariable String userNaem )
	{
	
		
		return userService.findUser(userNaem).isPresent()?userService.findUser(userNaem).get():null;
	}

	@GetMapping("/BookServices/search/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
		return bookClient.getBookById(bookId);
	}

	@GetMapping("/BookServices/search/title/{title}")
	public Book getBookByTitle(@PathVariable String title) {
		return bookClient.getBookByName(title);
	}

	@PostMapping("/BookServices/subscribe/{userId}/{bookId}")
	public String subscribeBook(@PathVariable int userId, @PathVariable int bookId) {
		return bookClient.subscribeBook(userId, bookId);
	}

	@PutMapping("/BookServices/unsubscribe/{subscribeID}")
	public String unSubscribe(@PathVariable int subscribeID) {
		return bookClient.unSubscribe(subscribeID);

	}

	@PostMapping("/BookServices/book/add")
	public Book addBook(@RequestBody Book book) throws UserExceptions {
		if(!book.getAuthor().isBlank()&&!book.getTitle().isBlank()) {
		return bookClient.addBook(book);
	}else
		throw new UserExceptions("missing Book details Retry with correct details");
	}
	@PutMapping("/BookServices/book/edit")
	public Book updateBook(@RequestBody Book book) throws UserExceptions {
		if(!book.getAuthor().isBlank()&&!book.getTitle().isBlank()) {
		return bookClient.updateBook(book);
		}else
			throw new UserExceptions("missing Book details Retry with correct details");
	}

	@PutMapping("/BookServices/book/block/{id}")
	public String blockBook(@PathVariable int id) throws UserExceptions  {
		if(null!=bookClient.getBookById(id)) {
			return bookClient.blockBook(id);	
		}else 
			throw new UserExceptions("Book not found in Database");
		
	}

	@GetMapping("/BookServices/subscribedBooks/{userId}")
	public List<MySubscriptiondetails> subscribedBooks(@PathVariable int userId) {
		return bookClient.subscribedBooks(userId);
	}

	@GetMapping("/BookServices/subscribeHistory/{userId}")
	public List<Subscription> subscribeHistory(@PathVariable int userId) {
		return bookClient.subscribeHistory(userId);
	}

	@GetMapping("/BookServices/myBooks/{userName}")
	public List<Book> booksPublishedByUser(@PathVariable String userName) {
		return bookClient.BooksPublishedByUser(userName);
	}
	
	@GetMapping("/BookServices/reading/{userId}/{SubscriptionId}")
	public String readingBook(@PathVariable int userId,@PathVariable int SubscriptionId) {
		return bookClient.readBook(userId,SubscriptionId);
	}
	
	@DeleteMapping("/BookServices/deleteBook/{bookId}")
	public String deletingBook(@PathVariable int bookId)
	{
		return bookClient.deleteBook(bookId);
	}
}

}