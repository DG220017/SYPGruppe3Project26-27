package pocket.exception;

public class PocketUserNotFoundException extends RuntimeException {
    public PocketUserNotFoundException(String message) {
        super(message);
    }
}
