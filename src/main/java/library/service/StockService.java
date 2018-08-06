package library.service;

import library.domain.Book;
import library.domain.Stock;
import library.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private StockRepository repository;
    private BookService bookService;

    @Autowired
    public StockService(StockRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public void addCopy(Stock copy) {
        Book book = bookService.getByIsbn(copy.isbn);
        if(book instanceof Book) {
            repository.save(copy);
        } else {
            throw new RuntimeException("can not add book");
        }
    }

    public List<Stock> getByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public List<Stock> getAvailableCopies() {
        return repository.findByAvailability(true);
    }

    public List<Stock> getAvailableCopiesFor(String isbn) {
        return this.getByIsbn(isbn)
                .stream()
                .filter(copy -> copy.availability)
                .collect(Collectors.toList());
    }
}
