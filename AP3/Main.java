// Interface Command
interface PagamentoCommand {
    void processarCompra(Compra compra);
}

// Concrete Command 1
class PagamentoCartaoCredito implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("Compra processada com cartão de crédito!\n" + compra.getInfoNota());
    }
}

// Concrete Command 2
class PagamentoCartaoDebito implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("Compra processada com cartão de débito!\n" + compra.getInfoNota());
    }
}

// Concrete Command 3
class PagamentoBoleto implements PagamentoCommand {
    @Override
    public void processarCompra(Compra compra) {
        System.out.println("Boleto criado!\n" + compra.getInfoNota());
    }
}

// Classe Compra (Receiver)
class Compra {
    private static int CONTADOR_ID;
    protected int idNotaFiscal;
    protected String nomeDaLoja;
    protected double valorTotal;

    public Compra(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja;
        idNotaFiscal = ++CONTADOR_ID;
    }

    public void setValor(double valor) {
        this.valorTotal = valor;
    }

    public String getInfoNota() {
        return "Nota fiscal nº: " + idNotaFiscal + "\nLoja: " + nomeDaLoja + "\nValor: " + valorTotal;
    }
}

// Classe Loja (Invoker)
class Loja {
    protected String nomeDaLoja;

    public Loja(String nome) {
        nomeDaLoja = nome;
    }

    public void executarCompra(double valor, PagamentoCommand formaDePagamento) {
        Compra compra = new Compra(nomeDaLoja);
        compra.setValor(valor);
        formaDePagamento.processarCompra(compra);
    }
}

// Cliente
public class Main {
    public static void main(String[] args) {
        Loja lojasAmericanas = new Loja("Lojas Americanas");
        lojasAmericanas.executarCompra(999.00, new PagamentoCartaoCredito());
        lojasAmericanas.executarCompra(49.00, new PagamentoBoleto());
        lojasAmericanas.executarCompra(99.00, new PagamentoCartaoDebito());

        Loja exorbitante = new Loja("Exorbitante");
        exorbitante.executarCompra(19.00, new PagamentoCartaoCredito());
    }
}