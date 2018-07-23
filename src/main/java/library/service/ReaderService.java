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

    public List<Reader> getByName(String name) {
        List<Reader> byFirstName = readerRepository.findByFirstName(name);
        List<Reader> byLastName = readerRepository.findByLastName(name);
        byFirstName.addAll(byLastName);
        return byFirstName;
    }

    public List<Reader> getByDOB(Date date) {
        return readerRepository.findByDOB(date);
    }
}
