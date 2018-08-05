package library.repository;

import library.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock,String> {
    public List<Stock> findByIsbn(String isbn);

    List<Stock> findByAvailability(boolean status);
}
