import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static char opcion = 'x';
    private static boolean continuar = true;
    private static ArrayList<Puerto> listadoPuertos = new ArrayList<Puerto>();
    private static ArrayList<Buque> listadoBuque = new ArrayList<Buque>();



    public static void main(String[] args) {

        listadoPuertos.add(new Puerto(1, "roquetas", TipoPuerto.YACIMIENTO));
        listadoPuertos.add(new Puerto(2, "aguadulce", TipoPuerto.REFINERIA));
        listadoBuque.add(new Buque('A', "mariachi", listadoPuertos.get(0), LocalDate.now()));

        while (continuar) {
            menu(opcion);
        }

        System.out.println("¡Muchas Gracias!");
    }

    static void menu(char opcion) {
        System.out.println("GesFlota: Gestión de Movimientos de una Flota");
        System.out.println("\tEditar Puerto ---------- (Pulsar P)");
        System.out.println("\tEditar Buque  ---------- (Pulsar B)");
        System.out.println("\tEstado Buques ---------- (Pulsar E)");
        System.out.println("\tOperar Buque  ---------- (Pulsar O)");
        System.out.println("\tResumen Mensual Buque -- (Pulsar R)");
        System.out.println("\tSalir         ---------- (Pulsar S)");
        System.out.println("Tecleé una opción válida (P|B|E|O|R|S)");

        opcion = scanner.nextLine().charAt(0);

        switch (Character.toUpperCase(opcion)) {
            case 'P' -> {
                System.out.println("Seleccionó Editar Puerto");
                editarPuerto();
            }
            case 'B' -> {
                System.out.println("Seleccionó Editar Buque");
                editarBuque();
            }
            case 'E' -> {
                System.out.println("Seleccionó Estado Buques");
                estadoBuques();
            }
            case 'O' -> {
                System.out.println("Seleccionó Operar Buque");
                operarBuques();
            }
            case 'R' -> {
                System.out.println("Seleccionó Resumen Mensual Buque");
                resumenMensualBuque();
            }
            case 'S' -> continuar = false;
            default -> System.out.println("Opción no válida, escriba (P|B|E|O|R|S)");
        }
    }

    static void editarPuerto() {

        System.out.println("Identificador (número entre 1 y 10)?");
        int idPuerto = scanner.nextInt();
        scanner.nextLine();

        while (!(idPuerto > 0 && idPuerto < 11)) {
            System.out.println("Número entre 1-10");
            idPuerto = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Nombre (entre 1 y 20 caracteres)?");
        String nombrePuerto = scanner.nextLine();

        while (nombrePuerto.length() > 20 || nombrePuerto.length() == 0) {
            System.out.println("Entre 1-20 caracteres");
            nombrePuerto = scanner.nextLine();
        }

        System.out.println("Tipo (Y-acimiento, R-efineria, D-epósito)?");
        char tipoPuerto = scanner.nextLine().charAt(0);
        tipoPuerto = Character.toUpperCase(tipoPuerto);
        TipoPuerto tipoPuertoReal = TipoPuerto.DEPOSITO;

        while (tipoPuerto != 'Y' && tipoPuerto != 'R' && tipoPuerto != 'D') {
            System.out.println("Tipo (Y-acimiento, R-efineria, D-epósito)?");
            tipoPuerto = scanner.nextLine().charAt(0);
            tipoPuerto = Character.toUpperCase(tipoPuerto);
        }

        switch (tipoPuerto) {
            case 'Y' -> tipoPuertoReal = TipoPuerto.YACIMIENTO;
            case 'R' -> tipoPuertoReal = TipoPuerto.REFINERIA;
            case 'D' -> tipoPuertoReal = TipoPuerto.DEPOSITO;
        }

        System.out.println("Los nuevos cambios borrarán los datos anteriores. ¿Estás seguro? S - N");
        char confirmacion = scanner.nextLine().charAt(0);
        confirmacion = Character.toUpperCase(confirmacion);

        while (confirmacion != 'S' && confirmacion != 'N') {
            System.out.println("Los nuevos cambios borrarán los datos anteriores. ¿Estás seguro? S - N");
            confirmacion = scanner.nextLine().charAt(0);
            confirmacion = Character.toUpperCase(confirmacion);
        }

        if (confirmacion == 'S') {
            for (Puerto pt : listadoPuertos) {
                if (pt.getIdPuerto() == idPuerto) {
                    pt.setNombrePuerto(nombrePuerto);
                    pt.setTipoPuerto(tipoPuertoReal);
                    System.out.println("Cambios hechos");
                }
            }
        } else {
            System.out.println("No se realizarán los cambios");
        }
    }

    static void editarBuque() {

        System.out.println("Identificador (A - B - C - D - E)?");
        char idBuque = scanner.nextLine().charAt(0);
        idBuque = Character.toUpperCase(idBuque);

        while (idBuque != 'A' && idBuque != 'B' && idBuque != 'C' && idBuque != 'D' && idBuque != 'E') {
            System.out.println("Identificador (A - B - C - D - E)?");
            idBuque = scanner.nextLine().charAt(0);
            idBuque = Character.toUpperCase(idBuque);
        }

        System.out.println("Nombre (entre 1 y 20 caracteres)?");
        String nombreBuque = scanner.nextLine();

        while (nombreBuque.length() > 20 || nombreBuque.length() == 0) {
            System.out.println("Entre 1-20 caracteres");
            nombreBuque = scanner.nextLine();
        }

        System.out.println("Fecha inicio: dia (1-31)");
        int dia = scanner.nextInt();
        scanner.nextLine();

        while (!(dia > 0 && dia < 32)) {
            System.out.println("Fecha inicio: dia (1-31)");
            dia = scanner.nextInt();
            scanner.nextLine();
        }

        int mes;

        if (dia == 29 || dia == 30) {
            System.out.println("Fecha inicio: mes ('Febrero no puede ser')");
            mes = scanner.nextInt();
            scanner.nextLine();

            while (mes == 2 || mes < 1 || mes > 12) {
                System.out.println("Fecha inicio: mes (1-12)");
                mes = scanner.nextInt();
                scanner.nextLine();
            }

        } else if (dia == 31) {
            System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
            mes = scanner.nextInt();
            scanner.nextLine();

            while (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
                System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                mes = scanner.nextInt();
                scanner.nextLine();
            }
        } else {
            System.out.println("Fecha inicio: mes (1-12)");
            mes = scanner.nextInt();
            scanner.nextLine();

            while (mes < 1 || mes > 12) {
                System.out.println("Fecha inicio: mes (1-12)");
                mes = scanner.nextInt();
                scanner.nextLine();
            }
        }

        System.out.println("Fecha inicio: año");
        int anio = scanner.nextInt();
        scanner.nextLine();

        LocalDate fechaInicial = LocalDate.of(anio, mes, dia);

        System.out.println("Posibles puertos: ");
        imprimirListadoPuertos(listadoPuertos);
        int idPuerto = scanner.nextInt();
        scanner.nextLine();

        while (!(idPuerto > 0 && idPuerto < 11)) {
            System.out.println("Número entre 1-10");
            idPuerto = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Los nuevos cambios borrarán los datos anteriores. ¿Estás seguro? S - N");
        char confirmacion = scanner.nextLine().toUpperCase().charAt(0);

        while (confirmacion != 'S' && confirmacion != 'N') {
            System.out.println("Los nuevos cambios borrarán los datos anteriores. ¿Estás seguro? S - N");
            confirmacion = scanner.nextLine().toUpperCase().charAt(0);
        }

        if (confirmacion == 'S') {
            for (Buque bq : listadoBuque) {
                if (bq.getIdBuque() == idBuque) {
                    bq.setNombreBuque(nombreBuque);
                    bq.setFechaInicioBuque(fechaInicial);
                    System.out.println("Cambios hechos");
                }
            }
        } else {
            System.out.println("No se realizarán los cambios");
        }
    }

    static void estadoBuques() {
        System.out.println("Estado Buques");
        for (Buque bq : listadoBuque) {
            bq.imprimirBuque();
        }
    }

    static void operarBuques() {
        int opcion = 0;
        int dia = 0;
        int mes = 0;
        int anio = 0;
        LocalDate fechaInicial;
        char idBuque = 'z';
        TipoPuerto tipoPuerto;
        char confirmacionCarga = '0';
        char cargaRespuesta = '0';
        TipoCarga cargaNueva = null;
        int duracionCarga = 0;
        char confirmacionTraslado;
        int duracionTraslado = 0;
        char confirmacionDescarga = 0;
        int duracionDescarga = 0;
        Puerto puertoDestino = null;

        System.out.println("¿Desea realizar una operación completa (1), " +
            "solo cargar (2), " +
            "solo desplazar(3), " +
            "solo descargar(4)?");
        opcion = scanner.nextInt();
        scanner.nextLine();

        while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4) {
            System.out.println("¿Desea realizar una operación completa (1), " +
                "solo cargar (2), " +
                "solo desplazar(3), " +
                "solo descargar(4)?");
            opcion = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Identificador (A - B - C - D - E)?");
        idBuque = scanner.nextLine().charAt(0);
        idBuque = Character.toUpperCase(idBuque);
        Buque buque = null;
        for (Buque bq : listadoBuque) {
            if (bq.getIdBuque() == idBuque) {
                buque = bq;
                break;
            }
        }

        switch (opcion) {
            // operacion completa
            case 1 -> {
                if (buque.isVacio()) {
                    System.out.println("No se puede realizar una operación completa porque ya está cargado");
                    return;
                } else {

                    System.out.println("Fecha inicio: dia (1-31)");
                    dia = scanner.nextInt();
                    scanner.nextLine();

                    while (!(dia > 0 && dia < 32)) {
                        System.out.println("Fecha inicio: dia (1-31)");
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (dia == 29 || dia == 30) {
                        System.out.println("Fecha inicio: mes ('Febrero no puede ser')");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes == 2 || mes < 1 || mes > 12) {
                            System.out.println("Fecha inicio: mes (1-12)");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }

                    } else if (dia == 31) {
                        System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
                            System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }
                    } else {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes < 1 || mes > 12) {
                            System.out.println("Fecha inicio: mes (1-12)");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Fecha inicio: año");
                    anio = scanner.nextInt();
                    scanner.nextLine();
                    fechaInicial = LocalDate.of(anio, mes, dia);

                    for (OperacionesBuque ob : buque.getOperacionesBuque()) {
                        if (fechaInicial.isAfter(ob.getFechaInicio()) && fechaInicial.isBefore(ob.getFechaFin())) {
                            System.out.println("Hay otra operación durante esas fechas, no se puede realizar ninguna nueva");
                            return;
                        }
                    }

                    System.out.println("El buque " + buque.getNombreBuque() + " se encuentra en " + buque.getUbicacionActual().getNombrePuerto());
                    tipoPuerto = buque.getUbicacionActual().getTipoPuerto();


                    switch (tipoPuerto) {
                        case YACIMIENTO -> System.out.println("Se puede cargar crudo");
                        case DEPOSITO, REFINERIA -> System.out.println("Se puede cargar fuel, fosil y gasolina");
                    }

                    // Preguntas relacionadas con la carga
                    System.out.println("¿Quiere realizar la carga? S-N");
                    confirmacionCarga = scanner.nextLine().charAt(0);
                    confirmacionCarga = Character.toUpperCase(confirmacionCarga);
                    while (confirmacionCarga != 'S' && confirmacionCarga != 'N') {
                        System.out.println("¿Quiere realizar la carga? S-N");
                        confirmacionCarga = scanner.nextLine().charAt(0);
                        confirmacionCarga = Character.toUpperCase(confirmacionCarga);
                    }

                    switch (tipoPuerto) {
                        case YACIMIENTO -> {
                            cargaNueva = TipoCarga.CRUDO;
                        }
                        default -> {
                            System.out.println("[F]uel, Gas[O]il o [G]asolina");
                            cargaRespuesta = scanner.nextLine().toUpperCase().charAt(0);

                            //este while no está bien
                            while (cargaRespuesta != 'F' && cargaRespuesta != 'O' && cargaRespuesta != 'G') {
                                System.out.println("[F]uel, F[O]sil o [G]asolina");
                                cargaRespuesta = scanner.nextLine().toUpperCase().charAt(0);
                            }
                        }
                    }

                    if (cargaRespuesta == 'F') {
                        cargaNueva = TipoCarga.FUEL;
                    } else if (cargaRespuesta == 'O') {
                        cargaNueva = TipoCarga.GASOIL;
                    } else if (cargaRespuesta == 'G') {
                        cargaNueva = TipoCarga.GASOLINA;
                    }

                    if (confirmacionCarga == 'S') {
                        System.out.println("¿Cuánto tiempo va a durar la carga? Día");
                        duracionCarga = scanner.nextInt();
                        scanner.nextLine();
                    }
                }

                // Preguntas relacionadas con el traslado
                System.out.println("¿Quiere realizar el traslado? S-N");
                confirmacionTraslado = scanner.nextLine().charAt(0);
                confirmacionTraslado = Character.toUpperCase(confirmacionTraslado);
                while (confirmacionTraslado != 'S' && confirmacionTraslado != 'N') {
                    System.out.println("¿Quiere realizar el traslado? S-N");
                    confirmacionTraslado = scanner.nextLine().charAt(0);
                    confirmacionTraslado = Character.toUpperCase(confirmacionTraslado);
                }

                if (confirmacionTraslado == 'S') {
                    System.out.println("Puertos de posible destino del buque: [Escoja el número]");

                    if (cargaNueva.equals("CRUDO")) {
                        for (Puerto pt : listadoPuertos) {
                            if (pt.equals(buque.getUbicacionActual())) {
                                continue;
                            }
                            if (pt.getTipoPuerto() == TipoPuerto.REFINERIA) {
                                System.out.println(pt.getIdPuerto() + "- " + pt.getNombrePuerto());
                                System.out.println("Tipo: REFINERIA");
                                System.out.println("\n");
                            }
                        }
                    } else {
                        for (Puerto pt : listadoPuertos) {
                            if (pt.equals(buque.getUbicacionActual())) {
                                continue;
                            }
                            if (pt.getTipoPuerto() == TipoPuerto.DEPOSITO) {
                                System.out.println(pt.getIdPuerto() + "- " + pt.getNombrePuerto());
                                System.out.println("Tipo: " + pt.getTipoPuerto());
                                System.out.println("\n");
                            }
                        }
                    }

                    int idPuerto = scanner.nextInt();
                    scanner.nextLine();

                    while (!(idPuerto > 0 && idPuerto < 11)) {
                        System.out.println("Número entre 1-10");
                        idPuerto = scanner.nextInt();
                        scanner.nextLine();
                    }

                    for (Puerto puerto : listadoPuertos) {
                        if (puerto.getIdPuerto() == idPuerto) {
                            puertoDestino = puerto;
                        }
                    }

                    System.out.println("¿Cuánto tiempo va a durar el traslado? Día");
                    duracionTraslado = scanner.nextInt();
                    scanner.nextLine();

                    // preguntas relacionadas con la descarga, solo ocurre si se va a trasladar el buque y
                    // si ha cargado o si no estaba vacío
                    if (confirmacionCarga == 'S') {

                        System.out.println("¿Quiere realizar la descarga? S-N");
                        confirmacionDescarga = scanner.nextLine().charAt(0);
                        confirmacionDescarga = Character.toUpperCase(confirmacionDescarga);
                        while (confirmacionDescarga != 'S' && confirmacionDescarga != 'N') {
                            System.out.println("¿Quiere realizar la descarga? S-N");
                            confirmacionDescarga = scanner.nextLine().charAt(0);
                            confirmacionDescarga = Character.toUpperCase(confirmacionDescarga);
                        }

                        if (confirmacionDescarga == 'S') {
                            System.out.println("¿Cuánto tiempo va a durar la descarga? Día");
                            duracionDescarga = scanner.nextInt();
                            scanner.nextLine();
                        }
                    }
                }

                System.out.println("Resumen de la operación");
                System.out.println("Fecha comienzo: " + fechaInicial.getDayOfMonth() + "/" + fechaInicial.getMonthValue() + "/" + fechaInicial.getYear());
                System.out.println("Puerto origen: " + buque.getUbicacionActual());
                System.out.println("Tipo de carga: " + cargaNueva);
                System.out.println("Duración carga: " + duracionCarga);

                if (confirmacionTraslado == 'S') {
                    System.out.println("Puerto destino: " + puertoDestino.getNombrePuerto());
                    System.out.println("Duración traslado: " + duracionTraslado);
                }

                if (confirmacionDescarga == 'S') {
                    System.out.println("Duración descarga: " + duracionDescarga);

                }

                System.out.println("¿Es correcta la operación? S-N");
                char confirmacionOperacion = scanner.nextLine().charAt(0);
                confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                while (confirmacionOperacion != 'S' && confirmacionOperacion != 'N') {
                    System.out.println("¿Quiere realizar la descarga? S-N");
                    confirmacionOperacion = scanner.nextLine().charAt(0);
                    confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                }

                if (confirmacionOperacion == 'S') {
                    if (confirmacionCarga == 'S') {
                        OperacionesBuque operacionesBuque1 = new OperacionesBuque(
                            fechaInicial,
                            fechaInicial.plusDays(duracionCarga),
                            duracionCarga,
                            TipoOperacion.CARGA,
                            cargaNueva,
                            buque.getUbicacionActual(),
                            puertoDestino);
                        buque.getOperacionesBuque().add(operacionesBuque1);
                    }

                    if (confirmacionTraslado == 'S') {
                        OperacionesBuque operacionesBuque2 = new OperacionesBuque(
                            fechaInicial.plusDays(duracionCarga),
                            fechaInicial.plusDays(duracionCarga).plusDays(duracionTraslado),
                            duracionTraslado,
                            TipoOperacion.TRANSITO,
                            cargaNueva,
                            buque.getUbicacionActual(),
                            puertoDestino);
                        buque.getOperacionesBuque().add(operacionesBuque2);

                    }

                    if (confirmacionDescarga == 'S') {
                        OperacionesBuque operacionesBuque3 = new OperacionesBuque(
                            fechaInicial.plusDays(duracionCarga).plusDays(duracionTraslado),
                            fechaInicial.plusDays(duracionCarga).plusDays(duracionTraslado).plusDays(duracionDescarga),
                            duracionDescarga,
                            TipoOperacion.DESCARGA,
                            cargaNueva,
                            buque.getUbicacionActual(),
                            puertoDestino);
                        buque.getOperacionesBuque().add(operacionesBuque3);
                    }

                } else {
                    System.out.println("No se realizará ningún cambio");
                }
            }
            // solo cargar
            case 2 -> {
                if (!buque.isVacio()) {
                    System.out.println("No se puede cargar un barco que lleve una mercancía");
                    return;
                } else {
                    System.out.println("Fecha inicio: dia (1-31)");
                    dia = scanner.nextInt();
                    scanner.nextLine();

                    while (!(dia > 0 && dia < 32)) {
                        System.out.println("Fecha inicio: dia (1-31)");
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (dia == 29 || dia == 30) {
                        System.out.println("Fecha inicio: mes ('Febrero no puede ser')");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes == 2 || mes < 1 || mes > 12) {
                            System.out.println("Fecha inicio: mes (1-12)");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }

                    } else if (dia == 31) {
                        System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
                            System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }
                    } else {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();

                        while (mes < 1 || mes > 12) {
                            System.out.println("Fecha inicio: mes (1-12)");
                            mes = scanner.nextInt();
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Fecha inicio: año");
                    anio = scanner.nextInt();
                    scanner.nextLine();
                    fechaInicial = LocalDate.of(anio, mes, dia);

                    for (OperacionesBuque ob : buque.getOperacionesBuque()) {
                        if (fechaInicial.isAfter(ob.getFechaInicio()) && fechaInicial.isBefore(ob.getFechaFin())) {
                            System.out.println("Hay otra operación durante esas fechas, no se puede realizar ninguna nueva");
                            return;
                        }
                    }

                    System.out.println("Cuanto durará la carga: Dia");
                    duracionCarga = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("El barco se encuentra en: " + buque.getUbicacionActual().getNombrePuerto());
                    switch (buque.getUbicacionActual().getTipoPuerto()) {
                        case YACIMIENTO -> {
                            cargaNueva = TipoCarga.CRUDO;
                        }
                        default -> {
                            System.out.println("[F]uel, Gas[O]il o [G]asolina");
                            cargaRespuesta = scanner.nextLine().toUpperCase().charAt(0);

                            //este while no está bien
                            while (cargaRespuesta != 'F' && cargaRespuesta != 'O' && cargaRespuesta != 'G') {
                                System.out.println("[F]uel, F[O]sil o [G]asolina");
                                cargaRespuesta = scanner.nextLine().toUpperCase().charAt(0);
                            }
                        }
                    }

                    if (cargaRespuesta == 'F') {
                        cargaNueva = TipoCarga.FUEL;
                    } else if (cargaRespuesta == 'O') {
                        cargaNueva = TipoCarga.GASOIL;
                    } else if (cargaRespuesta == 'G') {
                        cargaNueva = TipoCarga.GASOLINA;
                    }

                    System.out.println("Resumen de la operación");
                    System.out.println("Fecha comienzo: " + fechaInicial.getDayOfMonth() + "/" + fechaInicial.getMonthValue() + "/" + fechaInicial.getYear());
                    System.out.println("Puerto origen: " + buque.getUbicacionActual().getNombrePuerto());
                    System.out.println("Tipo de carga: " + cargaNueva);
                    System.out.println("Duración carga: " + duracionCarga);

                    System.out.println("¿Es correcta la operación? S-N");
                    char confirmacionOperacion = scanner.nextLine().charAt(0);
                    confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                    while (confirmacionOperacion != 'S' && confirmacionOperacion != 'N') {
                        System.out.println("¿Quiere realizar la descarga? S-N");
                        confirmacionOperacion = scanner.nextLine().charAt(0);
                        confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                    }

                    if (confirmacionOperacion == 'S') {
                        OperacionesBuque operacionesBuque1 = new OperacionesBuque(
                            fechaInicial,
                            fechaInicial.plusDays(duracionCarga),
                            duracionCarga,
                            TipoOperacion.CARGA,
                            cargaNueva,
                            buque.getUbicacionActual(),
                            puertoDestino);
                        buque.getOperacionesBuque().add(operacionesBuque1);
                    }
                }
            }
            // solo desplazar
            case 3 -> {
                System.out.println("Fecha inicio: dia (1-31)");
                dia = scanner.nextInt();
                scanner.nextLine();

                while (!(dia > 0 && dia < 32)) {
                    System.out.println("Fecha inicio: dia (1-31)");
                    dia = scanner.nextInt();
                    scanner.nextLine();
                }

                if (dia == 29 || dia == 30) {
                    System.out.println("Fecha inicio: mes ('Febrero no puede ser')");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes == 2 || mes < 1 || mes > 12) {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }

                } else if (dia == 31) {
                    System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
                        System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Fecha inicio: mes (1-12)");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes < 1 || mes > 12) {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }
                }

                System.out.println("Fecha inicio: año");
                anio = scanner.nextInt();
                scanner.nextLine();
                fechaInicial = LocalDate.of(anio, mes, dia);

                for (OperacionesBuque ob : buque.getOperacionesBuque()) {
                    if (fechaInicial.isAfter(ob.getFechaInicio()) && fechaInicial.isBefore(ob.getFechaFin())) {
                        System.out.println("Hay otra operación durante esas fechas, no se puede realizar ninguna nueva");
                        return;
                    }
                }

                System.out.println("Cuanto durará el traslado: Dia");
                duracionTraslado = scanner.nextInt();
                scanner.nextLine();


                for (Puerto pt : listadoPuertos) {
                    if (!pt.equals(buque.getUbicacionActual())) {
                        System.out.println(pt.getIdPuerto() + "- " + pt.getNombrePuerto());
                        System.out.println("Tipo: REFINERIA");
                        System.out.println("\n");

                    }
                }

                System.out.println("¿En qué puerto va a desembarcar? Escoja uno de los mostrados");
                int idPuerto = scanner.nextInt();
                scanner.nextLine();

                while (idPuerto < 1 || idPuerto > 10) {
                    System.out.println("¿En qué puerto va a desembarcar?");
                    idPuerto = scanner.nextInt();
                    scanner.nextLine();
                }

                for (Puerto pt : listadoPuertos) {
                    if (pt.getIdPuerto() == idPuerto) {
                        puertoDestino = pt;
                    }
                }

                System.out.println("Resumen de la operación");
                System.out.println("Fecha comienzo: " + fechaInicial.getDayOfMonth() + "/" + fechaInicial.getMonthValue() + "/" + fechaInicial.getYear());
                System.out.println("Puerto origen: " + buque.getUbicacionActual().getNombrePuerto());
                if (!buque.isVacio()) {
                    System.out.println("Tipo de carga: " + cargaNueva);
                }
                System.out.println("Duración traslado: " + duracionTraslado);
                System.out.println("Puerto destino: " + puertoDestino.getNombrePuerto());

                System.out.println("¿Es correcta la operación? S-N");
                char confirmacionOperacion = scanner.nextLine().charAt(0);
                confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                while (confirmacionOperacion != 'S' && confirmacionOperacion != 'N') {
                    System.out.println("¿Quiere realizar la descarga? S-N");
                    confirmacionOperacion = scanner.nextLine().charAt(0);
                    confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                }

                if (confirmacionOperacion == 'S') {
                    OperacionesBuque operacionesBuque1 = new OperacionesBuque(
                        fechaInicial,
                        fechaInicial.plusDays(duracionCarga),
                        duracionCarga,
                        TipoOperacion.TRANSITO,
                        buque.getTipoCarga(),
                        buque.getUbicacionActual(),
                        puertoDestino);
                    buque.getOperacionesBuque().add(operacionesBuque1);
                }
            }
            //solo descargar
            case 4 -> {
                if (buque.isVacio()) {
                    System.out.println("El buque está vacío, no se puede descargar");
                    return;
                }

                List<Integer> auxIdPuerto = new ArrayList<>();
                int i = 0;

                System.out.println("Fecha inicio: dia (1-31)");
                dia = scanner.nextInt();
                scanner.nextLine();

                while (!(dia > 0 && dia < 32)) {
                    System.out.println("Fecha inicio: dia (1-31)");
                    dia = scanner.nextInt();
                    scanner.nextLine();
                }

                if (dia == 29 || dia == 30) {
                    System.out.println("Fecha inicio: mes ('Febrero no puede ser')");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes == 2 || mes < 1 || mes > 12) {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }

                } else if (dia == 31) {
                    System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
                        System.out.println("Fecha inicio: mes (Enero(1), Marzo(3), Mayo(5), Julio(7), Agosto(8), Octubre(10), Diciembre(12))");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Fecha inicio: mes (1-12)");
                    mes = scanner.nextInt();
                    scanner.nextLine();

                    while (mes < 1 || mes > 12) {
                        System.out.println("Fecha inicio: mes (1-12)");
                        mes = scanner.nextInt();
                        scanner.nextLine();
                    }
                }

                System.out.println("Fecha inicio: año");
                anio = scanner.nextInt();
                scanner.nextLine();
                fechaInicial = LocalDate.of(anio, mes, dia);

                for (OperacionesBuque ob : buque.getOperacionesBuque()) {
                    if (fechaInicial.isAfter(ob.getFechaInicio()) && fechaInicial.isBefore(ob.getFechaFin())) {
                        System.out.println("Hay otra operación durante esas fechas, no se puede realizar ninguna nueva");
                        return;
                    }
                }

                System.out.println("¿Cuánto tiempo va a durar la descarga? Día");
                duracionDescarga = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Ubicaión actual: " + buque.getUbicacionActual().getNombrePuerto());

                if (buque.getUbicacionActual().getTipoPuerto() == TipoPuerto.YACIMIENTO) {
                    System.out.println("No se puede descargar nada");
                    return;
                } else if (buque.getUbicacionActual().getTipoPuerto() == TipoPuerto.REFINERIA) {
                    if (buque.getTipoCarga() != TipoCarga.CRUDO) {
                        System.out.println("El tipo de carga del buque no se puede dejar aquí");
                        return;
                    }
                } else if (buque.getUbicacionActual().getTipoPuerto() == TipoPuerto.DEPOSITO) {
                    if (buque.getTipoCarga() == TipoCarga.CRUDO) {
                        System.out.println("No se puede descargar crudo en este puerto");
                    }
                }

                System.out.println("Resumen operación descarga: ");
                System.out.println("Fecha comienzo: " + fechaInicial.getDayOfMonth() + "/" + fechaInicial.getMonthValue() + "/" + fechaInicial.getYear());
                System.out.println("Puerto origen: " + buque.getUbicacionActual());
                System.out.println("Puerto destino: " + puertoDestino.getNombrePuerto());
                System.out.println("Tipo de carga: " + buque.getTipoCarga().name());
                System.out.println("Duración descarga: " + duracionDescarga);


                System.out.println("¿Es correcta la operación? S-N");
                char confirmacionOperacion = scanner.nextLine().charAt(0);
                confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                while (confirmacionOperacion != 'S' && confirmacionOperacion != 'N') {
                    System.out.println("¿Quiere realizar la descarga? S-N");
                    confirmacionOperacion = scanner.nextLine().charAt(0);
                    confirmacionOperacion = Character.toUpperCase(confirmacionOperacion);
                }

                if (confirmacionOperacion == 'S') {
                    OperacionesBuque operacionesBuque1 = new OperacionesBuque(
                        fechaInicial,
                        fechaInicial.plusDays(duracionDescarga),
                        duracionDescarga,
                        TipoOperacion.DESCARGA,
                        buque.getTipoCarga(),
                        buque.getUbicacionActual(),
                        puertoDestino);
                    buque.getOperacionesBuque().add(operacionesBuque1);
                }
            }
        }
    }

    static void resumenMensualBuque() {

        int tiempoCarga = 0;
        int tiempoTraslado = 0;
        int tiempoDescarga = 0;
        Buque buque = null;
        char opcion = 'S';
        List<OperacionesBuque> operaciones = new ArrayList<>();
        List<OperacionesBuque> operacionesMes = new ArrayList<>();
        int numOperacion = 1;

        while (opcion == 'S') {

            System.out.println("Identificador (A - B - C - D - E)?");
            char idBuque = scanner.nextLine().charAt(0);
            idBuque = Character.toUpperCase(idBuque);

            while (idBuque != 'A' && idBuque != 'B' && idBuque != 'C' && idBuque != 'D' && idBuque != 'E') {
                System.out.println("Identificador (A - B - C - D - E)?");
                idBuque = scanner.nextLine().charAt(0);
                idBuque = Character.toUpperCase(idBuque);
            }

            System.out.println("Fecha inicio: mes (1-12)");
            int mes = scanner.nextInt();
            scanner.nextLine();

            while (mes < 1 || mes > 12) {
                System.out.println("Fecha inicio: mes (1-12)");
                mes = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Fecha inicio: año");
            int anio = scanner.nextInt();
            scanner.nextLine();

            for (Buque bq : listadoBuque) {
                if (bq.getIdBuque() == idBuque) {
                    buque = bq;
                }
            }

            if (buque == null) {
                return;
            }

            operaciones = buque.getOperacionesBuque();
            for (OperacionesBuque ob : operaciones) {
                if (ob.getFechaInicio().getMonthValue() == mes) {
                    operacionesMes.add(ob);
                }
            }

            //preguntar puerto destino

            for (OperacionesBuque op: operacionesMes) {
                System.out.println("Número operación: " + numOperacion);
                System.out.println("Tipo operación: " + op.getTipoOperacion().name());
                System.out.println("Fecha inicio: " + op.getFechaInicio());
                System.out.println("Fecha fin: " + op.getFechaFin());

                if (op.getTipoOperacion() == TipoOperacion.CARGA) {
                    tiempoCarga++;
                } else if (op.getTipoOperacion() == TipoOperacion.TRANSITO) {
                    tiempoTraslado++;
                    System.out.println("Puerto inicio: " + op.getPuertoInicio().getNombrePuerto());
                    System.out.println("Puerto destino: " + op.getPuertoDestino().getNombrePuerto());
                } else if (op.getTipoOperacion() == TipoOperacion.DESCARGA) {
                    tiempoDescarga++;
                }
                numOperacion++;
            }

            System.out.println("Tiempo de cargas (C): " + tiempoCarga);
            System.out.println("Tiempo de traslados (T#): " + tiempoTraslado);
            System.out.println("Tiempo de descargas (D): " + tiempoDescarga);

            System.out.println("¿Mostrar otro mes (S/N)?");
            opcion = scanner.nextLine().toUpperCase().charAt(0);

            while (opcion != 'S' && opcion != 'N') {
                System.out.println("¿Mostrar otro mes?");
                opcion = scanner.nextLine().toUpperCase().charAt(0);
            }

            tiempoCarga = 0;
            tiempoTraslado = 0;
            tiempoDescarga = 0;
            operaciones = new ArrayList<>();
            operacionesMes = new ArrayList<>();
            numOperacion = 1;
        }
    }

    static void salir() {
        System.out.println("Muchas gracias, hasta luego.");
    }

    static void imprimirListadoPuertos(List<Puerto> listadoPuertos) {
        for (Puerto pt : listadoPuertos) {
            pt.imprimirInfoPuerto();
        }
    }

    static void imprimirListadoBuques(List<Buque> listadoBuques) {
        for (Buque bq : listadoBuques) {
            bq.imprimirBuque();
        }
    }
}
