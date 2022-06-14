package Lab_4.DAO;


import Lab_1.FactoryMethod.Car;
import Lab_1.FactoryMethod.exception.DuplicateModelNameException;

import java.io.*;

public class TextReader implements DAO<Car>{

    @Override
    public Car read() {
        String path = TextReaderDAOFactory.getPath();
        Car car = new Car("", 0);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            car.modificationMark(line);
            line = br.readLine();
            while (line != null) {
                String[] split = line.split("-");
                car.addModel(split[0], Double.parseDouble(split[1]));
                line = br.readLine();
            }
        } catch (IOException | DuplicateModelNameException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public void write(Car car) {
        String path = TextReaderDAOFactory.getPath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(car.getMark()+"\n");
            String[] names = car.getNameAllModels();
            double[] prices = car.getPricesAllModels();
            for (int i = 0; i < car.getModelsSize(); i++){
                writer.write(names[i] + "-" + prices[i] +"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
