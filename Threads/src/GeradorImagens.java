package Threads.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GeradorImagens {
    
    public static ImageIcon gerarGatoLaranja() {
        return carregarImagemGato("Threads/src/imagens/gato1.jpg", 80, 60, "GATO 1");
    }
    
    public static ImageIcon gerarGatoCinza() {
        return carregarImagemGato("Threads/src/imagens/gato2.jpg", 80, 60, "GATO 2");
    }
    
    public static ImageIcon gerarGatoPreto() {
        return carregarImagemGato("Threads/src/imagens/gato3.jpg", 80, 60, "GATO 3");
    }
    
    private static ImageIcon carregarImagemGato(String caminho, int largura, int altura, String texto) {
        try {
            // Verificar se o arquivo existe
            File arquivo = new File(caminho);
            if (!arquivo.exists()) {
                System.err.println("Arquivo não encontrado: " + caminho);
                return criarImagemFallback(largura, altura, texto);
            }
            
            // Carregar a imagem original
            BufferedImage imagemOriginal = ImageIO.read(arquivo);
            if (imagemOriginal == null) {
                System.err.println("Não foi possível ler a imagem: " + caminho);
                return criarImagemFallback(largura, altura, texto);
            }
            
            // Redimensionar para o tamanho desejado
            BufferedImage imagemRedimensionada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagemRedimensionada.createGraphics();
            
            // Configurar qualidade da renderização
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Desenhar a imagem redimensionada
            g2d.drawImage(imagemOriginal, 0, 0, largura, altura, null);
            g2d.dispose();
            
            System.out.println("Imagem carregada com sucesso: " + caminho);
            return new ImageIcon(imagemRedimensionada);
            
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem: " + caminho);
            e.printStackTrace();
            
            // Fallback: criar uma imagem simples se não conseguir carregar
            return criarImagemFallback(largura, altura, texto);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar imagem: " + caminho);
            e.printStackTrace();
            
            return criarImagemFallback(largura, altura, texto);
        }
    }
    
    private static ImageIcon criarImagemFallback(int largura, int altura, String texto) {
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagem.createGraphics();
        
        // Configurar qualidade da renderização
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenhar fundo circular simples
        g2d.setColor(Color.GRAY);
        g2d.fillOval(5, 5, largura - 10, altura - 10);
        
        // Desenhar borda
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(5, 5, largura - 10, altura - 10);
        
        // Adicionar texto
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.WHITE);
        g2d.drawString(texto, largura/2 - 20, altura/2 + 5);
        
        g2d.dispose();
        
        return new ImageIcon(imagem);
    }
}
