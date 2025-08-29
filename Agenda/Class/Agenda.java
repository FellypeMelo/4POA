package Agenda.Class;

public class Agenda {
    private String nome;
    private String telefone;
    private String email;
    
    public Agenda(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return nome + "|" + telefone + "|" + email;
    }
    
    public static Agenda fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length == 3) {
            return new Agenda(
                partes[0],
                partes[1],
                partes[2]
            );
        }
        return null;
    }
}