package Agenda.Controller;

import Agenda.Class.Agenda;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendaController {
    private static final String ARQUIVO_AGENDA = "agenda.txt";
    private List<Agenda> eventos;
    
    public AgendaController() {
        this.eventos = new ArrayList<>();
        carregarEventos();
    }
    
    // Adicionar evento
    public boolean adicionarEvento(String nome, String data, String horario) {
        try {
            LocalDate dataEvento = LocalDate.parse(data);
            LocalTime horarioEvento = LocalTime.parse(horario);
            
            Agenda evento = new Agenda(nome, dataEvento, horarioEvento);
            eventos.add(evento);
            salvarEventos();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar evento: " + e.getMessage());
            return false;
        }
    }
    
    // Remover evento por nome
    public boolean removerEvento(String nome) {
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getNomeEvento().equalsIgnoreCase(nome)) {
                eventos.remove(i);
                salvarEventos();
                return true;
            }
        }
        return false;
    }
    
    // Editar evento
    public boolean editarEvento(String nomeAntigo, String novoNome, String novaData, String novoHorario) {
        for (Agenda evento : eventos) {
            if (evento.getNomeEvento().equalsIgnoreCase(nomeAntigo)) {
                try {
                    evento.setNomeEvento(novoNome);
                    evento.setDataEvento(LocalDate.parse(novaData));
                    evento.setHorarioEvento(LocalTime.parse(novoHorario));
                    salvarEventos();
                    return true;
                } catch (Exception e) {
                    System.out.println("Erro ao editar evento: " + e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }
    
    // Listar todos os eventos
    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado na agenda.");
            return;
        }
        
        System.out.println("\n=== EVENTOS NA AGENDA ===");
        for (int i = 0; i < eventos.size(); i++) {
            Agenda evento = eventos.get(i);
            System.out.printf("%d. %s - %s às %s%n", 
                i + 1,
                evento.getNomeEvento(),
                evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                evento.getHorarioEvento().format(DateTimeFormatter.ofPattern("HH:mm"))
            );
        }
        System.out.println("========================");
    }
    
    // Carregar eventos do arquivo
    private void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_AGENDA))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Agenda evento = Agenda.fromString(linha);
                    if (evento != null) {
                        eventos.add(evento);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda, será criado quando necessário
        } catch (IOException e) {
            System.out.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }
    
    // Salvar eventos no arquivo
    private void salvarEventos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_AGENDA))) {
            for (Agenda evento : eventos) {
                bw.write(evento.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }
}