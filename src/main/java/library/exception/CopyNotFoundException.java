package library.exception;

public class CopyNotFoundException extends Throwable {
    public CopyNotFoundException() {
        super("Copy is not available in the stock");
    }
}
