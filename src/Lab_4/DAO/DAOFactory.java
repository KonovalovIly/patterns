package Lab_4.DAO;

import Lab_1.FactoryMethod.Car;

public interface DAOFactory {

    static DAOFactory getDAOFactory(ReaderType type) {
        switch (type) {
            case SERIALIZABLE:
                return new SerializableReaderDAOFactory();
            case TEXT:
                return new TextReaderDAOFactory();
            default:
                throw new IllegalStateException();
        }
    }

    DAO<Car> getCarDao();

}
