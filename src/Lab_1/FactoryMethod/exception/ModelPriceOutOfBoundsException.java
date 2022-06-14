package Lab_1.FactoryMethod.exception;

public class ModelPriceOutOfBoundsException extends IllegalArgumentException {
    public ModelPriceOutOfBoundsException() {
    }

    public ModelPriceOutOfBoundsException(String message) {
        super(message);
    }
}
