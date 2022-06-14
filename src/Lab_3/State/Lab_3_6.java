package Lab_3.State;

import javax.swing.*;
import java.awt.*;

public class Lab_3_6 {

    private static JPanel imagePanel;
    private static final Student student = new Student();

    public static void main(String[] args) {
        initWindow();
    }

    private static void initWindow() {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        imagePanel = new JPanel();
        imagePanel.setBounds(0, 0, 500, 500);
        imagePanel.add(label);

        Button sleepBTN = new Button("СЕМЕСТР");
        sleepBTN.setBounds(0, 500, 150, 100);
        sleepBTN.addActionListener(e -> {
            student.changeActivity(State.SLEEP);
            updatePic();
        });

        Button prayingBTN = new Button("CЕССИЯ");
        prayingBTN.setBounds(175, 500, 150, 100);
        prayingBTN.addActionListener(e -> {
            student.changeActivity(State.PRAYING);
            updatePic();
        });

        Button funnyBTN = new Button("КАНИКУЛЫ");
        funnyBTN.setBounds(350, 500, 150, 100);
        funnyBTN.addActionListener(e -> {
            student.changeActivity(State.FUNNY);
            updatePic();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.add(imagePanel);
        frame.add(sleepBTN);
        frame.add(prayingBTN);
        frame.add(funnyBTN);
    }

    private static void updatePic() {
        imagePanel.removeAll();
        System.out.println(student.activity.toString());
        JLabel label = new JLabel(new ImageIcon(student.getImage()));
        imagePanel.add(label);
        imagePanel.validate();

    }


}
