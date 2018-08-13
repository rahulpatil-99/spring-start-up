package library.service;

import library.domain.Reader;
import library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReaderService {

    private ReaderRepository readerRepository;

    @Autowired
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

    public void createReader(Reader reader) {
        readerRepository.save(reader);
    }

    public void removeByFirstName(String firstName) {
        readerRepository.deleteByFirstName(firstName);
    }

    public void updateLastNameBy(String firstName, String lastName) {
        List<Reader> readers = readerRepository.findByFirstName(firstName);
        readers.stream().forEach(reader -> {
            reader.setLastName(lastName);
            readerRepository.save(reader);
        });
    }

    public boolean isRegistered(String readerId) {
        return readerRepository.findByReaderId(readerId) != null;
    }
}
