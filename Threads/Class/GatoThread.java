package Threads.Class;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GatoThread extends JLabel implements Runnable {
    private Thread gatoThread = null;
    private int posX;
    private int posY;
    public static int posicao = 0;
    private String nome;
    private boolean correndo = true;
    
    // Construtor padrão
    public GatoThread() {
        super();
    }
    
    // Construtor sobrecarregado
    public GatoThread(String nome, ImageIcon img, int posX, int posY) {
        super(img);
        this.posX = posX;
        this.posY = posY;
        this.nome = nome;
        
        // Debug: verificar se a imagem foi carregada
        if (img != null && img.getImage() != null) {
            System.out.println("GatoThread criado: " + nome + " - Imagem carregada com sucesso");
        } else {
            System.out.println("GatoThread criado: " + nome + " - IMAGEM NULA!");
        }
        
        // Configurar propriedades visuais
        this.setOpaque(false); // Permitir transparência
        this.setVisible(true);
        
        gatoThread = new Thread(this, nome);
        gatoThread.start();
    }
    
    // Método run() da interface Runnable
    @Override
    public void run() {
        while (correndo && posX < 1200) {
            // Movimento aleatório com pequenas animações
            int movimento = new Random().nextInt(5) + 2; // Movimento entre 2-6 pixels
            posX += movimento;
            
            // Pequena variação na posição Y para simular movimento natural
            int variacaoY = new Random().nextInt(3) - 1; // -1, 0, ou 1
            posY += variacaoY;
            
            // Garantir que o gato não saia da pista
            if (posY < 30) posY = 30;
            if (posY > 400) posY = 400;
            
            this.setLocation(posX, posY);
            
            // Verificar se chegou ao final
            if (posX >= 1200) {
                posicao++;
                JOptionPane.showMessageDialog(null, 
                    "🏆 " + posicao + "º Lugar: " + nome + " 🐱", 
                    "Resultado da Corrida", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            try {
                // Velocidade variável para cada gato
                Thread.sleep(new Random().nextInt(100) + 50); // Entre 50-150ms
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    
    // Método para parar a corrida
    public void pararCorrida() {
        correndo = false;
        if (gatoThread != null) {
            gatoThread.interrupt();
        }
    }
    
    // Getters e setters
    public String getNome() {
        return nome;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public boolean isCorrendo() {
        return correndo;
    }
}
