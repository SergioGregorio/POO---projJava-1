package View;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {
    public HelpWindow(JFrame parent) {
        setTitle("Help - Shape Animation");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("How to Use the Application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setText(
                "Welcome to the Shape Animation application!\n\n" +
                "Features:\n" +
                "- **Open File:** Load a text file to view its content in the central text area.\n" +
                "- **Close File:** Clear the text area and make it transparent again.\n" +
                "- **Settings:** Change the color, shape, and speed of the background animation.\n" +
                "- **Help:** Access this help window or view system details.\n\n" +
                "Tips:\n" +
                "- Click and drag the window to move it.\n" +
                "- Use the settings options to customize your experience.\n" +
                "- Explore the different shapes and colors to see how the animation adapts."
        );
        helpText.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);
    }
}
