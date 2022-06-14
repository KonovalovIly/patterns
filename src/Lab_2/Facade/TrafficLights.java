package Lab_2.Facade;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

public class TrafficLights {

    private static final String TRAFFIC_LIGHTS_IMAGE_PATH = "resources/traffic_lights.png";
    public static final int TRAFFIC_LIGHT_X_X = 420;
    public static final int TRAFFIC_LIGHT_X_Y = 470;
    public static final int TRAFFIC_LIGHT_Y_X = 430;
    public static final int TRAFFIC_LIGHT_Y_Y = 300;

    private BufferedImage trafficLightsPicture;

    private State state;
    private State prevState = State.YELLOW;

    {
        try {
            trafficLightsPicture = ImageIO.read(new File(TRAFFIC_LIGHTS_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TrafficLights(State state){
        this.state = state;
    }

    public BufferedImage getPicture() {
        switch (state) {
            case GREEN:
                return trafficLightsPicture.getSubimage(1629, 0, 765, 1519);
            case RED:
                return trafficLightsPicture.getSubimage(0, 0, 765, 1519);
            case YELLOW:
                return trafficLightsPicture.getSubimage(765, 0, 765, 1519);
        }
        return null;
    }

    public static BufferedImageOp getScalePictureOp() {
        AffineTransform at = new AffineTransform();
        at.scale(0.05F, 0.05F);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
    }

    public void changeState() throws InterruptedException {
        switch (state) {
            case GREEN:
                Thread.sleep(10000);
                this.state = State.YELLOW;
                this.prevState = State.GREEN;
                break;
            case RED:
                Thread.sleep(10000);
                this.state = State.YELLOW;
                this.prevState = State.RED;
                break;
            case YELLOW:
                Thread.sleep(2000);
                if (prevState == State.RED) this.state = State.GREEN;
                else this.state = State.RED;
                this.prevState = State.YELLOW;
                break;
        }
    }

    public State getState() {
        return this.state;
    }

    public State getPrevState() {
        return this.prevState;
    }

    public enum State {
        GREEN, RED, YELLOW
    }

}
