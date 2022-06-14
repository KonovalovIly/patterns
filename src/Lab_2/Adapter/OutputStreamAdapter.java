package Lab_2.Adapter;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamAdapter {

    public void adapt(String[] strings, OutputStream outputStream) throws IOException {
        for (String string :strings){
            byte[] bytes = string.getBytes();
            outputStream.write(bytes);
            outputStream.write(("\n").getBytes());
            outputStream.flush();
        }
    }

}
