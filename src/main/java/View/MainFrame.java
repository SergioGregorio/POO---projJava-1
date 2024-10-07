/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author
 */
public class MainFrame extends javax.swing.JFrame {

    private PatternPanel patternPanel;
    private int speed = 15;
    private String selectedShape = "Square";

    /**
     * Cria um novo formulário principal
     */
    public MainFrame() {
        initComponents();
        setSize(800, 600);
        setMinimumSize(new java.awt.Dimension(400, 300));
        patternPanel = new PatternPanel(speed, selectedShape);
        setContentPane(patternPanel);
        revalidate();
        repaint();
    }

    // Classe abstrata para formas geométricas
    abstract class Shape {
        int x, y, dx, dy; // Posição e direção

        public Shape(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        public abstract void draw(Graphics g);

        // Atualiza a posição e verifica colisão com as bordas
        public void move(int width, int height) {
            x += dx;
            y += dy;

            if (x <= 0 || x >= width) {
                dx = -dx; // Inverte direção horizontal
            }
            if (y <= 0 || y >= height) {
                dy = -dy; // Inverte direção vertical
            }
        }
    }

    // Classe para o quadrado
    class Square extends Shape {
        int size;

        public Square(int x, int y, int size, int dx, int dy) {
            super(x, y, dx, dy);
            this.size = size;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, size, size);
        }
    }

    // Classe para o círculo
    class Circle extends Shape {
        int radius;

        public Circle(int x, int y, int radius, int dx, int dy) {
            super(x, y, dx, dy);
            this.radius = radius;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x, y, radius, radius);
        }
    }

    // Classe para o triângulo
    class Triangle extends Shape {
        int size;

        public Triangle(int x, int y, int size, int dx, int dy) {
            super(x, y, dx, dy);
            this.size = size;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.GREEN);
            int[] xPoints = {x, x + size / 2, x - size / 2};
            int[] yPoints = {y, y + size, y + size};
            g.fillPolygon(new Polygon(xPoints, yPoints, 3));
        }
    }

    // Painel para desenhar as formas e o fundo
    class PatternPanel extends JPanel {
        private Shape[] shapes;
        private Timer timer;
        private BufferedImage backgroundImage;

        public PatternPanel(int speed, String selectedShape) {
            shapes = new Shape[]{
                createShape(selectedShape, 50, 50, 40, 3, 3),
                createShape(selectedShape, 100, 100, 40, 4, 2),
                createShape(selectedShape, 200, 200, 40, 2, 3)
            };

            // Inicializa o timer com a velocidade selecionada
            timer = new Timer(speed, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Shape shape : shapes) {
                        shape.move(getWidth(), getHeight());
                    }
                    repaint(); // Redesenha o painel
                }
            });
            timer.start(); // Inicia o timer
        }

        private Shape createShape(String shapeType, int x, int y, int size, int dx, int dy) {
            switch (shapeType) {
                case "Circle":
                    return new Circle(x, y, size, dx, dy);
                case "Triangle":
                    return new Triangle(x, y, size, dx, dy);
                default:
                    return new Square(x, y, size, dx, dy);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Desenha a imagem de fundo, se existir
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
            // Desenha as formas geométricas
            for (Shape shape : shapes) {
                shape.draw(g);
            }
        }

        // Método para ajustar a velocidade do timer
        public void setSpeed(int speed) {
            timer.setDelay(speed);
        }

        // Método para definir a imagem de fundo
        public void setBackgroundImage(BufferedImage img) {
            this.backgroundImage = img;
            repaint();
        }

        // Método para remover a imagem de fundo
        public void clearBackgroundImage() {
            this.backgroundImage = null;
            repaint();
        }

        // Método para ajustar a cor de fundo
        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);
            repaint();
        }

        // Método para atualizar a forma selecionada
        public void updateShapes(String shapeType) {
            for (int i = 0; i < shapes.length; i++) {
                shapes[i] = createShape(shapeType, shapes[i].x, shapes[i].y, 40, 3 + i, 2 + i); // Atualiza a forma
            }
            repaint(); // Redesenha o painel
        }
    }

    /**
     * Este método é chamado a partir do construtor para inicializar o formulário.
     * WARNING: Do NOT modify this code. O conteúdo deste método é sempre
     * regenerado pelo Editor de Formulários.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar(); // Cria uma barra de menu
        jMenu1 = new javax.swing.JMenu(); // Cria o primeiro menu (File)
        btnOpen = new javax.swing.JMenuItem(); // Item de menu para abrir arquivo
        btnClose = new javax.swing.JMenuItem(); // Item de menu para fechar arquivo
        btnExit = new javax.swing.JMenuItem(); // Item de menu para sair
        jMenu2 = new javax.swing.JMenu(); // Cria o segundo menu (Settings)
        jMenuItem1 = new javax.swing.JMenu(); // Cria um sub-menu para Patterns
        jMenuItemSquare = new javax.swing.JMenuItem(); // Item de menu para quadrado
        jMenuItemCircle = new javax.swing.JMenuItem(); // Item de menu para círculo
        jMenuItemTriangle = new javax.swing.JMenuItem(); // Item de menu para triângulo
        jMenuItem3 = new javax.swing.JMenuItem(); // Item de menu para Speed
        jMenu4 = new javax.swing.JMenu(); // Cria um sub-menu para Colors
        jMenuItem2 = new javax.swing.JMenuItem(); // Item de menu para cor azul
        jMenuItem4 = new javax.swing.JMenuItem(); // Item de menu para cor vermelha
        jMenuItem5 = new javax.swing.JMenuItem(); // Item de menu para cor verde
        btnHelp = new javax.swing.JMenu(); // Cria o menu de Help

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jMenu1.setText("File"); // Define o texto do menu File

        // Configura o item de menu para abrir arquivo
        btnOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        btnOpen.setText("Abrir Imagem");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jMenu1.add(btnOpen); // Adiciona o item de menu ao menu File

        // Configura o item de menu para fechar arquivo
        btnClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        btnClose.setText("Fechar Imagem");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jMenu1.add(btnClose);

        // Configura o item de menu para sair
        btnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, 0));
        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenu1.add(btnExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configuração");

        // Configura o sub-menu para Patterns
        jMenuItem1.setText("Formas");
        jMenuItemSquare.setText("Quadrado");
        jMenuItemSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedShape = "Square";
                patternPanel.updateShapes(selectedShape);
            }
        });
        jMenuItem1.add(jMenuItemSquare);

        jMenuItemCircle.setText("Circulo");
        jMenuItemCircle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedShape = "Circle";
                patternPanel.updateShapes(selectedShape);
            }
        });
        jMenuItem1.add(jMenuItemCircle);

        jMenuItemTriangle.setText("Triangulo");
        jMenuItemTriangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedShape = "Triangle";
                patternPanel.updateShapes(selectedShape);
            }
        });
        jMenuItem1.add(jMenuItemTriangle);

        jMenu2.add(jMenuItem1);

        // Configura o item de menu para Speed
        jMenuItem3.setText("Velocidade");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String input = JOptionPane.showInputDialog("Insira a velocidade (milisegundos):");
                try {
                    int newSpeed = Integer.parseInt(input);
                    if (newSpeed > 0) {
                        speed = newSpeed;
                        patternPanel.setSpeed(speed);
                    } else {
                        JOptionPane.showMessageDialog(null, "Velocidade deve ser maior que zero.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Insira um número.");
                }
            }
        });
        jMenu2.add(jMenuItem3);

        jMenu4.setText("Cores");

        // Configura o item de menu para cor azul
        jMenuItem2.setText("Azul");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternPanel.setBackground(Color.decode("#22d3ee"));
            }
        });
        jMenu4.add(jMenuItem2);

        // Configura o item de menu para cor vermelha
        jMenuItem4.setText("Vermelho"); // Define o texto do item de menu Red
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternPanel.setBackground(Color.decode("#f87171"));
            }
        });
        jMenu4.add(jMenuItem4);

        // Configura o item de menu para cor verde
        jMenuItem5.setText("Verde");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternPanel.setBackground(Color.decode("#4ade80"));
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu2.add(jMenu4);
        jMenuBar1.add(jMenu2);

        btnHelp.setText("Ajuda");
        jMenuBar1.add(btnHelp);

        setJMenuBar(jMenuBar1);
    }// </editor-fold>//GEN-END:initComponents

    // Método chamado ao clicar no botão de abrir imagem de fundo
    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                patternPanel.setBackgroundImage(img);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
            }
        }
    }

    // Método chamado ao clicar no botão de limpar imagem de fundo
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        patternPanel.clearBackgroundImage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorials/jdesktop/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true); // Cria e exibe o formulário principal
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnClose; // Item de menu para fechar imagem
    private javax.swing.JMenuItem btnExit; // Item de menu para sair
    private javax.swing.JMenuItem btnHelp; // Item de menu para ajuda
    private javax.swing.JMenuItem btnOpen; // Item de menu para abrir imagem
    private javax.swing.JMenuItem jMenuItem2; // Item de menu para cor azul
    private javax.swing.JMenuItem jMenuItem3; // Item de menu para velocidade
    private javax.swing.JMenuItem jMenuItem4; // Item de menu para cor vermelha
    private javax.swing.JMenuItem jMenuItem5; // Item de menu para cor verde
    private javax.swing.JMenu jMenu1; // Menu File
    private javax.swing.JMenu jMenu2; // Menu Settings
    private javax.swing.JMenu jMenu4; // Sub-menu Colors
    private javax.swing.JMenu jMenuItem1; // Sub-menu Patterns
    private javax.swing.JMenuItem jMenuItemCircle; // Item de menu para círculo
    private javax.swing.JMenuItem jMenuItemSquare; // Item de menu para quadrado
    private javax.swing.JMenuItem jMenuItemTriangle; // Item de menu para triângulo
    private javax.swing.JMenuBar jMenuBar1; // Barra de menu
    // End of variables declaration//GEN-END:variables
}
