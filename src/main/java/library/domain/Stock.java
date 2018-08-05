package library.domain;

public class Stock {

    public String copyId;
    public String isbn;
    public Boolean availability;

    public Stock(String copyId, String isbn, Boolean availability) {
        this.copyId = copyId;
        this.isbn = isbn;
        this.availability = availability;
    }
}
