package Lab_4.DAO;

import Lab_1.FactoryMethod.Car;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableReader implements DAO<Car>{

    @Override
    public Car read() {
        String path = SerializableReaderDAOFactory.getPath();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Car) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void write(Car car) {
        String path = SerializableReaderDAOFactory.getPath();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(car);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
