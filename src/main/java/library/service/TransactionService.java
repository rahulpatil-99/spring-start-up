package library.service;

import library.domain.Transaction;
import library.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return repository.findByTransactionId(transactionId);
    }

    public void returnedBookForTransaction(String transactionId) {
        this.getTransaction(transactionId).setReturnDate(new Date());
    }

    public List<Transaction> getCurrentTransactionForUser(String readerId) {
        return repository.getByReaderId(readerId).stream().filter(transaction -> transaction.returnDate==null).collect(Collectors.toList());
    }
}
