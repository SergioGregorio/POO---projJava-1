package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainFrame extends JFrame {
    private AnimatedBackgroundPanel animationPanel;
    private JTextArea textArea;
    private Color shapeColor = Color.RED;
    private int animationSpeed = 20;
    private String shapeType = "Circle";

    private JPanel textPanel;

    public MainFrame() {
        setTitle("Shape Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        layeredPane.setLayout(null);

        animationPanel = new AnimatedBackgroundPanel();
        animationPanel.setShapeColor(shapeColor);
        animationPanel.setAnimationSpeed(animationSpeed);
        animationPanel.setShapeType(shapeType);
        animationPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(animationPanel, Integer.valueOf(0));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(scrollPane, BorderLayout.CENTER);
        layeredPane.add(textPanel, Integer.valueOf(1));

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                animationPanel.setBounds(0, 0, getWidth(), getHeight());
                resizeTextPanel();
            }
        });

        resizeTextPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open File");
        JMenuItem closeItem = new JMenuItem("Close File");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(MainFrame.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    readFile(filePath);
                }
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textArea.setOpaque(false);
                textPanel.setBackground(new Color(0, 0, 0, 0));
                textPanel.repaint();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.stopAnimation();
                System.exit(0);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu configMenu = new JMenu("Settings");

        JMenuItem colorItem = new JMenuItem("Change Color");
        colorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(MainFrame.this, "Choose Shape Color", shapeColor);
                if (newColor != null) {
                    shapeColor = newColor;
                    animationPanel.setShapeColor(shapeColor);
                }
            }
        });

        JMenuItem shapeItem = new JMenuItem("Change Shape");
        shapeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] shapes = {"Circle", "Square", "Triangle"};
                String selectedShape = (String) JOptionPane.showInputDialog(
                        MainFrame.this,
                        "Select Shape:",
                        "Choose Shape",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        shapes,
                        shapes[0]);

                if (selectedShape != null) {
                    shapeType = selectedShape;
                    animationPanel.setShapeType(shapeType);
                }
            }
        });

        JMenuItem speedItem = new JMenuItem("Change Speed");
        speedItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] speeds = {"Slow", "Normal", "Fast"};
                String selectedSpeed = (String) JOptionPane.showInputDialog(
                        MainFrame.this,
                        "Select Animation Speed:",
                        "Choose Speed",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        speeds,
                        speeds[1]);

                if (selectedSpeed != null) {
                    switch (selectedSpeed) {
                        case "Slow":
                            animationSpeed = 40;
                            break;
                        case "Normal":
                            animationSpeed = 20;
                            break;
                        case "Fast":
                            animationSpeed = 5;
                            break;
                    }
                    animationPanel.setAnimationSpeed(animationSpeed);
                }
            }
        });

        configMenu.add(colorItem);
        configMenu.add(shapeItem);
        configMenu.add(speedItem);
        menuBar.add(configMenu);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHelpDialog();
            }
        });

        JMenuItem systemInfoItem = new JMenuItem("System Information");
        systemInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSystemInfo();
            }
        });

        helpMenu.add(helpItem);
        helpMenu.add(systemInfoItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        startAnimation();
    }

    private void resizeTextPanel() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        int panelWidth = frameWidth / 2;
        int panelHeight = frameHeight / 2;

        int x = (frameWidth - panelWidth) / 2;
        int y = (frameHeight - panelHeight) / 2;

        textPanel.setBounds(x, y, panelWidth, panelHeight);
    }

    private void readFile(String filePath) {
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                StringBuilder content = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }
                return content.toString();
            }

            @Override
            protected void done() {
                try {
                    String content = get();
                    textArea.setText(content);
                    textArea.setOpaque(true);
                    textArea.setBackground(new Color(255, 255, 255, 200));
                    textPanel.repaint();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error opening file: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute();
    }

    private void startAnimation() {
        animationPanel.startAnimation();
    }

    private void openHelpDialog() {
        JDialog helpDialog = new JDialog(this, "Help", true);
        helpDialog.setSize(400, 300);
        helpDialog.setLocationRelativeTo(this);

        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setText(
                "Welcome to the Shape Animation application!\n\n" +
                "Features:\n" +
                "- Open File: Loads a text file and displays its content in the central text area.\n" +
                "- Close File: Clears the text area and returns to viewing the animation.\n" +
                "- Settings:\n" +
                "  * Change Color: Modifies the color of the animated shape.\n" +
                "  * Change Shape: Choose between Circle, Square, or Triangle.\n" +
                "  * Change Speed: Adjusts the speed of the animation (Slow, Normal, Fast).\n\n" +
                "Help:\n" +
                "- This application allows you to view an animated shape while displaying the content of text files.\n" +
                "- Use the menus to customize the animation and manage files.\n\n" +
                "Developed by [Your Name]."
        );

        JScrollPane helpScrollPane = new JScrollPane(helpText);
        helpScrollPane.setBorder(BorderFactory.createEmptyBorder());

        helpDialog.add(helpScrollPane);

        helpDialog.setVisible(true);
    }

    private void showSystemInfo() {
        String appInfo = "Shape Animation\n" +
                "Version: 1.0.0\n" +
                "Author: Group E\n" +
                "Description: This application displays an animated shape in the background while allowing the viewing of text files in a centered area.";

        JOptionPane.showMessageDialog(this,
                appInfo,
                "System Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
