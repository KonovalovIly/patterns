package Lab_3.Command;

import Lab_1.FactoryMethod.Transport;

import java.io.OutputStream;

public interface PrintCommand {

    void execute(Transport transport, OutputStream stream);

}
