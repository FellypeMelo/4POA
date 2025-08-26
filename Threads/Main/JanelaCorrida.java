package Threads.Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import Threads.Class.GatoThread;
import Threads.src.GeradorImagens;
import Threads.src.GeradorPista;

public class JanelaCorrida extends JFrame implements ActionListener {
    
    private JButton btnIniciarCorrida;
    private JButton btnPararCorrida;
    private JButton btnReiniciar;
    private JLabel lblPista;
    private JLabel lblTitulo;
    private JPanel painelControles;
    private JPanel painelPista;
    private JLabel lblPistaFundo; // Referência para a pista de fundo
    
    // Imagens dos gatos
    private ImageIcon imgGato1;
    private ImageIcon imgGato2;
    private ImageIcon imgGato3;
    
    // Gatos da corrida
    private GatoThread gato1;
    private GatoThread gato2;
    private GatoThread gato3;
    
    // Estado da corrida
    private boolean corridaAtiva = false;
    
    public JanelaCorrida() {
        super();
        configurarJanela();
        criarComponentes();
        configurarLayout();
        adicionarEventos();
        setVisible(true);
    }
    
    private void configurarJanela() {
        setTitle("🏁 Corrida de Gatos - Teste de Threads 🐱");
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 139, 34)); // Verde floresta
    }
    
    private void criarComponentes() {
        // Título
        lblTitulo = new JLabel("🏁 CORRIDA DE GATOS 🏁");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Pista de corrida com imagem gerada
        painelPista = new JPanel();
        painelPista.setLayout(null);
        painelPista.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        
        // Adicionar imagem da pista como fundo (guardar referência)
        lblPistaFundo = new JLabel(GeradorPista.gerarPistaCorrida());
        lblPistaFundo.setBounds(0, 0, 1200, 500);
        lblPistaFundo.setOpaque(false); // Permitir transparência
        painelPista.add(lblPistaFundo);
        
        // Botões
        btnIniciarCorrida = new JButton("🚀 Iniciar Corrida");
        btnIniciarCorrida.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciarCorrida.setBackground(new Color(50, 205, 50));
        btnIniciarCorrida.setForeground(Color.WHITE);
        
        btnPararCorrida = new JButton("⏹️ Parar Corrida");
        btnPararCorrida.setFont(new Font("Arial", Font.BOLD, 14));
        btnPararCorrida.setBackground(new Color(220, 20, 60));
        btnPararCorrida.setForeground(Color.WHITE);
        btnPararCorrida.setEnabled(false);
        
        btnReiniciar = new JButton("🔄 Reiniciar");
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReiniciar.setBackground(new Color(255, 165, 0));
        btnReiniciar.setForeground(Color.WHITE);
        
        // Painel de controles
        painelControles = new JPanel();
        painelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelControles.setBackground(new Color(34, 139, 34));
        
        // Criar imagens dos gatos
        criarImagensGatos();
    }
    
    private void criarImagensGatos() {
        try {
            // Usar o gerador de imagens para criar os gatos
            imgGato1 = GeradorImagens.gerarGatoLaranja();
            imgGato2 = GeradorImagens.gerarGatoCinza();
            imgGato3 = GeradorImagens.gerarGatoPreto();
            
            // Verificar se as imagens foram carregadas corretamente
            if (imgGato1.getImage() == null || imgGato2.getImage() == null || imgGato3.getImage() == null) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Erro ao carregar algumas imagens dos gatos!\nVerifique se os arquivos existem na pasta src/imagens/", 
                    "Erro de Imagem", 
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Erro ao carregar imagens dos gatos: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        // Adicionar título
        add(lblTitulo, BorderLayout.NORTH);
        
        // Adicionar pista
        add(painelPista, BorderLayout.CENTER);
        
        // Adicionar controles
        painelControles.add(btnIniciarCorrida);
        painelControles.add(btnPararCorrida);
        painelControles.add(btnReiniciar);
        add(painelControles, BorderLayout.SOUTH);
        
        // Configurar posições
        lblTitulo.setBounds(0, 0, 1300, 50);
        painelPista.setBounds(50, 70, 1200, 500);
    }
    
    private void adicionarEventos() {
        btnIniciarCorrida.addActionListener(this);
        btnPararCorrida.addActionListener(this);
        btnReiniciar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarCorrida) {
            iniciarCorrida();
        } else if (e.getSource() == btnPararCorrida) {
            pararCorrida();
        } else if (e.getSource() == btnReiniciar) {
            reiniciarCorrida();
        }
    }
    
    private void iniciarCorrida() {
        if (!corridaAtiva) {
            corridaAtiva = true;
            btnIniciarCorrida.setEnabled(false);
            btnPararCorrida.setEnabled(true);
            
            // Criar e adicionar os gatos à pista com posições ajustadas
            gato1 = new GatoThread("Gato Laranja", imgGato1, 50, 120);
            gato2 = new GatoThread("Gato Cinza", imgGato2, 50, 220);
            gato3 = new GatoThread("Gato Preto", imgGato3, 50, 320);
            
            // Configurar posições e tamanhos (ajustados para as faixas)
            gato1.setBounds(50, 120, 80, 60);
            gato2.setBounds(50, 220, 80, 60);
            gato3.setBounds(50, 300, 80, 60);
            
            // Adicionar à pista (após a pista de fundo) e garantir que apareçam acima
            painelPista.add(gato1, 0); // Adicionar no topo (índice 0)
            painelPista.add(gato2, 0); // Adicionar no topo (índice 0)
            painelPista.add(gato3, 0); // Adicionar no topo (índice 0)
            
            // Forçar os gatos a ficarem visíveis
            gato1.setVisible(true);
            gato2.setVisible(true);
            gato3.setVisible(true);
            
            // Atualizar interface
            painelPista.revalidate();
            painelPista.repaint();
            
            // Debug: verificar se os gatos foram adicionados
            System.out.println("Gatos adicionados ao painel:");
            System.out.println("Gato1: " + gato1.isVisible() + " - Pos: " + gato1.getBounds());
            System.out.println("Gato2: " + gato2.isVisible() + " - Pos: " + gato2.getBounds());
            System.out.println("Gato3: " + gato3.isVisible() + " - Pos: " + gato3.getBounds());
            System.out.println("Componentes no painel: " + painelPista.getComponentCount());
            
            JOptionPane.showMessageDialog(this, 
                "🏁 Corrida iniciada! Os gatos estão correndo! 🐱", 
                "Corrida Iniciada", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void pararCorrida() {
        if (corridaAtiva) {
            corridaAtiva = false;
            
            // Parar todos os gatos
            if (gato1 != null) gato1.pararCorrida();
            if (gato2 != null) gato2.pararCorrida();
            if (gato3 != null) gato3.pararCorrida();
            
            btnIniciarCorrida.setEnabled(true);
            btnPararCorrida.setEnabled(false);
            
            JOptionPane.showMessageDialog(this, 
                "⏹️ Corrida parada! 🐱", 
                "Corrida Parada", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void reiniciarCorrida() {
        // Parar corrida atual se estiver ativa
        if (corridaAtiva) {
            pararCorrida();
        }
        
        // Remover apenas os gatos, manter a pista de fundo
        if (gato1 != null) {
            painelPista.remove(gato1);
            gato1 = null;
        }
        if (gato2 != null) {
            painelPista.remove(gato2);
            gato2 = null;
        }
        if (gato3 != null) {
            painelPista.remove(gato3);
            gato3 = null;
        }
        
        // Garantir que a pista de fundo esteja visível e no fundo
        if (lblPistaFundo != null) {
            painelPista.setComponentZOrder(lblPistaFundo, painelPista.getComponentCount() - 1);
        }
        
        // Atualizar interface
        painelPista.revalidate();
        painelPista.repaint();
        
        // Debug: verificar estado do painel
        System.out.println("Pista reiniciada - Componentes: " + painelPista.getComponentCount());
        
        // Resetar estado
        corridaAtiva = false;
        btnIniciarCorrida.setEnabled(true);
        btnPararCorrida.setEnabled(false);
        
        // Resetar posições
        GatoThread.posicao = 0;
        
        JOptionPane.showMessageDialog(this, 
            "🔄 Pista limpa! Pronto para nova corrida! 🐱", 
            "Reiniciado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
