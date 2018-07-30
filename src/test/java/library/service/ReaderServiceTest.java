package library.service;

import library.domain.Reader;
import library.repository.ReaderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private Reader reader;

    private ReaderService readerService;

    @Before
    public void setUp() {
        this.readerService = new ReaderService(readerRepository);
    }

    @Test
    public void shouldReturnEmptyListIfFirstNameDoesNotMatch() {
        List<Reader> readers = readerService.getByName("firstName");
        assertEquals(0,readers.size());
    }

    @Test
    public void shouldReturnReaderWhenFirstNameMatches() {
        Reader reader = new Reader("firstName", "LastName");
        when(readerRepository.findByFirstName("firstName")).thenReturn(Collections.singletonList(reader));
        List<Reader> readers = readerService.getByName("firstName");
        assertEquals(1,readers.size());
    }

    @Test
    public void shouldReturnEmptyListIfLastNameDoesNotMatch() {
        List<Reader> readers = readerService.getByName("lastName");
        assertEquals(0,readers.size());
    }

    @Test
    public void shouldReturnReaderWhenLastNameMatches() {
        Reader reader = new Reader("firstName", "LastName");
        when(readerRepository.findByLastName("LastName")).thenReturn(Collections.singletonList(reader));
        List<Reader> readers = readerService.getByName("LastName");
        assertEquals(1,readers.size());
    }

    @Test
    public void shouldReturnEmptyListIfDOBDoesNotMatch() {
        Reader reader = new Reader("firstName", "LastName");
        Date dateOfBirth = new Date(30-05-1998);
        reader.setDOB(dateOfBirth);

        List<Reader> readers = readerService.getByDOB(new Date());
        assertEquals(0,readers.size());
    }

    @Test
    public void shouldReturnReaderWhenDOBMatches() {
        Reader reader = new Reader("firstName", "LastName");
        Date dateOfBirth = new Date(30-05-1998);
        reader.setDOB(dateOfBirth);

        when(readerRepository.findByDOB(new Date(30-05-1998))).thenReturn(Collections.singletonList(reader));
        List<Reader> readers = readerService.getByDOB(new Date(30-05-1998));
        assertEquals(1,readers.size());
    }

    @Test
    public void shouldCallReaderRepositorySaveMethodToCreateReader() {
        readerService.createReader(reader);
        verify(readerRepository,times(1)).save(reader);
    }

    @Test
    public void shouldCallReaderRepositoryDeleteByFirstName() {
        readerService.removeByFirstName("someName");
        verify(readerRepository,times(1)).deleteByFirstName("someName");
    }

    @Test
    public void shouldCallReaderRepositorySaveMethodWithUpdatedReader() {
        Reader reader = new Reader("Ram", "Singh");
        when(readerRepository.findByFirstName("Ram")).thenReturn(Collections.singletonList(reader));
        readerService.updateLastNameBy("Ram","bhai");
        reader.setLastName("bhai");
        verify(readerRepository,times(1)).save(reader);
    }
}