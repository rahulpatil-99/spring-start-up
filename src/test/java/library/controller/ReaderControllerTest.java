package library.controller;

import library.domain.Reader;
import library.service.ReaderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReaderControllerTest {

    @Mock
    private ReaderService readerService;
    @Mock
    private Reader reader;
    private ReaderController controller;

    @Before
    public void setUp() {
        controller = new ReaderController(readerService);
    }

    @Test
    public void shouldCallReaderServiceGetByName() {
        controller.getReader("shyam");
        verify(readerService,times(1)).getByName("shyam");
    }

    @Test
    public void shouldCallReaderServiceCreateReader() {
        controller.createReader(reader);
        verify(readerService,times(1)).createReader(reader);
    }

    @Test
    public void shouldCallReaderServiceRemoveByFirstName() {
        Reader reader = new Reader("fName", "lName");
        controller.createReader(reader);
        controller.deleteReader("fName");
        verify(readerService,times(1)).removeByFirstName("fName");
    }

    @Test
    public void shouldCallReaderServiceUpdateLastNameByFirstName() {
        Reader reader = new Reader("fName","lName");
        controller.createReader(reader);
        controller.updateLastName("fName","LastName");
        verify(readerService,times(1)).updateLastNameBy("fName","LastName");
    }
}