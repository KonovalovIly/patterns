package Lab_1.FactoryMethod.exception;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException() {
    }

    public DuplicateModelNameException(String message) {
        super(message);
    }
}