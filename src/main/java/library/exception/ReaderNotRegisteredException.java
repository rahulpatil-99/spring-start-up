package library.exception;

public class ReaderNotRegisteredException extends Throwable {
    public ReaderNotRegisteredException() {
        super("Reader Not yet registered!");
    }
}
