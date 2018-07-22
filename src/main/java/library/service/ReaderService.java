package library.service;

import library.domain.Reader;
import library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReaderService {

    private ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> getByFirstName(String firstName) {
        return readerRepository.findByFirstName(firstName);
    }

    public List<Reader> getByLastName(String lastName) {
        return readerRepository.findByLastName(lastName);
    }

    public List<Reader> getByDOB(Date date) {
        return readerRepository.findByDOB(date);
    }
}
