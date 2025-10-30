package Gestor;

import Facturas.*;
import Facturas.Enums.Tipodecomprobante;
import Facturas.Enums.TipoOperacion;
import Entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestor {

    private List<Factura> listaFacturas = new ArrayList<>();
    private GestoraEntidades gestoraEntidades = new GestoraEntidades();

    public void cargarFactura(Scanner scanner) {
        try {
            System.out.print("¿Desea cargar una factura de (1) Compra o (2) Venta?: ");
            int opcionOperacion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcionOperacion != 1 && opcionOperacion != 2) {
                System.out.println("Opción inválida. Debe ingresar 1 o 2.");
                return;
            }

            TipoOperacion tipoOperacion = opcionOperacion == 1 ? TipoOperacion.COMPRA : TipoOperacion.VENTA;

            System.out.print("Ingrese tipo de factura (A/B/C): ");
            String tipoFacturaStr = scanner.next().toUpperCase();
            Tipodecomprobante tipoFactura;
            switch (tipoFacturaStr) {
                case "A": {
                    tipoFactura = Tipodecomprobante.A;
                    break;
                }
                case "B": {
                    tipoFactura = Tipodecomprobante.B;
                    break;
                }
                case "C": {
                    tipoFactura = Tipodecomprobante.C;
                    break;
                }
                default: {
                    System.out.println("Tipo de factura inválido. Debe ser A, B o C.");
                    return;
                }
            }

            System.out.print("Ingrese CUIT: ");
            String cuit = scanner.next();
            scanner.nextLine(); // limpiar buffer

            Entidad persona = gestoraEntidades.buscarPorCuit(cuit);

            if (tipoOperacion == TipoOperacion.COMPRA) {
                Proveedor proveedor;
                if (persona != null && persona instanceof Proveedor) {
                    proveedor = (Proveedor) persona;
                    System.out.println("Proveedor encontrado: " + proveedor);
                } else {
                    System.out.println("Proveedor no encontrado. Ingrese datos del nuevo proveedor:");
                    proveedor = gestoraEntidades.crearProveedor(scanner, cuit);
                    gestoraEntidades.agregarProveedor(proveedor);
                }
            } else { // VENTA
                Cliente cliente;
                if (persona != null && persona instanceof Cliente) {
                    cliente = (Cliente) persona;
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("Cliente no encontrado. Ingrese datos del nuevo cliente:");
                    cliente = gestoraEntidades.crearCliente(scanner, cuit);
                    gestoraEntidades.agregarCliente(cliente);
                }
            }

            System.out.print("Ingrese número de sucursal: ");
            int sucursal = scanner.nextInt();
            System.out.print("Ingrese número de factura: ");
            int numeroFactura = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            LocalDate fecha = LocalDate.now();

            Factura factura = null;
            switch (tipoFactura) {
                case A: {
                    factura = new FacturaA(cuit, sucursal, numeroFactura, fecha, tipoOperacion);
                    break;
                }
                case B: {
                    factura = new FacturaB(cuit, sucursal, numeroFactura, fecha, tipoOperacion);
                    break;
                }
                case C: {
                    factura = new FacturaC(cuit, sucursal, numeroFactura, fecha, tipoOperacion);
                    break;
                }
            }

            if (factura != null) {
                factura.cargaDatos(scanner);
                listaFacturas.add(factura);
                System.out.println("Factura agregada correctamente!");
            }

        } catch (InputMismatchException e) {
            System.out.println("ERROR: Debe ingresar datos válidos.");
            scanner.nextLine();
        }
    }

    public void verFacturas() {
        if (listaFacturas.isEmpty()) {
            System.out.println("No hay facturas cargadas.");
            return;
        }
        System.out.println("=== LISTADO DE FACTURAS ===");
        int index = 1;
        for (Factura f : listaFacturas) {
            System.out.println(index++ + ". " + f);
        }
    }

    public void verFacturasPorTipoOperacion(TipoOperacion tipoOperacion) {
        List<Factura> filtradas = new ArrayList<>();
        for (Factura f : listaFacturas) {
            if (f.getTipoOperacion() == tipoOperacion) {
                filtradas.add(f);
            }
        }

        if (filtradas.isEmpty()) {
            System.out.println("No hay facturas de tipo " + tipoOperacion + ".");
            return;
        }

        System.out.println("---------- LISTADO DE FACTURAS DE " + tipoOperacion + " ----------");
        int i = 1;
        for (Factura f : filtradas) {
            System.out.println(i++ + ". " + f);
            System.out.println("-----------------------------");
        }
    }
}
