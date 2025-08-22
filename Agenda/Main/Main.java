package Agenda.Main;

import Agenda.Controller.AgendaController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AgendaController controller = new AgendaController();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== AGENDA DE EVENTOS ===");
        
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar evento");
            System.out.println("2. Remover evento");
            System.out.println("3. Editar evento");
            System.out.println("4. Listar eventos");
            System.out.println("5. Sair");
            System.out.print("Opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            switch (opcao) {
                case 1:
                    adicionarEvento(controller, scanner);
                    break;
                case 2:
                    removerEvento(controller, scanner);
                    break;
                case 3:
                    editarEvento(controller, scanner);
                    break;
                case 4:
                    controller.listarEventos();
                    break;
                case 5:
                    System.out.println("Saindo da agenda...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void adicionarEvento(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        
        System.out.print("Data do evento (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        
        System.out.print("Horário do evento (HH:MM): ");
        String horario = scanner.nextLine();
        
        if (controller.adicionarEvento(nome, data, horario)) {
            System.out.println("Evento adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar evento. Verifique o formato da data e horário.");
        }
    }
    
    private static void removerEvento(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do evento a ser removido: ");
        String nome = scanner.nextLine();
        
        if (controller.removerEvento(nome)) {
            System.out.println("Evento removido com sucesso!");
        } else {
            System.out.println("Evento não encontrado.");
        }
    }
    
    private static void editarEvento(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do evento a ser editado: ");
        String nomeAntigo = scanner.nextLine();
        
        System.out.print("Novo nome do evento: ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Nova data do evento (YYYY-MM-DD): ");
        String novaData = scanner.nextLine();
        
        System.out.print("Novo horário do evento (HH:MM): ");
        String novoHorario = scanner.nextLine();
        
        if (controller.editarEvento(nomeAntigo, novoNome, novaData, novoHorario)) {
            System.out.println("Evento editado com sucesso!");
        } else {
            System.out.println("Falha ao editar evento. Verifique se o evento existe e o formato dos dados.");
        }
    }
}