package Lab_3.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Face extends JFrame implements FaceSubject {

    private final String LEFT_CLOSE_EYE = "resources/face/left_close_eye.png";
    private final String LEFT_OPEN_EYE = "resources/face/left_open_eye.png";
    private final String RIGHT_CLOSE_EYE = "resources/face/right_close_eye.png";
    private final String RIGHT_OPEN_EYE = "resources/face/right_open_eye.png";
    private final String BLACK_NOSE = "resources/face/black_nose.png";
    private final String RED_NOSE = "resources/face/red_nose.png";
    private final String MOUTH_SMILE = "resources/face/smile.png";
    private final String MOUTH_NO_SMILE = "resources/face/not_smile.png";
    private final String FACE = "resources/face/face.png";

    private final LinkedList<FaceObserver> observers = new LinkedList<>();

    private boolean isRightEyeOpen = true;
    private boolean isLeftEyeOpen = true;
    private boolean isNoseRed = false;
    private boolean isMouthSmiling = false;

    private String LEFT_EYE = LEFT_OPEN_EYE;
    private String RIGHT_EYE = RIGHT_OPEN_EYE;
    private String NOSE = BLACK_NOSE;
    private String MOUTH = MOUTH_NO_SMILE;

    private JPanel facePanel;

    public Face() {
        initWindow();
        updatePicture();
        initComponents();
    }

    private void initWindow() {
        facePanel = new JPanel();
        add(facePanel);
        setBounds(100, 100, 1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setAllListener(e.getPoint());
                System.out.println(e.getPoint());
            }
        });
    }

    private void setAllListener(Point point) {
        int x = point.x;
        int y = point.y;
        if (x > 153 && x < 230 && y > 140 && y < 276) {
            leftEyeLabelMouseClicked();
        } else if (x > 291 && x < 374 && y > 140 && y < 276) {
            rightEyeLabelMouseClicked();
        } else if (x > 226 && x < 303 && y > 282 && y < 318) {
            noseLabelMouseClicked();
        } else if (x > 195 && x < 325 && y > 354 && y < 405) {
            mouthLabelMouseClicked();
        }
    }

    public static BufferedImage getPicture(String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private void drawPicture(String fileName, Graphics2D graphics) {
        try {
            BufferedImage i = ImageIO.read(new File(fileName));
            graphics.drawImage(i, null, 0, 0);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void mouthLabelMouseClicked() {
        notifyObservers(Feature.MOUTH);
    }

    private void noseLabelMouseClicked() {
        notifyObservers(Feature.NOSE);
    }

    private void leftEyeLabelMouseClicked() {
        notifyObservers(Feature.LEFT_EYE);
    }

    private void rightEyeLabelMouseClicked() {
        notifyObservers(Feature.RIGHT_EYE);
    }


    @Override
    public void attach(FaceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(FaceObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Feature f) {
        observers.forEach((o) -> o.update(f));
    }

    @Override
    public boolean isMouthSmiling() {
        return isMouthSmiling;
    }

    @Override
    public boolean isNoseColored() {
        return isNoseRed;
    }

    @Override
    public boolean isEyeOpen(Side side) {
        if (Side.LEFT == side) {
            return isLeftEyeOpen;
        } else {
            return isRightEyeOpen;
        }
    }

    @Override
    public void updateMouth(boolean toSmile) {
        MOUTH = toSmile ? MOUTH_SMILE : MOUTH_NO_SMILE;
        isMouthSmiling = toSmile;
        updatePicture();
    }

    @Override
    public void updateNose(boolean toColored) {
        NOSE = toColored ? RED_NOSE : BLACK_NOSE;
        isNoseRed = toColored;
        updatePicture();
    }

    private void updatePicture() {
        BufferedImage facePicture = getPicture(FACE);
        Graphics2D graphics = (Graphics2D) facePicture.getGraphics();
        drawPicture(LEFT_EYE, graphics);
        drawPicture(RIGHT_EYE, graphics);
        drawPicture(NOSE, graphics);
        drawPicture(MOUTH, graphics);

        JLabel faceLabel = new JLabel(new ImageIcon(facePicture));
        facePanel.removeAll();
        facePanel.add(faceLabel);

        repaint();
        revalidate();
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void updateEye(Side side, boolean toClosed) {
        if (Side.LEFT == side) {
            LEFT_EYE = toClosed ? LEFT_CLOSE_EYE : LEFT_OPEN_EYE;
            isLeftEyeOpen = !toClosed;
        } else {
            RIGHT_EYE = toClosed ? RIGHT_CLOSE_EYE : RIGHT_OPEN_EYE;
            isRightEyeOpen = !toClosed;
        }
        updatePicture();
    }


    public void showFace() {
        setVisible(true);
    }

}
