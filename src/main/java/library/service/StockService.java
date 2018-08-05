package library.service;

import library.domain.Stock;
import library.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private StockRepository repository;

    @Autowired
    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public void addCopy(Stock copy) {
        repository.save(copy);
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
