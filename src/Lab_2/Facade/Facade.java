package Lab_2.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Facade extends AbstractFacade {

    private static Road roadInstance;
    private static Car carInstanceX;
    private static TrafficLights trafficLightsInstanceX;

    private static Car carInstanceY;
    private static TrafficLights trafficLightsInstanceY;

    private static BufferedImage carPicture;
    private static BufferedImage roadPicture;
    private static BufferedImage trafficLightsPictureX;

    private static BufferedImage trafficLightsPictureY;

    private static JPanel jPanel;
    private static JFrame jFrame;

    @Override
    public void runProcess() {
        initDrawable();
        setWindow();
        setCarDrive();
        setTrafficLightSwitcher();
    }

    private static void setWindow() {

        drawInstances();
        JLabel roadLabel = new JLabel(new ImageIcon(roadPicture));
        jPanel = new JPanel();
        jPanel.add(roadLabel);

        jFrame = new JFrame();
        jFrame.setSize(new Dimension(roadPicture.getWidth(), roadPicture.getHeight()));
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private static void setCarDrive() {
        new Thread(() -> {
            while (true) {
                drawInstances();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                rideCarX();
                rideCarY();

                JLabel pL = new JLabel(new ImageIcon(roadPicture));

                jPanel.removeAll();

                jPanel.add(pL);
                jFrame.repaint();
                jFrame.revalidate();
                jFrame.pack();
                jFrame.setLocationRelativeTo(null);
            }
        }).start();
    }

    private static void rideCarX() {
        if (carInstanceX.getPositionX() < Road.STOP_LINE_X - Car.LENGTH
                || carInstanceX.getPositionX() > Road.STOP_LINE_X
                || TrafficLights.State.GREEN.equals(trafficLightsInstanceX.getState())
                || TrafficLights.State.YELLOW.equals(trafficLightsInstanceX.getState())
                && TrafficLights.State.GREEN.equals(trafficLightsInstanceX.getPrevState())
        ) {
            if (carInstanceX.getPositionX() < roadPicture.getWidth())
                carInstanceX.setPositionX(carInstanceX.getPositionX() + Car.SPEED);
            else
                carInstanceX.setPositionX(Car.START_CAR_X_X);
        }
    }

    private static void rideCarY() {
        if (carInstanceY.getPositionY() < Road.STOP_LINE_Y - Car.LENGTH
                || carInstanceY.getPositionY() > Road.STOP_LINE_Y
                || TrafficLights.State.GREEN.equals(trafficLightsInstanceY.getState())
                || TrafficLights.State.YELLOW.equals(trafficLightsInstanceY.getState())
                && TrafficLights.State.GREEN.equals(trafficLightsInstanceY.getPrevState())
        ) {
            if (carInstanceY.getPositionY() < roadPicture.getHeight())
                carInstanceY.setPositionY(carInstanceY.getPositionY() + Car.SPEED);
            else
                carInstanceY.setPositionY(Car.START_CAR_Y_Y);
        }
    }

    private static void drawInstances() {
        roadPicture = roadInstance.getPicture();
        Graphics2D graphics = (Graphics2D) roadPicture.getGraphics();

        trafficLightsPictureX = trafficLightsInstanceX.getPicture();
        trafficLightsPictureY = trafficLightsInstanceY.getPicture();

        drawTrafficLights(graphics);
        drawCar(graphics);
    }

    private static void drawTrafficLights(Graphics2D graphics) {

        graphics.drawImage(trafficLightsPictureX,
                TrafficLights.getScalePictureOp(),
                TrafficLights.TRAFFIC_LIGHT_X_X,
                TrafficLights.TRAFFIC_LIGHT_X_Y);
        graphics.drawImage(trafficLightsPictureY,
                TrafficLights.getScalePictureOp(),
                TrafficLights.TRAFFIC_LIGHT_Y_X,
                TrafficLights.TRAFFIC_LIGHT_Y_Y);
    }

    private static void drawCar(Graphics2D graphics) {
        graphics.drawImage(carPicture, Car.getScalePictureOp(), carInstanceX.getPositionX(), Car.START_CAR_X_Y);
        graphics.drawImage(carPicture, Car.getRotateScalePictureOp(), Car.START_CAR_Y_X, carInstanceY.getPositionY());
    }


    private static void initDrawable() {
        carInstanceX = new Car();
        carInstanceY = new Car();
        roadInstance = new Road();
        trafficLightsInstanceX = new TrafficLights(TrafficLights.State.GREEN);
        trafficLightsInstanceY = new TrafficLights(TrafficLights.State.RED);

        carPicture = carInstanceX.getPicture();
    }

    private void setTrafficLightSwitcher() {
        new Thread(() -> {
            while (true) {
                try {
                    trafficLightsInstanceX.changeState();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    trafficLightsInstanceY.changeState();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
