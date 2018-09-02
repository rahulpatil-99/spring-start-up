package library.service;

import library.domain.Book;
import library.domain.Copy;
import library.repository.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CopyServiceTest {

    private CopyService service;
    @Mock
    private StockRepository repository;
    @Mock
    private BookService bookService;
    @Mock
    private Book book;

    @Before
    public void setUp() {
        this.service = new CopyService(repository,bookService);
    }

    @Test
    public void shouldAllowToAddCopyIfBookWithIsbnIsPresentInBooks() {
        Copy copy = new Copy("1-1", "1234", true);
        when(bookService.getByIsbn("1234")).thenReturn(book);
        service.addCopy(copy);
        verify(repository,times(1)).save(copy);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfBookWithIsbnIsNotPresentInBooks() {
        Copy copy = new Copy("1-1", "1234", true);
        service.addCopy(copy);
    }

    @Test
    public void shouldCallStockRepoFindByIsbn() {
        service.getByIsbn("1234");
        verify(repository,times(1)).findByIsbn("1234");
    }

    @Test
    public void shouldCallStockRepoFindByAvailability() {
        service.getAvailableCopies();
        verify(repository,times(1)).findByAvailability(true);
    }

    @Test
    public void shouldGetAvailableCopiesForGivenIsbn() {
        List<Copy> copies = new ArrayList<>();
        Copy copy1 = new Copy("1-1", "1234", true);
        copies.add(copy1);
        copies.add(new Copy("1-2","1234",false));

        when(repository.findByIsbn("1234")).thenReturn(copies);
        List<Copy> availableCopies = service.getAvailableCopiesFor("1234");
        assertEquals(Collections.singletonList(copy1),availableCopies);
    }

    @Test
    public void shouldMakeCopyUnavailable() {
        Copy copy = new Copy("1-1", "1234", true);
        when(repository.findByCopyId("1-1")).thenReturn(copy);
        service.makeUnAvailable("1-1");
        assertFalse(copy.availability);
    }

    @Test
    public void shouldMakeCopyAvailable() {
        Copy copy = new Copy("1-1", "1234", false);
        when(repository.findByCopyId("1-1")).thenReturn(copy);
        service.makeAvailable("1-1");
        assertTrue(copy.availability);
    }
}