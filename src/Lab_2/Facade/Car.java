package Lab_2.Facade;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

public class Car {

    private static final String CAR_IMAGE_PATH = "resources/car.png";

    public static final int START_CAR_X_X = -70;
    public static final int START_CAR_X_Y = 420;
    public static final int START_CAR_Y_X = 507;
    public static final int START_CAR_Y_Y = -70;
    public static final int LENGTH = 70;
    public static final int SPEED = 15;

    private BufferedImage carPicture;

    private int carPositionX = START_CAR_X_X;
    private int carPositionY = START_CAR_Y_Y;

    public Car() {
        try {
            carPicture = ImageIO.read(new File(CAR_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPicture() {
        return this.carPicture;
    }

    public static BufferedImageOp getScalePictureOp() {
        AffineTransform at = new AffineTransform();
        at.scale(0.1F, 0.1F);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
    }

    public static BufferedImageOp getRotateScalePictureOp() {
        AffineTransform at = new AffineTransform();
        at.scale(0.1F, 0.1F);
        at.rotate(Math.PI / 2);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
    }

    public int getPositionX() {
        return carPositionX;
    }

    public void setPositionX(final int position) {
        this.carPositionX = position;
    }

    public int getPositionY() {
        return carPositionY;
    }

    public void setPositionY(final int position) {
        this.carPositionY = position;
    }
}
