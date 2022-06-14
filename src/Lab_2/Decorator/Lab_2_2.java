package Lab_2.Decorator;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.TransportStaticMethod;
import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;

public class Lab_2_2 {

    public static void main(String[] args) throws Exception {
        Transport car = createTransport();
        synchronizedTransport(car);
        nonSynchronizedTransport(car);
    }

    private static Transport createTransport() throws Exception {
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        final Transport car = TransportStaticMethod.createInstance("Subaru", 0);
        car.addModel("Impreza WRX", 2002.1);
        car.addModel("Levorg", 1003.7);
        return car;
    }

    private static void synchronizedTransport(Transport transport) {
        Transport synchronizedTransport = TransportStaticMethod.synchronizedTransport(transport);
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    synchronizedTransport.addModel("Impreza 11", 1201.5);
                    System.out.println("Model is added");
                } catch (DuplicateModelNameException ignored) {
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        TransportStaticMethod.printModels(synchronizedTransport);
    }

    private static void nonSynchronizedTransport(Transport transport) {
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    transport.addModel("Impreza 11", 1201.5);
                    System.out.println("Model is added");
                } catch (DuplicateModelNameException ignored) {
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        TransportStaticMethod.printModels(transport);
    }
}
