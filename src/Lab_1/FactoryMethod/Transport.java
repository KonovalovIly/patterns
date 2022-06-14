package Lab_1.FactoryMethod;

import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.exception.NoSuchModelNameException;
import Lab_3.Command.PrintCommand;
import Lab_3.Visitor.Visitor;

import java.io.OutputStream;
import java.io.Serializable;

public interface Transport extends Cloneable, Serializable {
    void modificationMark(String mark);
    String getMark();
    String[] getNameAllModels();
    double[] getPricesAllModels();
    double getModelPriceByName(String name) throws NoSuchModelNameException;
    void modificationPriceModelByName(String name, double price) throws NoSuchModelNameException;
    void modificationNameModelByName(String oldName, String newName)
            throws DuplicateModelNameException, NoSuchModelNameException;
    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;
    int getModelsSize();
    void setPrintCommand(PrintCommand command);
    void print(OutputStream stream);
    Transport clone();
    void accept(Visitor visitor);
}