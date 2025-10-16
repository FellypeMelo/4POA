import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Deque<CompraMemento> historico = new ArrayDeque<>();
    private static final Map<Integer, PagamentoCommand> comandos = new HashMap<>();
    
    static {
        inicializarComandos();
    }
    
    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    executarFluxoPagamento();
                    break;
                case 2:
                    executarFluxoHistorico();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE PAGAMENTOS ===");
        System.out.println("1. Realizar Pagamento");
        System.out.println("2. Gerenciar Histórico");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void inicializarComandos() {
        comandos.put(1, new PagamentoCartaoCredito());
        comandos.put(2, new PagamentoCartaoDebito());
        comandos.put(3, new PagamentoBoleto());
    }
    
    private static void executarFluxoPagamento() {
        System.out.print("Nome da loja: ");
        String nomeLoja = scanner.nextLine();
        
        System.out.print("Valor da compra: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("\nFormas de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. Boleto");
        System.out.print("Escolha a forma de pagamento: ");
        int formaPagamento = scanner.nextInt();
        scanner.nextLine();
        
        processarPagamento(nomeLoja, valor, formaPagamento);
    }
    
    private static void processarPagamento(String nomeLoja, double valor, int formaPagamento) {
        PagamentoCommand comando = comandos.get(formaPagamento);
        if (comando == null) {
            System.out.println("Forma de pagamento inválida!");
            return;
        }
        
        Compra compra = new Compra(nomeLoja);
        compra.definirValor(valor);
        salvarEstado(compra);
        comando.processarCompra(compra);
    }
    
    private static void executarFluxoHistorico() {
        while (true) {
            exibirMenuHistorico();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    listarHistorico();
                    break;
                case 2:
                    desfazerUltimaCompra();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void exibirMenuHistorico() {
        System.out.println("\n=== GERENCIAR HISTÓRICO ===");
        System.out.println("1. Listar Histórico de Compras");
        System.out.println("2. Desfazer Última Compra");
        System.out.println("3. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void salvarEstado(Compra compra) {
        historico.push(compra.criarMemento());
    }
    
    private static void desfazerUltimaCompra() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma compra para desfazer.");
            return;
        }
        
        CompraMemento memento = historico.pop();
        System.out.println("Compra desfeita com sucesso:");
        System.out.println(memento.obterInfoNota());
    }
    
    private static void listarHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Histórico de compras vazio.");
            return;
        }
        
        System.out.println("\n=== HISTÓRICO DE COMPRAS ===");
        int contador = 1;
        for (CompraMemento memento : historico) {
            System.out.println(contador + ". " + memento.obterInfoNota());
            contador++;
        }
    }
}

interface PagamentoCommand {
    void processarCompra(Compra compra);
}

final class PagamentoCartaoCredito implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("\n✅ Compra processada com cartão de crédito!");
        System.out.println(compra.obterInfoNota());
    }
}

final class PagamentoCartaoDebito implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("\n✅ Compra processada com cartão de débito!");
        System.out.println(compra.obterInfoNota());
    }
}

final class PagamentoBoleto implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("\n📄 Boleto criado com sucesso!");
        System.out.println(compra.obterInfoNota());
    }
}

final class Compra {
    private static int contadorId = 0;
    private final int idNotaFiscal;
    private String nomeDaLoja;
    private double valorTotal;

    public Compra(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja;
        this.idNotaFiscal = ++contadorId;
    }

    public void definirValor(double valor) {
        this.valorTotal = valor;
    }

    public String obterInfoNota() {
        return String.format("Nota fiscal nº: %d\nLoja: %s\nValor: R$ %.2f", 
                           idNotaFiscal, nomeDaLoja, valorTotal);
    }

    public CompraMemento criarMemento() {
        return new CompraMemento(nomeDaLoja, valorTotal, idNotaFiscal);
    }
}

final class CompraMemento {
    private final String nomeDaLoja;
    private final double valorTotal;
    private final int idNotaFiscal;

    public CompraMemento(String nomeDaLoja, double valorTotal, int idNotaFiscal) {
        this.nomeDaLoja = nomeDaLoja;
        this.valorTotal = valorTotal;
        this.idNotaFiscal = idNotaFiscal;
    }

    public String obterInfoNota() {
        return String.format("Nota fiscal nº: %d | Loja: %s | Valor: R$ %.2f", 
                           idNotaFiscal, nomeDaLoja, valorTotal);
    }
}