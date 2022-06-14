package Lab_2.Adapter;

import java.io.IOException;
import java.io.OutputStream;

public class Lab_2_1 {

    public static void main(String[] args) throws IOException {
        OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
        String[] strings = new String[]{"first", "second", "third", "fourth"};
        OutputStream outputStream = System.out;
        outputStreamAdapter.adapt(strings, outputStream);
    }

}
