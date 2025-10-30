package Facturas;

import Facturas.Enums.Tipodecomprobante;
import Facturas.Enums.TipoOperacion;

import java.time.LocalDate;
import java.util.Scanner;

public class CargaFact {

    private Scanner scanner = new Scanner(System.in);

    public Factura crearFactura() {
        Factura factura = null;

        try {
            System.out.print("Ingrese el tipo de comprobante (A/B/C): ");
            Tipodecomprobante tipo = Tipodecomprobante.valueOf(scanner.next().toUpperCase());

            System.out.print("Ingrese CUIT: ");
            String cuit = scanner.next();

            System.out.print("Ingrese sucursal: ");
            int sucursal = scanner.nextInt();

            System.out.print("Ingrese número de factura: ");
            int numero = scanner.nextInt();

            System.out.print("Ingrese fecha (AAAA-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.next());

            System.out.print("Ingrese tipo de operación (COMPRA/VENTA): ");
            TipoOperacion operacion = TipoOperacion.valueOf(scanner.next().toUpperCase());

            // Instanciar la factura según el tipo
            switch (tipo) {
                case A:
                    factura = new FacturaA(cuit, sucursal, numero, fecha, operacion);
                    break;
                case B:
                    factura = new FacturaB(cuit, sucursal, numero, fecha, operacion);
                    break;
                case C:
                    factura = new FacturaC(cuit, sucursal, numero, fecha, operacion);
                    break;
            }

            if (factura != null) {
                factura.cargaDatos(scanner); // Llamada unificada
            }

        } catch (Exception e) {
            System.out.println("Error al crear la factura: " + e.getMessage());
            scanner.nextLine(); // limpiar buffer
        }

        return factura;
    }
}
