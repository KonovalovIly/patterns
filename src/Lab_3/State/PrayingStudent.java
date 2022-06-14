package Lab_3.State;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrayingStudent implements Activity {

    @Override
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources/student/pray_to_god.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
