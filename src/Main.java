import Gestor.Gestor;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ FACTURAS ---");
            System.out.println("1. Cargar factura");
            System.out.println("2. Ver todas las facturas");
            System.out.println("3. Ver solo facturas de compra");
            System.out.println("4. Ver solo facturas de venta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    gestor.cargarFactura(scanner);
                    break;
                case 2:
                    gestor.verFacturas();  // Todas
                    break;
                case 3:
                    gestor.verFacturasPorTipoOperacion(Facturas.Enums.TipoOperacion.COMPRA);
                    break;
                case 4:
                    gestor.verFacturasPorTipoOperacion(Facturas.Enums.TipoOperacion.VENTA);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}


/// Cosas a corregir:
///el primer dato que se muestra es el nogrado que es el No Gravado es mover el dato en todos los tostring
/// No se muestra correctamente el Cuit/Cuil , calculo que es por algun dato mal pasado al constructor
///Añadir comentarios de como funciona el codigo , comentario de "Nico" me quedan 10 min para irme a la facu por eso no los agrege , lo hago despues.
///Subirlo a git , lo mismo del anterior , lo hago en la facu ..... espero
///Mover la factura C a facturas no se porque esta en los Enums , se me habra escapado