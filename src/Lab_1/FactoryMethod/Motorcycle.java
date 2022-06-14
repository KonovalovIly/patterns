package Lab_1.FactoryMethod;

import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.exception.ModelPriceOutOfBoundsException;
import Lab_1.FactoryMethod.exception.NoSuchModelNameException;
import Lab_3.Command.PrintCommand;
import Lab_3.Visitor.Visitor;

import java.io.OutputStream;

public class Motorcycle implements Transport {

    private String mark;
    private int size;
    private Model head = new Model();
    private PrintCommand command;

    {
        head.prev = head;
        head.next = head;
    }

    public Motorcycle(String mark, int modelsSize) {
        this.mark = mark;
        size = modelsSize;

        Model prev = head;
        Model next = head;

        for (int i = 0; i < modelsSize; i++) {
            Model newModel = new Model(mark + (i + 1), (i + 1));
            prev.next = newModel;
            next.prev = newModel;

            newModel.prev = prev;
            newModel.next = next;

            prev = newModel;
        }
    }

    @Override
    public Motorcycle clone() {
        try {
            Motorcycle m = (Motorcycle) super.clone();
            m.head = new Model();
            m.head.next = m.head;
            m.head.prev = m.head;

            Model origCurr = head.next;
            Model clonePrev = m.head;
            Model cloneNext = m.head;

            while (origCurr != head) {
                Model newModel = origCurr.clone();

                newModel.prev = clonePrev;
                newModel.next = cloneNext;

                clonePrev.next = newModel;
                cloneNext.prev = newModel;

                clonePrev = newModel;
                origCurr = origCurr.next;
            }

            return m;
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
        Model curr = head.next;
        while (curr != head) {
            sb.append("    ").append(curr.name).append(" ").append(curr.price).append("\n");
            curr = curr.next;
        }
        return sb.toString();
    }

    protected static class Model implements Cloneable {

        private String name = null;
        private double price = Double.NaN;

        private Model prev = null;
        private Model next = null;

        public Model() {
        }

        public Model(String name, double price) {
            if (price <= 0) {
                throw new ModelPriceOutOfBoundsException("Price can't be 0 or less.");
            }
            this.name = name;
            this.price = price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }

    @Override
    public void modificationMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String[] getNameAllModels() {
        String[] names = new String[size];
        Model current = head.next;
        for (int i = 0; i < size; i++) {
            names[i] = current.name;
            current = current.next;
        }
        return names;
    }

    @Override
    public double[] getPricesAllModels() {
        double[] prices = new double[size];
        Model current = head.next;
        for (int i = 0; i < size; i++) {
            prices[i] = current.price;
            current = current.next;
        }
        return prices;
    }


    private Model getModelByName(String name) throws NoSuchModelNameException {
        Model curr = head.next;
        while (curr != head) {
            if (curr.name.equals(name)) {
                return curr;
            }
            curr = curr.next;
        }
        throw new NoSuchModelNameException("No model with name " + name + "  was found.");
    }

    private void checkPrice(double price) {
        if (price <= 0) {
            throw new ModelPriceOutOfBoundsException("Price can't be 0 or less.");
        }
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        return getModelByName(name).price;
    }

    @Override
    public void modificationPriceModelByName(String name, double price) throws NoSuchModelNameException {
        checkPrice(price);
        getModelByName(name).price = price;
    }

    @Override
    public void modificationNameModelByName(String oldName, String newName)
            throws DuplicateModelNameException, NoSuchModelNameException {
        Model current = head.next;
        Model updatable = head;
        while (current != head) {
            if (current.name.equals(oldName)) {
                updatable = current;
            } else if (current.name.equals(newName)) {
                throw new DuplicateModelNameException("Model with name " + newName + " already exists.");
            }
            current = current.next;
        }
        if (updatable != head) {
            updatable.name = newName;
        } else {
            throw new NoSuchModelNameException("No model with name " + oldName + "  was found.");
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        checkPrice(price);

        Model current = head.next;
        while (current != head) {
            if (current.name.equals(name)) {
                throw new DuplicateModelNameException("Model with name " + name + "  already exists.");
            }
            current = current.next;
        }

        Model newModel = new Model(name, price);
        Model prev = head.prev;
        Model next = head;
        newModel.next = next;
        newModel.prev = prev;
        prev.next = newModel;
        next.prev = newModel;

        size++;
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        Model curr = getModelByName(name);

        Model prev, next;
        prev = curr.prev;
        next = curr.next;

        prev.next = next;
        next.prev = prev;
        size--;
    }

    @Override
    public int getModelsSize() {
        return size;
    }

    @Override
    public void setPrintCommand(PrintCommand command) {
        this.command = command;
    }

    @Override
    public void print(OutputStream stream) {
        command.execute(this, stream);
    }
}
