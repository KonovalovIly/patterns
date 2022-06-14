package Lab_2.Facade;

import java.io.IOException;

public class Lab_2_3 {

    public static void main(String[] args) throws IOException {
        AbstractFacade facade = new Facade();
        facade.runProcess();
    }

}
