package Lab_3.TemplateMethod;

import Lab_3.TemplateMethod.Algorithms.ThrowBall;
import Lab_3.TemplateMethod.Algorithms.ThrowShapeAlgorithm;
import Lab_3.TemplateMethod.Algorithms.ThrowSquare;
import Lab_3.TemplateMethod.Algorithms.ThrowStar;
import Lab_3.TemplateMethod.Shape.Shape;

import java.awt.*;
import javax.swing.*;

public class AnimationField extends JFrame {

    private Shape currentFigure = Shape.BALL;
    private final DynamicPaintingPanel paintingPanel;

    private JComboBox<String> figureComboBox;
    private JPanel mainPanel;

    public AnimationField() {
        initComponents();

        figureComboBox.removeAllItems();
        figureComboBox.addItem("мяч");
        figureComboBox.addItem("квадрат");
        figureComboBox.addItem("звезда");
        mainPanel.setLayout(new BorderLayout());
        paintingPanel = new DynamicPaintingPanel();
        mainPanel.add(paintingPanel);
    }

    public void showAndStart() {
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {

        figureComboBox = new JComboBox<>();
        JLabel jLabel1 = new JLabel();
        JButton startButton = new JButton();
        JButton closeButton = new JButton();
        mainPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Анимация");

        figureComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        figureComboBox.addActionListener(evt -> figureComboBoxActionPerformed());

        jLabel1.setText("Следующей фигурой для броска будет:");

        startButton.setText("Пуск");
        startButton.addActionListener(evt -> startButtonActionPerformed());

        closeButton.setText("Закрыть");
        closeButton.addActionListener(evt -> closeButtonActionPerformed());

        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        mainPanel.setMaximumSize(new Dimension(320, 273));
        mainPanel.setMinimumSize(new Dimension(320, 273));
        mainPanel.setPreferredSize(new Dimension(320, 273));

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 316, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 269, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(startButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(closeButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(figureComboBox, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(figureComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startButton)
                                        .addComponent(closeButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void figureComboBoxActionPerformed() {
        String value = String.valueOf(figureComboBox.getModel().getSelectedItem());
        switch (value) {
            case "квадрат": {
                currentFigure = Shape.SQUARE;
                break;
            }
            case "звезда": {
                currentFigure = Shape.STAR;
                break;
            }
            default: {
                currentFigure = Shape.BALL;
                break;
            }
        }
    }

    private void startButtonActionPerformed() {
        ThrowShapeThread thread;
        ThrowShapeAlgorithm algorithm = null;
        switch (currentFigure) {
            case BALL: {
                algorithm = new ThrowBall(paintingPanel);
                break;
            }
            case SQUARE: {
                algorithm = new ThrowSquare(paintingPanel);
                break;
            }
            case STAR: {
                algorithm = new ThrowStar(paintingPanel);
                break;
            }
            default: {
                break;
            }
        }
        thread = new ThrowShapeThread(algorithm);
        thread.start();
    }

    private void closeButtonActionPerformed() {
        System.exit(0);
    }
}
