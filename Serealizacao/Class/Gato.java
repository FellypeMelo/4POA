package Serealizacao.Class;
import java.io.Serializable;

public class Gato implements Serializable{
    private String nome;
    private int idade;
    private String cor;
    private String raca;
    private String sexo;
    private String dono;
    private String telefone;
    private String email;

    public Gato(String nome, int idade, String cor, String raca, String sexo, String dono, String telefone, String email) {
        this.nome = nome;
        this.idade = idade;
        this.cor = cor;
        this.raca = raca;
        this.sexo = sexo;
        this.dono = dono;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
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

    public String toString() {
        return "gato [nome=" + nome + ", idade=" + idade + ", cor=" + cor + ", raca=" + raca + ", sexo=" + sexo
                + ", dono=" + dono + ", telefone=" + telefone + ", email=" + email + "]";
    }

    public void miar(){
        System.out.println("Miau");
    }
}
