package Lab_3.ChainOfResponsibility;

import Lab_1.FactoryMethod.Transport;

public interface TransportChainHandler {

    public void setNextHandler(TransportChainHandler handler);

    public void handle(Transport request);
}
