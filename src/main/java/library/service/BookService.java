package library.service;

import library.domain.Book;
import library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
