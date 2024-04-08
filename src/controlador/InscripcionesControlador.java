package controlador;

import modelo.InscripcionesModelo;
import modelo.ExcursionesModelo;
import modelo.SociosModelo;
import vista.InscripcionesVista;
import controlador.SociosControlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class InscripcionesControlador {

    // Atributos para relacionar la vista con el ArrayList de inscripciones
    private InscripcionesVista vistaInsc;
    private ArrayList<InscripcionesModelo> inscripciones;
    private SociosControlador sociosControlador;
    private ExcursionesControlador excursionesControlador;

    // Constructor
    public InscripcionesControlador() {
        this.vistaInsc = new InscripcionesVista();
        this.inscripciones = new ArrayList<>();
        this.sociosControlador = sociosControlador;
        this.excursionesControlador = excursionesControlador;

    }

    // Método para iniciar el controlador y manejar el menú
    public boolean iniciar() {
        int opcion;
        do {
            opcion = vistaInsc.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarInscripcion();
                    break;
                case 2:
                    //mostrarInscripcion();
                    break;
                case 3:
                    //eliminarInscripcion();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 4);
        return false;
    }

    // Método para agregar una inscripción solicitando datos al usuario
    private void agregarInscripcion() {
        System.out.println("Añadiendo inscripción...");
        System.out.println("Introduzca el número de inscripción:");
        int nInscripcion = vistaInsc.solicitarNumeroInscripcion();
        ExcursionesModelo excursion = vistaInsc.solicitarExcursion();
        SociosModelo socio = vistaInsc.solicitarSocio();
        if (socio == null) {
            //Si el socio no existe solicitamos sus datos para añadirlo a la base de datos
            int opcion = vistaInsc.agregarSocioInsc();
            switch (opcion) {
                case 1:
                    sociosControlador.agregarSocioEstandar();
                    break;
                case 2:
                    sociosControlador.agregarSocioFederado();
                    break;
                case 3:
                    sociosControlador.agregarSocioInfantil();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de agregar socio...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        InscripcionesModelo inscripcion = new InscripcionesModelo(nInscripcion, socio, excursion);
        inscripciones.add(inscripcion);
        System.out.println("Inscripción añadida correctamente.");
    }

    // Método para mostrar inscripciones filtradas por fechas
    public void mostrarInscripcion() {
        LocalDate[] fechas = vistaInsc.fechasFiltroInscripciones();
        LocalDate fechaInicio = fechas[0];
        LocalDate fechaFin = fechas[1];

        vistaInsc.mostrarMensaje("Inscripciones entre " + fechaInicio + " y " + fechaFin + ":");

        for (InscripcionesModelo inscripcion : inscripciones) {
            LocalDate fechaExcursion = inscripcion.getExcursion().getFecha();
            if (fechaExcursion.compareTo(fechaInicio) >= 0 && fechaExcursion.compareTo(fechaFin) <= 0) {
                vistaInsc.mostrarInscripcion(inscripcion);
            }
        }
    }

    // Método para eliminar una inscripción

}
