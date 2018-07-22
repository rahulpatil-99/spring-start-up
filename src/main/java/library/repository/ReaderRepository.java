package library.repository;

import library.domain.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ReaderRepository extends MongoRepository<Reader,String> {

    public List<Reader> findByFirstName(String firstName);
    public List<Reader> findByLastName(String lastName);

    public List<Reader> findByDOB(Date date);
}
