package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Class.Gato;

public class CorridaGatos extends JFrame implements ActionListener {
    
    private JButton btnIniciar, btnParar, btnReiniciar;
    private JPanel painelPista, painelControles;
    private JLabel lblTitulo;
    
    private Gato gato1, gato2, gato3;
    private boolean corridaAtiva = false;
    
    public CorridaGatos() {
        configurarJanela();
        criarComponentes();
        configurarLayout();
        adicionarEventos();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("Corrida de Gatos - Threads");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 139, 34));
    }
    
    private void criarComponentes() {
        // Título
        lblTitulo = new JLabel("CORRIDA DE GATOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Pista de corrida
        painelPista = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharPista(g);
            }
        };
        painelPista.setLayout(null);
        painelPista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        painelPista.setBackground(new Color(139, 69, 19));
        
        // Botões
        btnIniciar = new JButton("Iniciar");
        btnParar = new JButton("Parar");
        btnReiniciar = new JButton("Reiniciar");
        
        configurarBotao(btnIniciar, new Color(50, 205, 50));
        configurarBotao(btnParar, new Color(220, 20, 60));
        configurarBotao(btnReiniciar, new Color(255, 165, 0));
        
        btnParar.setEnabled(false);
        
        // Painel de controles
        painelControles = new JPanel();
        painelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelControles.setBackground(new Color(34, 139, 34));
    }
    
    private void configurarBotao(JButton btn, Color cor) {
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(120, 35));
    }
    
    private void desenharPista(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Faixas da pista
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        for (int i = 1; i <= 3; i++) {
            int y = i * 120;
            g2d.drawLine(0, y, getWidth(), y);
        }
        
        // Linha de chegada
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(getWidth() - 50, 0, getWidth() - 50, getHeight());
        
        // Texto CHEGADA
        g2d.setColor(Color.BLACK);
        g2d.fillRect(getWidth() - 120, 10, 110, 25);
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("CHEGADA", getWidth() - 110, 28);
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        add(lblTitulo, BorderLayout.NORTH);
        add(painelPista, BorderLayout.CENTER);
        
        painelControles.add(btnIniciar);
        painelControles.add(btnParar);
        painelControles.add(btnReiniciar);
        add(painelControles, BorderLayout.SOUTH);
    }
    
    private void adicionarEventos() {
        btnIniciar.addActionListener(this);
        btnParar.addActionListener(this);
        btnReiniciar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            iniciarCorrida();
        } else if (e.getSource() == btnParar) {
            pararCorrida();
        } else if (e.getSource() == btnReiniciar) {
            reiniciarCorrida();
        }
    }
    
    private void iniciarCorrida() {
        if (!corridaAtiva) {
            corridaAtiva = true;
            btnIniciar.setEnabled(false);
            btnParar.setEnabled(true);
            
            // Criar gatos com imagens reais
            gato1 = new Gato("Gato Laranja", "Threads/src/imagens/gato1.jpg", 50, 80);
            gato2 = new Gato("Gato Cinza", "Threads/src/imagens/gato2.jpg", 50, 200);
            gato3 = new Gato("Gato Preto", "Threads/src/imagens/gato3.jpg", 50, 320);
            
            // Adicionar à pista
            painelPista.add(gato1);
            painelPista.add(gato2);
            painelPista.add(gato3);
            
            painelPista.revalidate();
            painelPista.repaint();
        }
    }
    
    private void pararCorrida() {
        if (corridaAtiva) {
            corridaAtiva = false;
            
            if (gato1 != null) gato1.parar();
            if (gato2 != null) gato2.parar();
            if (gato3 != null) gato3.parar();
            
            btnIniciar.setEnabled(true);
            btnParar.setEnabled(false);
        }
    }
    
    private void reiniciarCorrida() {
        pararCorrida();
        
        // Remover gatos
        if (gato1 != null) painelPista.remove(gato1);
        if (gato2 != null) painelPista.remove(gato2);
        if (gato3 != null) painelPista.remove(gato3);
        
        gato1 = gato2 = gato3 = null;
        
        painelPista.revalidate();
        painelPista.repaint();
        
        btnIniciar.setEnabled(true);
        btnParar.setEnabled(false);
    }
}
