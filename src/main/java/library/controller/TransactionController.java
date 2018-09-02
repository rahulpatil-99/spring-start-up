package library.controller;

import library.domain.Transaction;
import library.exception.CopyAlreadyBoughtException;
import library.exception.CopyNotFoundException;
import library.exception.ReaderNotRegisteredException;
import library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    private TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public void addTransaction(@RequestBody Transaction transaction) throws CopyNotFoundException, CopyAlreadyBoughtException, ReaderNotRegisteredException {
        service.addTransaction(transaction);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransaction(@PathVariable String transactionId) {
        return service.getTransaction(transactionId);
    }

    @GetMapping("/currentTransactions")
    public List<Transaction> getCurrentTransaction(@RequestParam("readerId") String readerId) {
        return service.getCurrentTransactionsForReader(readerId);
    }

    @GetMapping("/return/{transactionId}")
    public void returnCopy(@PathVariable String transactionId) {
        service.returnedBookForTransaction(transactionId);
    }
}
