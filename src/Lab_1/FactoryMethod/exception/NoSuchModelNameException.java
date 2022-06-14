package Lab_1.FactoryMethod.exception;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException() {
    }

    public NoSuchModelNameException(String message) {
        super(message);
    }
}

