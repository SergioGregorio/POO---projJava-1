/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    
    private final AnimatedBackgroundPanel animatedBackgroundPanel;
    private JProgressBar progressBar;
    private JTextArea textArea;
    private JPanel mainPanel;
    
    private String selectedPattern = "Pattern 1";
    private Color selectedColor = Color.WHITE;
    private int animationSpeed = 1000;
    
    public mainFrame() {
        // Configurações da janela
        setTitle("Animação de Fundo");
        setSize(800, 600); // Define um tamanho padrão para a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria e adiciona o painel animado
        animatedBackgroundPanel = new AnimatedBackgroundPanel();
        add(animatedBackgroundPanel, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
        initComponents();
        setupUI();
        addColorMenuItems();
    }
    
    private void setupUI() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.PAGE_END);

        setContentPane(mainPanel);
    }

    private void showHelpDialog() {
        String helpMessage = """
                             Menu Help:
                             Help: Instructions on how to use the application:
                             1. Load a file to display its contents.
                             2. Adjust the speed using the slider.
                             3. Change colors via the 'Colors' menu.
                             4. View the progress of file loading with the progress bar.
                             About: Shows information about the application (such as version and authors).
                             """;
        JOptionPane.showMessageDialog(null, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAboutDialog() {
        String aboutMessage = """
                              Application Version 1.0
                              Authors:
                              Jose Vitor Dutra Antonio 187174
                              S\u00e9rgio Carlos de Sousa Greg\u00f3rio Junior 195405
                              Nasser Nasser Fares 196894
                              Marcelo Expedito Costa de Oliveira 248007
                              Igor Akaida 259741
                              Maria Eduarda de Souza Gomes 260844
                              """;
        JOptionPane.showMessageDialog(null, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void startAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Animation in progress...");
                    try {
                        Thread.sleep(animationSpeed);
                    } catch (InterruptedException e) {
                        break; // Interrompe se a thread for interrompida
                    }
                }
            }
        }).start();
    }

    private void loadFile(File file) {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    var progress = 0;
                    long fileSize = file.length();
                    long bytesRead = 0;

                    textArea.setText("");
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                        bytesRead += line.length() + 1; // +1 para a quebra de linha
                        progress = (int) ((bytesRead * 100) / fileSize);
                        publish(progress);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (Integer chunk : chunks) {
                    progressBar.setValue(chunk);
                }
            }

            @Override
            protected void done() {
                progressBar.setValue(100);
                JOptionPane.showMessageDialog(null, "File loaded successfully.");
            }
        };
        worker.execute();
    }
    
/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        btnOpen = new javax.swing.JMenuItem();
        btnClose = new javax.swing.JMenuItem();
        btnExit = new javax.swing.JMenuItem();
        Settings = new javax.swing.JMenu();
        Patterns = new javax.swing.JMenuItem();
        Speed = new javax.swing.JMenuItem();
        Colors = new javax.swing.JMenu();
        Blue = new javax.swing.JMenuItem();
        Red = new javax.swing.JMenuItem();
        Green = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        File.setText("File");

        btnOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        btnOpen.setText("Open File");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        File.add(btnOpen);

        btnClose.setText("Close File");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        File.add(btnClose);

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        File.add(btnExit);

        jMenuBar1.add(File);

        Settings.setText("Settings");

        Patterns.setText("Patterns");
        Patterns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatternsActionPerformed(evt);
            }
        });
        Settings.add(Patterns);

        Speed.setText("Speed");
        Speed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpeedActionPerformed(evt);
            }
        });
        Settings.add(Speed);

        Colors.setText("Colors");

        Blue.setText("Blue");
        Blue.setText("Blue");
        Colors.add(Blue);

        Red.setText("Red");
        Colors.add(Red);

        Green.setText("Green");
        Colors.add(Green);

        Settings.add(Colors);

        jMenuBar1.add(Settings);

        Help.setText("Help");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        // Item "Ajuda"
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(e -> showHelpDialog());
        Help.add(helpItem);

        // Item "Sobre"
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        Help.add(aboutItem);

        jMenuBar1.add(Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PatternsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatternsActionPerformed
        String[] options = {"Pattern 1", "Pattern 2", "Pattern 3"};
        selectedPattern = (String) JOptionPane.showInputDialog(
                null,
                "Choose a pattern: ",
                "Patterns",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selectedPattern != null) {
            JOptionPane.showMessageDialog(null, "You chose: " + selectedPattern);
            applyPattern();
        }
    }//GEN-LAST:event_PatternsActionPerformed
    
    private void applyPattern() {
        switch (selectedPattern) {
            case "Pattern 1" -> mainPanel.setBackground(Color.LIGHT_GRAY);
            case "Pattern 2" -> mainPanel.setBackground(Color.BLUE);
            case "Pattern 3" -> mainPanel.setBackground(Color.GREEN);
        }
    }
    
    private void addColorMenuItems() {
        Blue.addActionListener(e -> setColor(Color.BLUE));
        Red.addActionListener(e -> setColor(Color.RED));
        Green.addActionListener(e -> setColor(Color.GREEN));
    }

    private void setColor(Color color) {
        selectedColor = color;
        mainPanel.setBackground(selectedColor);
    }
    
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        textArea.setText(""); // Limpa a área de texto
        progressBar.setValue(0); // Reseta a barra de progresso
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadFile(selectedFile);
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void SpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpeedActionPerformed
        String[] speedOptions = {"Slow", "Medium", "Fast"};
        String selectedSpeed = (String) JOptionPane.showInputDialog(
                null,
                "Choose the animation speed:",
                "Speed",
                JOptionPane.QUESTION_MESSAGE,
                null,
                speedOptions,
                speedOptions[1]); // Define "Medium" como padrão

        if (selectedSpeed != null) {
            switch (selectedSpeed) {
                case "Slow" -> animationSpeed = 2000; // 2 segundos
                case "Medium" -> animationSpeed = 1000; // 1 segundo
                case "Fast" -> animationSpeed = 500; // 0.5 segundos
            }
            JOptionPane.showMessageDialog(null, "Speed adjusted to: " + selectedSpeed);
            startAnimation();
        }
    }//GEN-LAST:event_SpeedActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HelpActionPerformed


    public static void main(String args[]) {
    // Inicia a thread personalizada
    MyThread myThread = new MyThread();
    Thread thread = new Thread(myThread);
    thread.start();

    // Inicia a interface gráfica
    SwingUtilities.invokeLater(mainFrame::new // Cria e exibe a janela principal
    );
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Blue;
    private javax.swing.JMenu Colors;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Green;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem Patterns;
    private javax.swing.JMenuItem Red;
    private javax.swing.JMenu Settings;
    private javax.swing.JMenuItem Speed;
    private javax.swing.JMenuItem btnClose;
    private javax.swing.JMenuItem btnExit;
    private javax.swing.JMenuItem btnOpen;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
