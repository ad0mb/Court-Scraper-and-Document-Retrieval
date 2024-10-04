package courtscraper.exceptions;

public class BlockedDocumentException extends Exception {

    public BlockedDocumentException() {
        super();
    }

    public BlockedDocumentException(String message) {
        super(message);
    }
}
