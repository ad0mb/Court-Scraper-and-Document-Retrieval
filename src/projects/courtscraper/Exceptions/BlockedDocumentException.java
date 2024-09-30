package courtscraper.Exceptions;

public class BlockedDocumentException extends Exception {

    public BlockedDocumentException() {
        super();
    }

    public BlockedDocumentException(String message) {
        super(message);
    }
}
