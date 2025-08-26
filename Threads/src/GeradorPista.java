package Threads.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GeradorPista {
    
    public static ImageIcon gerarPistaCorrida() {
        int largura = 1200;
        int altura = 500;
        
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagem.createGraphics();
        
        // Configurar qualidade da renderização
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        // Fundo da pista (terra)
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(0, 0, largura, altura);
        
        // Adicionar textura ao fundo
        adicionarTextura(g2d, largura, altura);
        
        // Linhas da pista (faixas)
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        // Linhas horizontais para separar as faixas
        for (int i = 1; i <= 3; i++) {
            int y = i * 100;
            g2d.drawLine(0, y, largura, y);
        }
        
        // Linhas verticais para marcar o progresso (mais suaves)
        g2d.setColor(new Color(255, 255, 255, 100)); // Branco semi-transparente
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        for (int i = 1; i <= 12; i++) {
            int x = i * 100;
            g2d.drawLine(x, 0, x, altura);
        }
        
        // Linha de chegada mais visível
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(largura - 50, 0, largura - 50, altura);
        
        // Adicionar texto "CHEGADA" com fundo
        g2d.setColor(Color.BLACK);
        g2d.fillRect(largura - 150, 10, 140, 30);
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("🏁 CHEGADA", largura - 140, 30);
        
        // Adicionar decorações (árvores, nuvens)
        desenharDecoracoes(g2d, largura, altura);
        
        // Adicionar bordas da pista
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(0, 0, largura, altura);
        
        g2d.dispose();
        
        return new ImageIcon(imagem);
    }
    
    private static void adicionarTextura(Graphics2D g2d, int largura, int altura) {
        // Adicionar pequenos pontos para textura
        g2d.setColor(new Color(160, 82, 45, 50));
        for (int i = 0; i < largura; i += 20) {
            for (int j = 0; j < altura; j += 20) {
                if (Math.random() > 0.7) {
                    g2d.fillOval(i, j, 3, 3);
                }
            }
        }
    }
    
    private static void desenharDecoracoes(Graphics2D g2d, int largura, int altura) {
        // Nuvens no céu com melhor qualidade
        g2d.setColor(new Color(255, 255, 255, 180));
        desenharNuvem(g2d, 100, 50, 80, 50);
        desenharNuvem(g2d, 400, 80, 70, 40);
        desenharNuvem(g2d, 700, 60, 90, 55);
        desenharNuvem(g2d, 1000, 70, 75, 45);
        
        // Árvores nas laterais com melhor desenho
        for (int i = 0; i < 5; i++) {
            int x = i * 200 + 50;
            desenharArvore(g2d, x, altura - 80);
        }
    }
    
    private static void desenharNuvem(Graphics2D g2d, int x, int y, int largura, int altura) {
        // Desenhar nuvem com múltiplos círculos sobrepostos
        g2d.fillOval(x, y, largura/2, altura/2);
        g2d.fillOval(x + largura/3, y, largura/2, altura/2);
        g2d.fillOval(x + largura/2, y + altura/4, largura/2, altura/2);
        g2d.fillOval(x, y + altura/4, largura/2, altura/2);
    }
    
    private static void desenharArvore(Graphics2D g2d, int x, int y) {
        // Tronco
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(x, y, 25, 80);
        
        // Copa da árvore
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillOval(x - 20, y - 60, 65, 80);
        
        // Detalhes da árvore
        g2d.setColor(new Color(0, 100, 0));
        g2d.fillOval(x - 15, y - 50, 55, 60);
    }
}
