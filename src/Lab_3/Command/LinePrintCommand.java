package Lab_3.Command;

import Lab_1.FactoryMethod.Transport;

import java.io.IOException;
import java.io.OutputStream;

public class LinePrintCommand implements PrintCommand {
    @Override
    public void execute(Transport transport, OutputStream stream) {
        try {
            stream.write((transport.getMark() + ":").getBytes());

            String[] names = transport.getNameAllModels();
            double[] prices = transport.getPricesAllModels();

            for (int i = 0; i < names.length; i++) {
                stream.write((" (" + names[i] + ", " + prices[i] + ")").getBytes());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
