package controlador;

import modelo.ExcursionesModelo;
import vista.ExcursionesVista;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExcursionesControlador {

    //Atributos para relacionar la vista con el arraylist de excursiones
    private ExcursionesVista vistaEx;
    private ArrayList<ExcursionesModelo> excursiones;

    //Controlador
    //Al iniciar el controlador iniciamos la vista y el arraylist
    public ExcursionesControlador() {
        this.vistaEx = new ExcursionesVista();
        this.excursiones = new ArrayList<>();

        //Creamos dos excursiones para comprobar si el filtro funciona sin tener que introducir excursiones cada vez que ejecutemos el programa
        ExcursionesModelo excursion = new ExcursionesModelo("A190", "Montaña", LocalDate.of(2024, 3, 28), 3, 120);
        ExcursionesModelo excursion2 = new ExcursionesModelo("B200", "Playa", LocalDate.of(2024, 4, 30), 1, 50);

        //Usamos el controlador para añadir las excursiones al arraylist de excursiones
        addExcursion(excursion);
        addExcursion(excursion2);
    }

    //Menú interno para seleccionar qué método se ejecutará una vez la vista haya pedido al usuario que seleccione una opción.
    public boolean iniciar(){
        int opcion;
        do {
            //Aqui se extrae la opción de la vista
            opcion = vistaEx.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarExcursion();
                    break;
                case 2:
                    mostrarExcursionesFiltradas();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de Excursiones...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 0);
        return false;
    }
    //Gracias a los datos introducidos por el usuario, el controlador añade el nuevo objeto de excursiones al arraylist
    public void agregarExcursion(){
        ExcursionesModelo excursion = vistaEx.DatosExcursion();
        excursiones.add(excursion);
        //Debería tener en cuenta posibles errores
        vistaEx.mostrarMensaje("Excursión añadida correctamente.");
    }

    //Función para añadir excursiones creadas en el MAIN, NO INTRODUCIDAS POR EL USUARIO EN LA VISTA.
    public void addExcursion(ExcursionesModelo excursion){
        excursiones.add(excursion);
    }

    //Iteramos sobre los objetos del arraylist para comprobar su fecha, si se encuentra entre las dos fechas facilitadas,
    // se imprimen por pantalla gracias a la función mostrarExcursion de la vista.
    public void mostrarExcursionesFiltradas(){
        LocalDate[] fechas = vistaEx.ExcursionesFechasFiltro();
        LocalDate fechaInicio = fechas[0];
        LocalDate fechaFinal = fechas[1];

        vistaEx.mostrarMensaje("Excursiones entre " + fechaInicio + " y " + fechaFinal + ":");
        //Quizás prodía hacerse mas sencillo utilizando isEqual, isAfter, isBefore, lo probaré.
        for (ExcursionesModelo excursion : excursiones) {
            LocalDate fechaExcursion = excursion.getFecha();
            if ((fechaExcursion.compareTo(fechaInicio) >= 0 && fechaExcursion.compareTo(fechaFinal) <= 0)) {
                vistaEx.mostrarExcursion(excursion);
            }
        }
    }

    public void mostrarExcursiones() {
        for (ExcursionesModelo excursion : excursiones) {
            System.out.println("Código: " + excursion.getCodigo());
            System.out.println("Nombre: " + excursion.getDescripcion());
            System.out.println("Fecha: " + excursion.getFecha());
            System.out.println("Descripción: " + excursion.getN_dias());
            System.out.println("-------------------------------------");
        }
    }

    public ExcursionesModelo obtenerExcursionPorCodigo(String codigo) {
        for (ExcursionesModelo excursion : excursiones) {
            if (excursion.getCodigo().equals(codigo)) {
                return excursion;
            }
        }
        return null;
    }
}
