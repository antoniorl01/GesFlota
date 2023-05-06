public class Puerto {
    private int idPuerto;
    private String nombrePuerto;
    private char opcion;
    private TipoPuerto tipoPuerto;

    /* Constructor */
    public Puerto(int idPuerto, String nombrePuerto, TipoPuerto tipoPuerto) {
        this.idPuerto = idPuerto;
        this.nombrePuerto = nombrePuerto;
        this.tipoPuerto = tipoPuerto;
    }

    /* Getters and Setters */
    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public TipoPuerto getTipoPuerto() {
        return tipoPuerto;
    }

    public void setTipoPuerto(TipoPuerto tipoPuerto) {
        this.tipoPuerto = tipoPuerto;
    }

    /* Methods */
    public void imprimirInfoPuerto() {
        String tipo = "";
        switch (tipoPuerto) {
            case YACIMIENTO -> tipo = "Yacimiento";
            case REFINERIA -> tipo = "Refineria";
            case DEPOSITO -> tipo = "Deposito";
        }
        System.out.println("Id: " + idPuerto);
        System.out.println("Nombre: " + nombrePuerto);
        System.out.println("Tipo de puerto: " + tipo);
    }

    public void confirmacionCorrecto(char opcion) {
    }
}
