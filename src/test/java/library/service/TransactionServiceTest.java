package library.service;

import library.domain.Transaction;
import library.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    private TransactionService service;
    @Mock
    private TransactionRepository repository;
    @Mock
    private Transaction transaction;

    @Before
    public void setUp() {
        service = new TransactionService(repository);
    }

    @Test
    public void shouldCallTransactionRepoSave() {
        service.addTransaction(transaction);
        verify(repository,times(1)).save(transaction);
    }

    @Test
    public void shouldCallTransactionRepoFindByTransactionId() {
        service.getTransaction("12234556");
        verify(repository,times(1)).findByTransactionId("12234556");
    }

    @Test
    public void shouldUpdateReturnDateForGivenTransaction() {
        Transaction transaction = new Transaction("123", "1-2", new Date(20-7-2018));
        when(repository.findByTransactionId("12234556")).thenReturn(transaction);
        service.returnedBookForTransaction("12234556");
        Transaction actualTransaction = service.getTransaction("12234556");
        transaction.setReturnDate(new Date());
        assertEquals(actualTransaction,transaction);
    }

    @Test
    public void shouldReturnTransactionsForGivenMemberId() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction("111", "1-1", new Date(20 - 6 - 2018));
        Transaction transaction2 = new Transaction("111", "2-1", new Date(10 - 7 - 2018));
        transaction1.setReturnDate(new Date(10-7-2018));
        transactions.add(transaction1);
        transactions.add(transaction2);
        when(repository.getByReaderId("111")).thenReturn(transactions);
        List<Transaction> transactionsForUser = service.getCurrentTransactionForUser("111");
        assertEquals(transactionsForUser,Collections.singletonList(transaction2));
    }
}