package Lab_4.DAO;

import Lab_1.FactoryMethod.Car;

public class SerializableReaderDAOFactory implements DAOFactory{

    private static final String URL = "SerializableReaderDAO.txt";

    public static String getPath() {
        return URL;
    }

    @Override
    public DAO<Car> getCarDao() {
        return new SerializableReader();
    }
}
