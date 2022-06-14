package Lab_3.State;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SleepStudent implements Activity{

    @Override
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources/student/sleeping.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
