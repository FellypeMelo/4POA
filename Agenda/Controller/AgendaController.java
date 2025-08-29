package Agenda.Controller;

import Agenda.Class.Agenda;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaController {
    private static final String ARQUIVO_AGENDA = "agenda.txt";
    private List<Agenda> contatos;
    
    public AgendaController() {
        this.contatos = new ArrayList<>();
        carregarContatos();
    }
    
    // Adicionar contato
    public boolean adicionarContato(String nome, String telefone, String email) {
        try {
            Agenda contato = new Agenda(nome, telefone, email);
            contatos.add(contato);
            salvarContatos();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
            return false;
        }
    }
    
    // Remover contato por nome
    public boolean removerContato(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {
                contatos.remove(i);
                salvarContatos();
                return true;
            }
        }
        return false;
    }
    
    // Editar contato
    public boolean editarContato(String nomeAntigo, String novoNome, String novoTelefone, String novoEmail) {
        for (Agenda contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nomeAntigo)) {
                try {
                    contato.setNome(novoNome);
                    contato.setTelefone(novoTelefone);
                    contato.setEmail(novoEmail);
                    salvarContatos();
                    return true;
                } catch (Exception e) {
                    System.out.println("Erro ao editar contato: " + e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }
    
    // Buscar contato por nome
    public Agenda buscarContato(String nome) {
        for (Agenda contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
    }
    
    // Listar todos os contatos
    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado na agenda.");
            return;
        }
        
        System.out.println("\n=== CONTATOS NA AGENDA ===");
        for (int i = 0; i < contatos.size(); i++) {
            Agenda contato = contatos.get(i);
            System.out.printf("%d. %s - %s - %s%n", 
                i + 1,
                contato.getNome(),
                contato.getTelefone(),
                contato.getEmail()
            );
        }
        System.out.println("==========================");
    }
    
    // Carregar contatos do arquivo
    private void carregarContatos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_AGENDA))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Agenda contato = Agenda.fromString(linha);
                    if (contato != null) {
                        contatos.add(contato);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda, será criado quando necessário
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }
    
    // Salvar contatos no arquivo
    private void salvarContatos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_AGENDA))) {
            for (Agenda contato : contatos) {
                bw.write(contato.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }
}