import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Lab_2_4_Server {
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Run server, and wait user");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connect with client ready");
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        double a = in.readDouble();
        double b = in.readDouble();
        double outNum = a*b;
        out.writeDouble(outNum);
        out.flush();
        System.out.println("Number sending to client");
    }
}
