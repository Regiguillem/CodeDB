package controlador;
import modelo.*;
import vista.SociosVista;
import java.util.ArrayList;


public class SociosControlador {

    //Atributos para relacionar la vista con el arraylist de socios
    public SociosVista vistaSoc;
    private ArrayList<SocioEstandarModelo> sociosEst;
    private ArrayList<SociosFederadosModelo> sociosFed;
    private ArrayList<SocioInfantilModelo> sociosInf;
    public ArrayList<FederacionesModelo> federaciones;
    public ArrayList<SeguroModelo> seguros;
    public ArrayList<SociosModelo> listaSocios;

    //Creamos las instancias para los dos tipos de seguro
    SeguroCompletoModelo seguroCompleto = new SeguroCompletoModelo();
    SeguroBasicoModelo seguroBasico = new SeguroBasicoModelo();

    //Creamos las instancias de las federaciones para registrar los socios Federadors
    FederacionesModelo fedFEEC = new FederacionesModelo("A001", "FEEC");
    FederacionesModelo fedFEDME = new FederacionesModelo("B001", "FEDME");

    //Creamos instancias para tener socios ya registrados en el programa
    SocioEstandarModelo socioEst = new SocioEstandarModelo(1, "Guillem", "53316605Y", seguroCompleto);
    SociosFederadosModelo socioFed = new SociosFederadosModelo(2, "Sergi", "45678925J", fedFEDME);
    SocioInfantilModelo socioInf = new SocioInfantilModelo(3, "Andrea", socioEst);
    public SociosControlador() {
        this.vistaSoc = new SociosVista();
        this.sociosEst = new ArrayList<>();
        this.sociosFed = new ArrayList<>();
        this.sociosInf = new ArrayList<>();
        this.federaciones = new ArrayList<>();
        this.seguros = new ArrayList<>();
        this.listaSocios = new ArrayList<>();
        //Añadimos las federaciones en una lista de todas las federaciones
        federaciones.add(fedFEDME);
        federaciones.add(fedFEEC);
        //Añadimos los seguros en una lista de los tipos de seguros
        seguros.add(seguroCompleto);
        seguros.add(seguroBasico);
        //Añadimos los socios a sus listas
        sociosEst.add(socioEst);
        sociosFed.add(socioFed);
        sociosInf.add(socioInf);
        //Añadimos los socios en una lista global de socios
        listaSocios.add(socioEst);
        listaSocios.add(socioFed);
        listaSocios.add(socioInf);
    }

    public boolean iniciar() {
        int opcion;
        do {
            // Extraer la opción del menú desde la vista
            opcion = vistaSoc.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarSocioEstandar();
                    break;
                case 2:
                    modificarTipoSeguroSocio();
                    break;
                case 3:
                    agregarSocioFederado();
                    break;
                case 4:
                    agregarSocioInfantil();
                    break;
                case 5:
                    //eliminarSocio();
                    break;
                case 6:
                    vistaSoc.mostrarSociosPorTipo();
                case 7:
                    //mostrarFacturaMensualFiltroSocio();
                case 0:
                    System.out.println("Saliendo del menú de Gestión de Socios...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 0);
        return false;
    }

    public void agregarSocioEstandar() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Estándar...");

        // Solicitar la información del socio al usuario
        int n_socio = vistaSoc.solicitarNumeroSocio();
        String nombre = vistaSoc.solicitarNombreSocio();
        String nif = vistaSoc.solicitarNifSocio();
        SeguroModelo seguro = vistaSoc.solicitarSeguroSocio();

        // Crear una instancia de SocioEstandarModelo con la información proporcionada por el usuario
        SocioEstandarModelo socioEstandar = new SocioEstandarModelo(n_socio, nombre, nif, seguro);

        // Agregar el socio a la lista de socios
        sociosEst.add(socioEstandar);
        listaSocios.add(socioEstandar);

        vistaSoc.mostrarMensaje("Socio Estándar añadido correctamente.");
    }

    public void modificarTipoSeguroSocio() {
        vistaSoc.mostrarMensaje("Modificando tipo de seguro de un socio estándar...");

        // Mostrar la lista de socios estándar disponibles
        mostrarSociosEst();

        // Solicitar al usuario que elija el socio cuyo tipo de seguro desea modificar
        int numeroSocio = vistaSoc.solicitarNumeroSocio();
        SocioEstandarModelo socioSeleccionado = buscarSocioEstPorNumero(numeroSocio);

        if (socioSeleccionado != null) {
            // Solicitar al usuario que elija el nuevo tipo de seguro
            int opcionSeguro = vistaSoc.solicitarNuevoTipoSeguro();
            SeguroModelo nuevoSeguro = null;

            // Crear el nuevo tipo de seguro seleccionado por el usuario
            switch (opcionSeguro) {
                case 1:
                    nuevoSeguro = seguroCompleto;
                    break;
                case 2:
                    nuevoSeguro = seguroBasico;
                    break;
                default:
                    vistaSoc.mostrarMensaje("Opción no válida. No se realizarán cambios en el tipo de seguro.");
                    return;
            }

            // Actualizar el tipo de seguro del socio seleccionado
            socioSeleccionado.setSeguro(nuevoSeguro);
            vistaSoc.mostrarMensaje("Tipo de seguro del socio modificado correctamente.");
        } else {
            vistaSoc.mostrarMensaje("El socio con el número especificado no existe.");
        }
    }

    public void mostrarSociosEst() {
        System.out.println("Lista de Socios Estándar Disponibles:");
        for (SocioEstandarModelo socio : sociosEst) {
            System.out.println("Número de socio: " + socio.getN_socio() + ", Nombre: " + socio.getNombre());
        }
    }

    private SocioEstandarModelo buscarSocioEstPorNumero(int numeroSocio) {
        for (SocioEstandarModelo socio : sociosEst) {
            if (socio.getN_socio() == numeroSocio) {
                return socio;
            }
        }
        return null; // Retorna null si no se encuentra ningún socio con el número especificado
    }

    public void agregarSocioFederado() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Federado...");

        // Solicitar la información del socio federado al usuario
        int n_socio = vistaSoc.solicitarNumeroSocio();
        String nombre = vistaSoc.solicitarNombreSocio();
        String nif = vistaSoc.solicitarNifSocio();
        FederacionesModelo federacionSocio = solicitarFederacion();

        // Crear una instancia de SociosFederadosModelo con la información proporcionada por el usuario
        SociosFederadosModelo socioFederado = new SociosFederadosModelo(n_socio, nombre, nif, federacionSocio);

        // Agregar el socio federado a la lista de socios

        sociosFed.add(socioFederado);
        listaSocios.add(socioFederado);

        vistaSoc.mostrarMensaje("Socio Federado añadido correctamente.");
    }

    public void mostrarSociosFed() {
        if (sociosFed.isEmpty()) {
            System.out.println("No hay socios federados.");
        } else {
            System.out.println("Lista de socios federados:");
            for (SociosFederadosModelo socio : sociosFed) {
                System.out.println("Nombre: " + socio.getNombre() + ", NIF: " + socio.getNif() + ", Código Federación: " + socio.getFederacion());
            }
        }
    }
    public FederacionesModelo solicitarFederacion() {
        System.out.println("Seleccione la federación del socio:");

        // Mostrar las opciones de federaciones disponibles
        for (int i = 0; i < federaciones.size(); i++) {
            FederacionesModelo federacion = federaciones.get(i);
            System.out.println((i + 1) + ". " + federacion.getNombre() + " (Código: " + federacion.getCodigo() + ")");
        }

        int opcionFederacion;
        do {
            opcionFederacion = vistaSoc.opcionFederacion();
            if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
                System.out.println("Seleccione una opción válida.");
            }
        } while (opcionFederacion < 1 || opcionFederacion > federaciones.size());

        return federaciones.get(opcionFederacion - 1); // Devolver la federación seleccionada
    }

    public void agregarSocioInfantil() {
        vistaSoc.mostrarMensaje("Añadiendo Socio Infantil...");

        // Solicitar la información del socio infantil al usuario
        int n_socio = vistaSoc.solicitarNumeroSocio();
        String nombre = vistaSoc.solicitarNombreSocio();
        int n_socioPadreMadre = vistaSoc.solicitarNumeroSocioPadreMadre();

        // Buscar el socio padre o madre en la lista de socios estándar
        SocioEstandarModelo socioPadreMadre = buscarSocioEstPorNumero(n_socioPadreMadre);

        // Verificar si se encontró el socio padre o madre
        if (socioPadreMadre != null) {
            // Crear una instancia de SocioInfantilModelo con la información proporcionada por el usuario
            SocioInfantilModelo socioInfantil = new SocioInfantilModelo(n_socio, nombre, socioPadreMadre);

            // Agregar el socio infantil a la lista de socios infantiles
            sociosInf.add(socioInfantil);
            listaSocios.add(socioInfantil);

            vistaSoc.mostrarMensaje("Socio Infantil añadido correctamente.");
            // Mostrar la información del socio infantil agregado
            vistaSoc.mostrarSocioInfantil(socioInfantil, socioPadreMadre.getNombre());
        } else {
            vistaSoc.mostrarMensaje("El socio padre o madre con el número especificado no existe.");
        }
    }

    public void mostrarSociosInf() {
        if (sociosInf.isEmpty()) {
            System.out.println("No hay socios infantiles.");
        } else {
            System.out.println("Lista de socios infantiles:");
            for (SocioInfantilModelo socio : sociosInf) {
                System.out.println("Nombre: " + socio.getNombre() + ", Socio Padre/Madre: " + socio.getN_socioPadreMadre().getNombre());
            }
        }
    }

    public void mostrarSocios() {
        for (SociosModelo socio : listaSocios) {
            System.out.println("Número de socio: " + socio.getN_socio());
            System.out.println("Nombre: " + socio.getNombre());
            // Comprobar el tipo de socio y mostrar información específica según el tipo
            if (socio instanceof SocioEstandarModelo) {
                SocioEstandarModelo socioEstandar = (SocioEstandarModelo) socio;
                System.out.println("Tipo de socio: Estándar");
                // Mostrar más información específica del socio estándar si es necesario
                System.out.println("NIF: " + socioEstandar.getNif());
                System.out.println("Seguro: " + socioEstandar.getSeguro().getTipo());
            } else if (socio instanceof SociosFederadosModelo) {
                SociosFederadosModelo socioFederado = (SociosFederadosModelo) socio;
                System.out.println("Tipo de socio: Federado");
                // Mostrar más información específica del socio federado si es necesario
                System.out.println("NIF: " + socioFederado.getNif());
                System.out.println("Federación: " + socioFederado.getFederacion().getNombre());
            } else if (socio instanceof SocioInfantilModelo) {
                SocioInfantilModelo socioInfantil = (SocioInfantilModelo) socio;
                System.out.println("Tipo de socio: Infantil");
                // Mostrar más información específica del socio infantil si es necesario
                System.out.println("Número de socio del Padre/Madre: " + socioInfantil.getN_socioPadreMadre().getN_socio());
                System.out.println("Descuento Cuota: " + socioInfantil.getDescuento_cuota());
            }
            System.out.println("------------------------------------------");
        }
    }

    public SociosModelo obtenerSocioPorCodigo(int codigo) {
        for (SociosModelo socio : listaSocios) {
            if (codigo == socio.getN_socio()) {
                return socio;
            }
        }
        return null; // Devuelve null si no se encuentra ningún socio con el código proporcionado
    }
    // Otros métodos para manejar las demás opciones del menú de socios
}