package library.service;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    private StockService service;
    @Mock
    private StockRepository repository;
    @Mock
    private Stock copy;

    @Before
    public void setUp() {
        this.service = new StockService(repository);
    }

    @Test
    public void shouldCallStockRepoSaveMethod() {
        service.addCopy(copy);
        verify(repository,times(1)).save(copy);
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