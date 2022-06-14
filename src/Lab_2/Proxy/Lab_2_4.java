package Lab_2.Proxy;

import java.io.IOException;

public class Lab_2_4 {

    private static final double A = 12;
    private static final double B = 104.3;

    public static void main(String[] args) throws IOException {
        Proxy multiplyService = new Proxy();
        System.out.println(multiplyService.multiply(A, B));
    }
}
