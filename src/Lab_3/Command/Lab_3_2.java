package Lab_3.Command;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.TransportStaticMethod;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

import java.io.FileOutputStream;
import java.io.IOException;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;

public class Lab_3_2 {

    public static void main(String[] args) throws Exception {
        Transport transport = getTransport();
        linePrint(transport);
        columnPrint(transport);
    }

    private static void columnPrint(Transport transport) {
        try (FileOutputStream writer = new FileOutputStream("Command Column Transport " + transport.getMark() + " line.txt")) {
            transport.setPrintCommand(new ColumnPrintCommand());
            transport.print(writer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void linePrint(Transport transport) {
        try (FileOutputStream writer = new FileOutputStream("Command Line Transport " + transport.getMark() + " line.txt")) {
            transport.setPrintCommand(new LinePrintCommand());
            transport.print(writer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Transport getTransport() throws Exception {
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        Transport car = TransportStaticMethod.createInstance("Subaru", 0);
        car.addModel("Impreza WRX", 2002.1);
        car.addModel("Levorg", 1003.7);
        return car;
    }


}
