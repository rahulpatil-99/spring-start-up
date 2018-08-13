package library.service;

import library.domain.Stock;
import library.domain.Transaction;
import library.exception.CopyAlreadyBoughtException;
import library.exception.CopyNotFoundException;
import library.exception.ReaderNotRegisteredException;
import library.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository repository;
    private StockService stockService;
    private ReaderService readerService;

    @Autowired
    public TransactionService(TransactionRepository repository, StockService stockService,ReaderService readerService) {
        this.repository = repository;
        this.stockService = stockService;
        this.readerService = readerService;
    }

    public void addTransaction(Transaction transaction) throws CopyAlreadyBoughtException, CopyNotFoundException, ReaderNotRegisteredException {
        if(!readerService.isRegistered(transaction.readerId)){
            throw new ReaderNotRegisteredException();
        }
        Stock copy = stockService.getByCopyId(transaction.copyId);
        if(copy instanceof Stock){
            if(copy.availability){
                repository.save(transaction);
                stockService.makeUnAvailable(copy.copyId);
            } else {
                throw new CopyAlreadyBoughtException();
            }
        } else{
           throw new CopyNotFoundException();
        }
    }

    public Transaction getTransaction(String transactionId) {
        return repository.findByTransactionId(transactionId);
    }

    public void returnedBookForTransaction(String transactionId) {
        Transaction transaction = this.getTransaction(transactionId);
        transaction.setReturnDate(new Date());
        this.stockService.makeAvailable(transaction.copyId);
    }

    public List<Transaction> getCurrentTransactionForUser(String readerId) {
        return repository.getByReaderId(readerId).stream()
                .filter(transaction -> transaction.returnDate==null)
                .collect(Collectors.toList());
    }
}
