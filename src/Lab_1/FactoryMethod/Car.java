package Lab_1.FactoryMethod;

import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.exception.ModelPriceOutOfBoundsException;
import Lab_1.FactoryMethod.exception.NoSuchModelNameException;
import Lab_3.Command.PrintCommand;
import Lab_3.Visitor.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements Transport, Serializable {

    private String mark;
    private Model[] models;
    private PrintCommand command;

    public Car(String mark, int modelsSize) {
        this.mark = mark;
        this.models = new Model[modelsSize];

        for (int i = 0; i < modelsSize; i++) {
            models[i] = new Model(mark + (i + 1), (i + 1));
        }
    }

    @Override
    public void modificationMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    @Override
    public String[] getNameAllModels() {
        String[] names = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].name;
        }
        return names;
    }

    @Override
    public double[] getPricesAllModels() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].price;
        }
        return prices;
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        return getModelByName(name).price;
    }

    @Override
    public void modificationPriceModelByName(String name, double price) throws NoSuchModelNameException {
        getModelByName(name).price = price;
    }

    @Override
    public void modificationNameModelByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        Model model = getModelByName(oldName);
        for (Model mod : models) {
            if (mod.name.equals(newName)) {
                throw new DuplicateModelNameException("Model with name " + newName + " already exists.");
            }
        }
        model.name = newName;
    }

    private Model getModelByName(String name) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model.name.equals(name)) {
                return model;
            }
        }
        throw new NoSuchModelNameException("No model with name " + name + "  was found.");
    }

    private void checkPrice(double price) {
        if (price <= 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be 0 or less.");
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        checkPrice(price);
        int i = 0;
        int j = -1;
        while (i < models.length) {
            if (models[i].name.equals(name)) {
                j = i;
                break;
            }
            i++;
        }
        if (j >= 0) {
            throw new DuplicateModelNameException("Model with name " + name + "  already exists.");
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(name, price);
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        int deleteIndex = 0;
        while (deleteIndex < models.length) {
            deleteIndex++;
            if (((models[deleteIndex].name)).equals(name)) break;
        }
        if (deleteIndex == models.length) {
            throw new NoSuchModelNameException("No model with name " + name + "  was found.");
        }
        Model[] newModels = new Model[models.length - 1];
        System.arraycopy(models, 0, newModels, 0, deleteIndex);
        System.arraycopy(models, deleteIndex + 1, newModels, deleteIndex, models.length - deleteIndex - 1);
        models = newModels;
    }

    @Override
    public int getModelsSize() {
        return models.length;
    }

    @Override
    public void setPrintCommand(PrintCommand command) {
        this.command = command;
    }

    @Override
    public void print(OutputStream stream) {
        command.execute(this, stream);
    }

    @Override
    public Car clone() {
        try {
            Car a = (Car) super.clone();
            a.models = this.models.clone();
            for (int i = 0; i < models.length; i++) {
                a.models[i] = this.models[i].clone();
            }
            return a;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mark).append("\n");
        sb.append("Models: \n");
        for (Model model : models) {
            sb.append("    ").append(model.name).append(" ").append(model.price).append("\n");
        }
        return sb.toString();
    }

    static class Model implements Cloneable, Serializable {

        private String name;
        private double price;

        public Model(String name, double price) {
            if (price <= 0) {
                throw new ModelPriceOutOfBoundsException("Price can't be 0 or less.");
            } else {
                this.name = name;
                this.price = price;
            }
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

        @Override
        public String toString() {
            return name + ' ' + price;
        }

    }

    class AutoIterator implements Iterator {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            return models[currentIndex++];
        }

    }

    public AutoIterator iterator() {
        return new AutoIterator();
    }

    public static class AutoMemento {

        private byte[] state;

        public void setAuto(Car car) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(car);
            oos.flush();
            state = baos.toByteArray();
        }

        public Car getAuto() throws IOException, ClassNotFoundException {
            ByteArrayInputStream bais = new ByteArrayInputStream(state);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Car) ois.readObject();
        }
    }

    public AutoMemento createMemento() throws IOException {
        AutoMemento memento = new AutoMemento();
        memento.setAuto(this);
        return memento;
    }

    public Car setMemento(AutoMemento memento) throws IOException, ClassNotFoundException {
        return memento.getAuto();
    }

}
