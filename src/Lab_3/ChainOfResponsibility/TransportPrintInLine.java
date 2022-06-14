package Lab_3.ChainOfResponsibility;

import Lab_1.FactoryMethod.Transport;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class TransportPrintInLine implements TransportChainHandler {

    private TransportChainHandler nextChain;

    @Override
    public void setNextHandler(TransportChainHandler handler) {
        nextChain = handler;
    }

    @Override
    public void handle(Transport request) {
        if (request.getModelsSize() <= 3){
            try (PrintWriter writer = new PrintWriter("Line Transport " + request.getMark() + ".txt", StandardCharsets.UTF_8)) {

                writer.print(request.getMark() + ":");

                String[] names = request.getNameAllModels();
                double[] prices = request.getPricesAllModels();

                for (int i = 0; i < names.length; i++) {
                    writer.print(" (" + names[i] + ", " + prices[i] + ")");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            nextChain.handle(request);
        }
    }
}
