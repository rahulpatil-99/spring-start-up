package library.repository;

import library.domain.Copy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Copy,String> {
    public List<Copy> findByIsbn(String isbn);

    List<Copy> findByAvailability(boolean status);

    Copy findByCopyId(String copyId);
}
