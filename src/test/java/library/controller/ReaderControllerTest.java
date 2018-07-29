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
    public void shouldCallReaderServiceCreateUser() {
        controller.createReader(reader);
        verify(readerService,times(1)).createReader(reader);
    }
}