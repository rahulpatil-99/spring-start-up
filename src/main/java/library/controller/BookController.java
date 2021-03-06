package library.controller;

import library.domain.Book;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable(value = "bookId") String id) {
        return bookService.getByBookId(id);
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/ids/{title}")
    public List<String> getIdsByTitle(@PathVariable String title) {
        return bookService.getIdsByTitle(title);
    }

    @DeleteMapping("/remove")
    public void removeBook(@RequestBody String bookId) {
        bookService.removeBook(bookId);
    }
}
