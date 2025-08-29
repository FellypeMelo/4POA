package Agenda.Main;

import Agenda.Controller.AgendaController;
import Agenda.Class.Agenda;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AgendaController controller = new AgendaController();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== AGENDA DE CONTATOS ===");
        
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Remover contato");
            System.out.println("3. Editar contato");
            System.out.println("4. Buscar contato");
            System.out.println("5. Listar contatos");
            System.out.println("6. Sair");
            System.out.print("Opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            switch (opcao) {
                case 1:
                    adicionarContato(controller, scanner);
                    break;
                case 2:
                    removerContato(controller, scanner);
                    break;
                case 3:
                    editarContato(controller, scanner);
                    break;
                case 4:
                    buscarContato(controller, scanner);
                    break;
                case 5:
                    controller.listarContatos();
                    break;
                case 6:
                    System.out.println("Saindo da agenda...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void adicionarContato(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do contato: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        if (controller.adicionarContato(nome, telefone, email)) {
            System.out.println("Contato adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar contato.");
        }
    }
    
    private static void removerContato(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do contato a ser removido: ");
        String nome = scanner.nextLine();
        
        if (controller.removerContato(nome)) {
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
    
    private static void editarContato(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do contato a ser editado: ");
        String nomeAntigo = scanner.nextLine();
        
        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Novo telefone: ");
        String novoTelefone = scanner.nextLine();
        
        System.out.print("Novo email: ");
        String novoEmail = scanner.nextLine();
        
        if (controller.editarContato(nomeAntigo, novoNome, novoTelefone, novoEmail)) {
            System.out.println("Contato editado com sucesso!");
        } else {
            System.out.println("Falha ao editar contato. Verifique se o contato existe.");
        }
    }
    
    private static void buscarContato(AgendaController controller, Scanner scanner) {
        System.out.print("Nome do contato a ser buscado: ");
        String nome = scanner.nextLine();
        
        Agenda contato = controller.buscarContato(nome);
        if (contato != null) {
            System.out.println("\n=== CONTATO ENCONTRADO ===");
            System.out.printf("Nome: %s%n", contato.getNome());
            System.out.printf("Telefone: %s%n", contato.getTelefone());
            System.out.printf("Email: %s%n", contato.getEmail());
            System.out.println("==========================");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
}