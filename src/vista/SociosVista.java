package vista;
import controlador.SociosControlador;
import modelo.*;

import java.util.Scanner;

public class SociosVista {
    private Scanner scanner;
    private SociosControlador sociosControlador;

    public SociosVista() {
        this.scanner = new Scanner(System.in);
        this.sociosControlador = sociosControlador;
    }

    public int mostrarMenu() {
        System.out.println("Gestión de Socios");
        System.out.println("1. Añadir Socio Estándar");
        System.out.println("2. Modificar tipo de seguro de un socio estándar");
        System.out.println("3. Añadir Socio Federado");
        System.out.println("4. Añadir Socio Infantil");
        System.out.println("5. Eliminar socio");
        System.out.println("6. Mostrar socios filtrados:");
        System.out.println("7. Mostrar factura mensual de socio:");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public int solicitarNuevoTipoSeguro() {
        System.out.println("Introduzca el nuevo tipo de seguro: ");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        return opcion;
    }

    public void mostrarMensaje (String mensaje){
        System.out.println(mensaje);
    }

    // Métodos para solicitar información al usuario
    public int solicitarNumeroSocio() {
        System.out.print("Introduzca el número de socio: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        return codigo;
    }

    public String solicitarNombreSocio() {
        System.out.print("Introduzca el nombre del socio: ");
        return scanner.nextLine();
    }

    public String solicitarNifSocio() {
        System.out.print("Introduzca el NIF del socio: ");
        return scanner.nextLine();
    }

    public SeguroModelo solicitarSeguroSocio() {
        System.out.println("Seleccione el tipo de seguro para el socio:");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");

        int opcionSeguro = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcionSeguro) {
            case 1:
                return new SeguroCompletoModelo();
            case 2:
                return new SeguroBasicoModelo();
            default:
                System.out.println("Opción no válida. Seleccionando Seguro Básico por defecto.");
                return new SeguroBasicoModelo();
        }
    }

    public int opcionFederacion(){
        System.out.println("Seleccione una opción válida: ");
        int opcionFed = scanner.nextInt();
        return opcionFed;
    }

    public int solicitarNumeroSocioPadreMadre() {
        System.out.print("Introduzca el número de socio del padre o madre: ");
        return scanner.nextInt();
    }

    public void mostrarSocioInfantil(SocioInfantilModelo socioInfantil, String nombrePadreMadre) {
        System.out.println("Socio Infantil añadido correctamente:");
        System.out.println("Número de socio: " + socioInfantil.getN_socio());
        System.out.println("Nombre del socio: " + socioInfantil.getNombre());
        System.out.println("Número de socio del padre o madre: " + socioInfantil.getN_socioPadreMadre().getN_socio() + " (Nombre: " + nombrePadreMadre + ")");
        System.out.println("Descuento en cuota: " + socioInfantil.getDescuento_cuota() + "%");
    }

    public void mostrarSociosPorTipo() {
        System.out.println("Seleccione el tipo de socios que desea ver:");
        System.out.println("1. Socios Estándar");
        System.out.println("2. Socios Federados");
        System.out.println("3. Socios Infantiles");
        System.out.println("4. Mostrar todos los socios");
        System.out.println("0. Volver al menú anterior");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Mostrando socios estándar:");
                sociosControlador.mostrarSociosEst();
                break;
            case 2:
                System.out.println("Mostrando socios federados:");
                sociosControlador.mostrarSociosFed();
                break;
            case 3:
                System.out.println("Mostrando socios infantiles:");
                sociosControlador.mostrarSociosInf();
                break;
            case 4:
                System.out.println("Mostrando todos los socios:");
                sociosControlador.mostrarSocios();
                break;
            case 0:
                System.out.println("Volviendo al menú anterior.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    //Faltan argumentos que pedir al usuario?
}
