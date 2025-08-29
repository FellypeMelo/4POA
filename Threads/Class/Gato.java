package Class;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Gato extends JLabel implements Runnable {
    
    private Thread thread;
    private String nome;
    private ImageIcon imagemGato;
    private int posX, posY;
    private boolean correndo = true;
    private static int posicao = 0;
    
    public Gato(String nome, String caminhoImagem, int posX, int posY) {
        this.nome = nome;
        this.posX = posX;
        this.posY = posY;
        
        carregarImagem(caminhoImagem);
        configurarVisual();
        iniciarThread();
    }
    
    // Método estático para resetar a contagem
    public static void resetarContagem() {
        posicao = 0;
    }
    
    private void carregarImagem(String caminho) {
        try {
            File arquivo = new File(caminho);
            if (arquivo.exists()) {
                BufferedImage imagemOriginal = ImageIO.read(arquivo);
                BufferedImage imagemRedimensionada = new BufferedImage(80, 60, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = imagemRedimensionada.createGraphics();
                
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.drawImage(imagemOriginal, 0, 0, 80, 60, null);
                g2d.dispose();
                
                imagemGato = new ImageIcon(imagemRedimensionada);
            } else {
                criarImagemFallback();
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem: " + caminho);
            criarImagemFallback();
        }
    }
    
    private void criarImagemFallback() {
        BufferedImage imagem = new BufferedImage(80, 60, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagem.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fundo circular
        g2d.setColor(Color.GRAY);
        g2d.fillOval(5, 5, 70, 50);
        
        // Borda
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(5, 5, 70, 50);
        
        // Texto
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.WHITE);
        g2d.drawString(nome, 20, 35);
        
        g2d.dispose();
        imagemGato = new ImageIcon(imagem);
    }
    
    private void configurarVisual() {
        setIcon(imagemGato);
        setBounds(posX, posY, 80, 60);
        setOpaque(false);
        setText(nome);
        setFont(new Font("Arial", Font.BOLD, 10));
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setVerticalAlignment(SwingConstants.BOTTOM);
    }
    
    private void iniciarThread() {
        thread = new Thread(this, nome);
        thread.start();
    }
    
    @Override
    public void run() {
        Random random = new Random();
        
        while (correndo && posX < 900) {
            // Movimento aleatório
            int movimento = random.nextInt(8) + 3; // 3-10 pixels
            posX += movimento;
            
            // Pequena variação Y
            int variacaoY = random.nextInt(5) - 2; // -2 a +2
            posY += variacaoY;
            
            // Manter na faixa
            if (posY < 30) posY = 30;
            if (posY > 450) posY = 450;
            
            // Atualizar posição
            SwingUtilities.invokeLater(() -> {
                setLocation(posX, posY);
            });
            
            // Verificar chegada
            if (posX >= 900) {
                posicao++;
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, 
                        posicao + "o Lugar: " + nome, 
                        "Resultado da Corrida", 
                        JOptionPane.INFORMATION_MESSAGE);
                });
                return;
            }
            
            try {
                Thread.sleep(random.nextInt(100) + 50); // 50-150ms
            } catch (InterruptedException e) {
                return;
            }
        }
    }
    
    public void parar() {
        correndo = false;
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        
        // Limpar recursos visuais
        setVisible(false);
        setIcon(null);
    }
    
    // Método para limpeza completa
    public void limpar() {
        parar();
        setText("");
        setBounds(0, 0, 0, 0);
    }
    
    public String getNome() {
        return nome;
    }
    
    public boolean isCorrendo() {
        return correndo;
    }
}
