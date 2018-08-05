package library.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Transaction {

    @Id
    public String transactionId;

    public String readerId;
    public String copyId;
    public Date borrowedDate;
    public Date returnDate;

    public Transaction(String readerId, String copyId, Date borrowedDate) {
        this.readerId = readerId;
        this.copyId = copyId;
        this.borrowedDate = borrowedDate;
    }

    public void setReturnDate(Date date) {
        this.returnDate=date;
    }
}
