package uet.oop.bomberman.exceptions;

public class LoadLevelException extends GameException{
    public LoadLevelException() {
    }

    public LoadLevelException(String message) {
        super(message);
    }

    public LoadLevelException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadLevelException(Throwable cause) {
        super(cause);
    }
}
