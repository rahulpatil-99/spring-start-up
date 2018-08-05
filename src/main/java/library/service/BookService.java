package library.service;

import library.domain.Book;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
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

    public Book getByBookId(String id) {
        return bookRepository.findByBookId(id);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<String> getIdsByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream().map(book -> book.bookId).collect(Collectors.toList());
    }

    public void removeBook(String bookId) {
        bookRepository.deleteByBookId(bookId);
    }
}
