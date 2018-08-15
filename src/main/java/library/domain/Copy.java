package library.domain;

public class Copy {

    public String copyId;
    public String isbn;
    public Boolean availability;

    public Copy(String copyId, String isbn, Boolean availability) {
        this.copyId = copyId;
        this.isbn = isbn;
        this.availability = availability;
    }
}
