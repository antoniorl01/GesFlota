import java.time.LocalDate;

/**
 * El único cambio que hice fue que añadí un campo añadido tipoOperación
 * Creo que el que hizo el documento quería esto pero se le olvidó añadirlo
 */
public class OperacionesBuque {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int duracionOperacion;
    private TipoCarga tipoCarga;
    private  TipoOperacion tipoOperacion;
    private Puerto puertoInicio;
    private Puerto puertoDestino;


    public OperacionesBuque(LocalDate fechaInicio, LocalDate fechaFin, int duracionOperacion, TipoOperacion tipoOperacion, TipoCarga tipoCarga, Puerto puertoInicio, Puerto puertoDestino) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionOperacion = duracionOperacion;
        this.tipoOperacion = tipoOperacion;
        this.tipoCarga = tipoCarga;
        this.puertoInicio = puertoInicio;
        this.puertoDestino = puertoDestino;
    }

    /* Getters and Setters */
    public int getDuracionOperacion(LocalDate fechaInicio, LocalDate fechaFin) {
        return -1;
    }

    public LocalDate calcularFechaFin(LocalDate fechaInicio, int duracionOperacion) {
        return LocalDate.now();
    }

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public Puerto getPuertoInicio() {
        return puertoInicio;
    }

    public void setPuertoInicio(Puerto puertoInicio) {
        this.puertoInicio = puertoInicio;
    }

    public Puerto getPuertoDestino() {
        return puertoDestino;
    }

    public void setPuertoDestino(Puerto puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDuracionOperacion() {
        return duracionOperacion;
    }

    public void setDuracionOperacion(int duracionOperacion) {
        this.duracionOperacion = duracionOperacion;
    }

    /* Methods */
    public void imprimirOperacionesBuque(LocalDate fechaInicio, Puerto puertoInicio,
                                         TipoCarga tipoCarga, int duracionOperacion, Puerto puertoDestion) {
        System.out.println("Operacion Buque");
        System.out.println("Fecha inicio: " + fechaInicio);
        System.out.println("Puerto salida: " + puertoInicio.getNombrePuerto());
        System.out.println("Carga: " + tipoCarga.name());
        System.out.println("Fecha fin: " + fechaFin);
        System.out.println("Puerto destino: " + puertoDestion.getNombrePuerto());
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
}
