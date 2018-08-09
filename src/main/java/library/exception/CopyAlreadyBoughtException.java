package library.exception;

public class CopyAlreadyBoughtException extends Throwable {
    public CopyAlreadyBoughtException() {
        super("Currently copy is bought by another reader");
    }
}
