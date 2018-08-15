package library.service;

import library.domain.Copy;
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
    private CopyService copyService;
    private ReaderService readerService;

    @Autowired
    public TransactionService(TransactionRepository repository, CopyService copyService, ReaderService readerService) {
        this.repository = repository;
        this.copyService = copyService;
        this.readerService = readerService;
    }

    public void addTransaction(Transaction transaction) throws CopyAlreadyBoughtException, CopyNotFoundException, ReaderNotRegisteredException {
        if(!readerService.isRegistered(transaction.readerId)){
            throw new ReaderNotRegisteredException();
        }
        Copy copy = copyService.getByCopyId(transaction.copyId);
        if(copy != null){
            if(copy.availability){
                repository.save(transaction);
                copyService.makeUnAvailable(copy.copyId);
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
        this.copyService.makeAvailable(transaction.copyId);
    }

    public List<Transaction> getCurrentTransactionForUser(String readerId) {
        return repository.getByReaderId(readerId).stream()
                .filter(transaction -> transaction.returnDate==null)
                .collect(Collectors.toList());
    }
}
