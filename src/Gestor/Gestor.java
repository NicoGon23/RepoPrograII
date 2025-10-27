package Gestor;

import Entidades.Entidad;
import Entidades.GestoraEntidades;
import Entidades.Proveedor;
import Facturas.*;
import Facturas.Enums.Tipodecomprobante;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class Gestor {
    List<Factura> Lista = new ArrayList<>();
    GestoraEntidades gestoraEntidades = new GestoraEntidades();


    public Gestor() {
    }


    public void Cargadefacturas(Scanner scanner) {
        int tipo = 0;
        try {
            System.out.print("Ingrese tipo de factura (1=A, 2=B, 3=C): ");
            tipo = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("⚠️ Entrada inválida. Debe ingresar un número entre 1 y 3.");
            scanner.nextLine();
            return;
        }
        if (tipo < 1 || tipo > 3) {
            System.out.println("⚠️ Tipo de factura inválido. Debe ser 1, 2 o 3.");
            return;
        }

        System.out.print("Ingrese el CUIT: ");
        String cuit = scanner.next();

        Entidad personaEncontrada = gestoraEntidades.buscarPorCuit(cuit);
        Proveedor proveedor;
        if (personaEncontrada != null && personaEncontrada instanceof Proveedor) {
            proveedor = (Proveedor) personaEncontrada;
            System.out.println("Proveedor encontrado: " + proveedor);
        } else {
            System.out.println("Proveedor no encontrado. Ingrese datos del nuevo proveedor:");

            scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();

            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Rubro: ");
            String rubro = scanner.nextLine();

            proveedor = new Proveedor(nombre, apellido, cuit, direccion, telefono, email, rubro);
            gestoraEntidades.agregarProveedor(proveedor);

            System.out.print("Ingrese número de sucursal: ");
            int sucursal = scanner.nextInt();

            System.out.print("Ingrese número de factura: ");
            int numFactura = scanner.nextInt();

            LocalDate fecha = LocalDate.now();

            Factura factura = null;

            switch (tipo) {
                case 1 -> {
                    CargaFact<FacturaA> cargaA = new CargaFact<>(Tipodecomprobante.A);
                    factura = cargaA.crearFactura(cuit, sucursal, numFactura, fecha);
                }
                case 2 -> {
                    CargaFact<FacturaB> cargaB = new CargaFact<>(Tipodecomprobante.B);
                    factura = cargaB.crearFactura(cuit, sucursal, numFactura, fecha);
                }
                case 3 -> {
                    CargaFact<FacturaC> cargaC = new CargaFact<>(Tipodecomprobante.C);
                    factura = cargaC.crearFactura(cuit, sucursal, numFactura, fecha);
                }
                default -> System.out.println("Tipo de factura inválido.");
            }

            if (factura != null) {
                factura.cargaDatos(scanner);
                Lista.add(factura);
                System.out.println("Factura " + factura.getTipo() + " agregada correctamente.");
            }
        }
    }

        public void verFacturasCargadas() {

            if (Lista.isEmpty()) {
                System.out.println("No hay facturas cargadas.");
                return;
            }

            System.out.println("=== LISTADO DE FACTURAS ===");
            int index = 1;
            for (Factura factura : Lista) {
                System.out.println(index++ + ". " + factura);
                System.out.println("----------------------------");
            }
        }

    }

