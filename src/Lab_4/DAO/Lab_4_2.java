package Lab_4.DAO;

import Lab_1.FactoryMethod.Car;
import Lab_3.ChainOfResponsibility.Lab_3_1;

public class Lab_4_2 {

    public static void main(String[] args) throws Exception {
        Car car = (Car) Lab_3_1.createCar(4);
        System.out.println(car.toString());
        writeAndRead(ReaderType.TEXT, car);
        writeAndRead(ReaderType.SERIALIZABLE, car);
    }

    private static void writeAndRead(ReaderType type, Car car) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(type);
        DAO<Car> carDao = daoFactory.getCarDao();

        carDao.write(car);
        Car readCar = carDao.read();
        System.out.println(type);
        System.out.println(readCar.toString());
    }


}
