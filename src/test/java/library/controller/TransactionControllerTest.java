package library.controller;

import library.domain.Transaction;
import library.exception.CopyAlreadyBoughtException;
import library.exception.CopyNotFoundException;
import library.exception.ReaderNotRegisteredException;
import library.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {
    private TransactionController controller;
    @Mock
    private TransactionService service;

    @Before
    public void setUp() {
        controller = new TransactionController(service);
    }

    @Test
    public void shouldCallTransactionServiceAddTransaction() throws CopyNotFoundException, CopyAlreadyBoughtException, ReaderNotRegisteredException {
        Transaction transaction = new Transaction("101","1-1",new Date(10-7-2018));
        controller.addTransaction(transaction);
        verify(service,times(1)).addTransaction(transaction);
    }

    @Test
    public void shouldCallTransactionServiceGetTransaction() {
        controller.getTransaction("1-1");
        verify(service,times(1)).getTransaction("1-1");
    }

    @Test
    public void shouldCallTransactionServiceGetCurrentTransactionsForReader() {
        controller.getCurrentTransaction("101");
        verify(service,times(1)).getCurrentTransactionsForReader("101");
    }

    @Test
    public void shouldCallTransactionServiceReturnBookForTransaction() {
        controller.returnCopy("34235454");
        verify(service,times(1)).returnedBookForTransaction("34235454");
    }
}