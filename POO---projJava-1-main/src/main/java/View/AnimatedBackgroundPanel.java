package View;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class AnimatedBackgroundPanel extends JPanel implements Runnable {
    private int x = 0; // Controle de movimento horizontal
    private int waveHeight = 10; // Altura da onda
    private Color waveColor = new Color(0, 153, 76); // Cor das ondas
    private Thread animationThread;
    private volatile boolean running = true; // Flag para controlar o loop
    private Color backgroundColor = Color.WHITE; // Cor de fundo padrão
    private int animationSpeed = 100; // Velocidade de animação padrão

    public AnimatedBackgroundPanel() {
        // Cria e inicia a thread de animação
        animationThread = new Thread(this);
        animationThread.start();
    }

    // Método para alterar a cor da animação (ondas)
    public void setWaveColor(Color color) {
        this.waveColor = color; // Atualiza a cor das ondas
        repaint(); // Redesenha o painel
    }

    // Método para alterar a cor do fundo, se necessário
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color; // Atualiza a cor de fundo
        repaint(); // Redesenha o painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar o fundo primeiro
        g.setColor(backgroundColor); // Definir a cor de fundo
        g.fillRect(0, 0, getWidth(), getHeight()); // Preencher o fundo
        
        // Desenhar as ondas (barra animada) sobre o fundo
        g.setColor(waveColor); // Definir a cor das ondas
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Desenha a barra com efeito de onda
        for (int i = 0; i < panelWidth; i += 50) {
            int yOffset = (int) (waveHeight * Math.sin((i + x) * 0.05));
            g.fillRect(i, panelHeight - 50 + yOffset, 50, 50); // Barra animada
        }

        // **Desenhar o texto por último**, após a animação
        g.setColor(Color.BLACK); // Cor do texto (pode ajustar conforme necessário)
        g.drawString("Seu Texto Aqui", 20, 30); // Exemplo de texto que aparece na tela
    }

    @Override
    public void run() {
        while (running) {
            x += 5; // Velocidade de movimento da onda
            repaint();
            try {
                Thread.sleep(animationSpeed); // Intervalo da animação
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopAnimation() {
        running = false;
        animationThread.interrupt(); // Interrompe a thread
    }

    public void setWaveHeight(int height) {
        this.waveHeight = height; // Atualiza a altura da onda
        repaint();
    }

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed; // Atualiza a velocidade da animação
    }
}
