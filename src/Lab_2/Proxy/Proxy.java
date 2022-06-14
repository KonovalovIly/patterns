package Lab_2.Proxy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Proxy {
    private static final int SERVER_PORT = 5000;
    private static final String SERVER_ADDRESS = "localhost";

    public double multiply(double a, double b) throws IOException {
        double outNum = 0;
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeDouble(a);
            out.writeDouble(b);
            out.flush();
            outNum = in.readDouble();

        }
        return outNum;
    }

}
