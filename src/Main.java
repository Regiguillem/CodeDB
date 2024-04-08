import controlador.ExcursionesControlador;
import controlador.InscripcionesControlador;
import controlador.SociosControlador;
import modelo.ExcursionesModelo;
import modelo.FederacionesModelo;
import modelo.SeguroBasicoModelo;
import modelo.SeguroCompletoModelo;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //Inciamos las instancias de los seguros
        SeguroCompletoModelo seguroCompleto = new SeguroCompletoModelo();
        SeguroBasicoModelo seguroBasico = new SeguroBasicoModelo();

        Scanner scanner = new Scanner(System.in);

        ExcursionesControlador controladorEx = new ExcursionesControlador();

        SociosControlador controladorSoc = new SociosControlador();

        InscripcionesControlador controladorInsc = new InscripcionesControlador();

        int opcion;

        do {
            // Menú Principal
            System.out.println("Menú Principal");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Controlador Excursiones");
            System.out.println("2. Controlador Socios");
            System.out.println("3. Controlador Inscripciones");
            System.out.println("0. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    controladorEx.iniciar();
                    break;
                case 2:
                    controladorSoc.iniciar();
                    break;
                case 3:
                    controladorInsc.iniciar();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}