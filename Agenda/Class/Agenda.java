package Agenda.Class;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
    private String nomeEvento;
    private LocalDate dataEvento;
    private LocalTime horarioEvento;
    
    public Agenda(String nomeEvento, LocalDate dataEvento, LocalTime horarioEvento) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horarioEvento = horarioEvento;
    }
    
    // Getters e Setters
    public String getNomeEvento() {
        return nomeEvento;
    }
    
    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
    
    public LocalDate getDataEvento() {
        return dataEvento;
    }
    
    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }
    
    public LocalTime getHorarioEvento() {
        return horarioEvento;
    }
    
    public void setHorarioEvento(LocalTime horarioEvento) {
        this.horarioEvento = horarioEvento;
    }
    
    @Override
    public String toString() {
        return nomeEvento + "|" + dataEvento + "|" + horarioEvento;
    }
    
    public static Agenda fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length == 3) {
            return new Agenda(
                partes[0],
                LocalDate.parse(partes[1]),
                LocalTime.parse(partes[2])
            );
        }
        return null;
    }
}