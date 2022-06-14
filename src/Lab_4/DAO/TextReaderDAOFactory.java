package Lab_4.DAO;

import Lab_1.FactoryMethod.Car;

public class TextReaderDAOFactory implements DAOFactory {

    private static final String URL = "TextReaderDAO.txt";

    public static String getPath() {
        return URL;
    }

    @Override
    public DAO<Car> getCarDao() {
        return new TextReader();
    }
}
