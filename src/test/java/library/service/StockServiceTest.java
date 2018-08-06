package library.service;

import library.domain.Book;
import library.domain.Stock;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    private StockService service;
    @Mock
    private StockRepository repository;
    @Mock
    private BookService bookService;
    @Mock
    private Book book;

    @Before
    public void setUp() {
        this.service = new StockService(repository,bookService);
    }

    @Test
    public void shouldAllowToAddCopyIfBookWithIsbnIsPresentInBooks() {
        Stock copy = new Stock("1-1", "1234", true);
        when(bookService.getByIsbn("1234")).thenReturn(book);
        service.addCopy(copy);
        verify(repository,times(1)).save(copy);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfBookWithIsbnIsNotPresentInBooks() {
        Stock copy = new Stock("1-1", "1234", true);
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
        List<Stock> stocks = new ArrayList<>();
        Stock copy1 = new Stock("1-1", "1234", true);
        stocks.add(copy1);
        stocks.add(new Stock("1-2","1234",false));

        when(repository.findByIsbn("1234")).thenReturn(stocks);
        List<Stock> availableCopies = service.getAvailableCopiesFor("1234");
        assertEquals(Collections.singletonList(copy1),availableCopies);
    }
}