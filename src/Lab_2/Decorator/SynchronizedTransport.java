package Lab_2.Decorator;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.exception.NoSuchModelNameException;
import Lab_3.Command.PrintCommand;
import Lab_3.Visitor.Visitor;

import java.io.OutputStream;

public class SynchronizedTransport implements Transport {

    private Transport transport;

    public SynchronizedTransport(Transport t) {
        transport = t;
    }

    @Override
    public synchronized void modificationMark(String mark) {
        transport.modificationMark(mark);
    }

    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized String[] getNameAllModels() {
        return transport.getNameAllModels();
    }

    @Override
    public synchronized double[] getPricesAllModels() {
        return transport.getPricesAllModels();
    }

    @Override
    public synchronized double getModelPriceByName(String name) throws NoSuchModelNameException {
        return transport.getModelPriceByName(name);
    }

    @Override
    public synchronized void modificationPriceModelByName(String name, double price) throws NoSuchModelNameException {
        transport.modificationPriceModelByName(name, price);
    }

    @Override
    public synchronized void modificationNameModelByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        transport.modificationNameModelByName(oldName, newName);
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void deleteModel(String name) throws NoSuchModelNameException {
        transport.deleteModel(name);
    }

    @Override
    public synchronized int getModelsSize() {
        return transport.getModelsSize();
    }

    @Override
    public void setPrintCommand(PrintCommand command) {
        transport.setPrintCommand(command);
    }

    @Override
    public void print(OutputStream stream) {
        transport.print(stream);
    }

    @Override
    public synchronized Transport clone() {
        return transport.clone();
    }

    @Override
    public void accept(Visitor visitor) {
        transport.accept(visitor);
    }
}
