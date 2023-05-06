import java.time.LocalDate;

public class Calendario {
    private LocalDate dia;
    private LocalDate mes;
    private LocalDate anio;

    /* Constructor */
    public Calendario(LocalDate dia, LocalDate mes, LocalDate anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /* Getters and setters */
    public LocalDate getDia() {
        return dia;
    }

    public LocalDate getMes() {
        return mes;
    }

    public LocalDate getAnio() {
        return anio;
    }

    public void imprimirCalendario() {

    }

    public void resumenCalendario() {

    }
}
