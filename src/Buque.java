import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Buque {
    private char idBuque;
    private String nombreBuque;
    private ArrayList<OperacionesBuque> operacionesBuque = new ArrayList<>();
    private char opcion;
    private boolean vacio = true;
    private TipoCarga tipoCarga;
    private EstadoBuque estadoBuque;
    private Date ultimaFecha;
    private Puerto ubicacionActual;
    private LocalDate fechaInicioBuque;

    public Buque(char idBuque, String nombreBuque, Puerto ubicacionActual, LocalDate fechaInicioBuque) {
        this.idBuque = idBuque;
        this.nombreBuque = nombreBuque;
        this.ubicacionActual = ubicacionActual;
        this.fechaInicioBuque = fechaInicioBuque;
    }

    /* Getters and Setter */
    public char getIdBuque() {
        return idBuque;
    }

    public void setIdBuque(char idBuque) {
        this.idBuque = idBuque;
    }

    public String getNombreBuque() {
        return nombreBuque;
    }

    public void setNombreBuque(String nombreBuque) {
        this.nombreBuque = nombreBuque;
    }

    public boolean isVacio() {
        return vacio;
    }

    public EstadoBuque getEstadoBuque() {
        return estadoBuque;
    }

    public void setEstadoBuque(EstadoBuque estadoBuque) {
        this.estadoBuque = estadoBuque;
    }

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public ArrayList<OperacionesBuque> getOperacionesBuque() {
        return operacionesBuque;
    }

    public void setOperacionesBuque(ArrayList<OperacionesBuque> operacionesBuque) {
        this.operacionesBuque = operacionesBuque;
    }

    public char getOpcion() {
        return opcion;
    }

    public void setOpcion(char opcion) {
        this.opcion = opcion;
    }

    public void setVacio(boolean vacio) {
        this.vacio = vacio;
    }

    public Date getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(Date ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

    public Puerto getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(Puerto ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public LocalDate getFechaInicioBuque() {
        return fechaInicioBuque;
    }

    public void setFechaInicioBuque(LocalDate fechaInicioBuque) {
        this.fechaInicioBuque = fechaInicioBuque;
    }

    /* Methods */

    /**
     * Yacimiento: crudo
     * Refineria: fuel, fosil, gasolina
     * Depósito: fuel, fosil, gasolina
     */
    public void cargar(TipoCarga tipoCarga) {
        if (isVacio()) {
            this.tipoCarga = tipoCarga;
            vacio = false;
        }
    }
    public void trasladar(Puerto puerto) {
        if (!this.ubicacionActual.equals(puerto)) {
            this.ubicacionActual = puerto;
        }
    }
    /**
     * Yacimiento: nada
     * Refineria: crudo
     * Depósito: fuel, fosil, gasolina
     */
    public void descargar(Puerto puerto, TipoCarga tipoCarga) {
        switch (puerto.getTipoPuerto()) {
            case YACIMIENTO -> System.out.println("No se puede descargar");
            case DEPOSITO -> {
                if ((tipoCarga == TipoCarga.CRUDO)) {
                    vacio = true;
                } else {
                    System.out.println("No se puede descargar se tipo de materia");
                }
            }
            case REFINERIA -> {
                if (tipoCarga == TipoCarga.CRUDO) {
                    System.out.println("No se puede descargar se tipo de materia");
                } else {
                    vacio = true;
                }
            }
        }
    }
    public void imprimirOperacionesBuque() {}
    public void imprimirBuque() {
        if (!vacio) {
            System.out.println("Buque{" +
                "Id=" + idBuque +
                ", Nombre='" + nombreBuque + '\'' +
                ", Última Fecha=" + ultimaFecha +
                ", Puerto=" + ubicacionActual.getNombrePuerto() +
                ", Carga=" + tipoCarga +
                '}');
            return;
        }

        System.out.println("Buque{" +
            "Id=" + idBuque +
            ", Nombre='" + nombreBuque + '\'' +
            ", Última Fecha=" + ultimaFecha +
            ", Puerto=" + ubicacionActual.getNombrePuerto() +
            ", Carga=vacio" +
            '}');

    }
}
