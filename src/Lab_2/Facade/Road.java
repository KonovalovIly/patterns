package Lab_2.Facade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Road {

    private static final String ROAD_IMAGE_PATH = "resources/road.jpg";
    public static final int STOP_LINE_X = 420;
    public static final int STOP_LINE_Y = 330;

    public BufferedImage getPicture() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ROAD_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}