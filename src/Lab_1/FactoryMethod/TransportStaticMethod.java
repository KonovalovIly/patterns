package Lab_1.FactoryMethod;

import Lab_1.FactoryMethod.factory.CarFactory;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_2.Decorator.SynchronizedTransport;

public class TransportStaticMethod {

    private static TransportFactory factory = new CarFactory();

    public static TransportFactory getFactory() {
        return factory;
    }

    public static Transport createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }

    public static void setTransportFactory(TransportFactory factory) {
        TransportStaticMethod.factory = factory;
    }

    public static double getAveragePrice(Transport t) {
        double sum = 0;
        double[] prices = t.getPricesAllModels();
        for (double price : prices) sum += price;
        return sum / prices.length;
    }

    public static void printModels(Transport t) {
        System.out.println("Model names:");
        for (String name : t.getNameAllModels())
            System.out.println(name);
    }

    public static void printModelsPrices(Transport t) {
        System.out.println("Model prices:");
        for (double price : t.getPricesAllModels())
            System.out.println(price);
    }

    public static Transport synchronizedTransport(Transport t){
        return new SynchronizedTransport(t);
    }

}
