package View;

import javax.swing.*;
import java.awt.*;

public class AnimatedBackgroundPanel extends JPanel {
    private Color shapeColor = Color.RED;
    private int diameter = 50;
    private int animationSpeed = 20;
    private String shapeType = "Circle";

    private int x = 0;
    private int y = 0;
    private int xDirection = 1;
    private int yDirection = 1;
    private Timer timer;

    public AnimatedBackgroundPanel() {
        setPreferredSize(new Dimension(800, 600));
        startAnimation();
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
        repaint();
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
        repaint();
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
        restartAnimation();
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
        repaint();
    }

    public void startAnimation() {
        timer = new Timer(animationSpeed, e -> {
            moveShape();
            repaint();
        });
        timer.start();
    }

    public void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void restartAnimation() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(animationSpeed, e -> {
            moveShape();
            repaint();
        });
        timer.start();
    }

    private void moveShape() {
        x += xDirection;
        y += yDirection;

        if (x + diameter > getWidth() || x < 0) {
            xDirection = -xDirection;
        }
        if (y + diameter > getHeight() || y < 0) {
            yDirection = -yDirection;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(shapeColor);

        switch (shapeType) {
            case "Circle":
                g.fillOval(x, y, diameter, diameter);
                break;
            case "Square":
                g.fillRect(x, y, diameter, diameter);
                break;
            case "Triangle":
                int[] xPoints = {x, x + diameter / 2, x + diameter};
                int[] yPoints = {y + diameter, y, y + diameter};
                g.fillPolygon(xPoints, yPoints, 3);
                break;
        }
    }
}
