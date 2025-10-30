package Facturas;

import Facturas.Enums.Tipodecomprobante;
import Facturas.Enums.TipoOperacion;

import java.time.LocalDate;
import java.util.Scanner;

public class FacturaA extends Factura {

    private double neto21;
    private double neto105;
    private double percepcionIVA;
    private double percepcionIB;
    private double otrosImpuestos;
    private double IVA21;
    private double IVA105;

    // Constructor principal
    public FacturaA(String cuit, int sucursal, int numeroDeFactura, LocalDate fecha, TipoOperacion tipoOperacion) {
        super(0, cuit, Tipodecomprobante.A, sucursal, numeroDeFactura, fecha);
        setTipoOperacion(tipoOperacion);
    }

    @Override
    public double calculototal() {
        return getNogrado() + neto21 + neto105 + IVA21 + IVA105 + percepcionIVA + percepcionIB + otrosImpuestos;
    }

    private double calcularIVA21() { return neto21 * 0.21; }
    private double calcularIVA105() { return neto105 * 0.105; }

    @Override
    public void cargaDatos(Scanner scanner) {
        try {
            // System.out.print("Ingrese el tipo de operación (VENTA/COMPRA): "); esto quedo innecesario pero no lo borro por si acaso.
            // setTipoOperacion(TipoOperacion.valueOf(scanner.next().toUpperCase()));

            System.out.print("Ingrese el no gravado: ");
            setNogrado(scanner.nextDouble());

            System.out.print("Ingrese el neto 21%: ");
            neto21 = scanner.nextDouble();

            System.out.print("Ingrese el neto 10.5%: ");
            neto105 = scanner.nextDouble();

            System.out.print("Ingrese percepción de IVA: ");
            percepcionIVA = scanner.nextDouble();

            System.out.print("Ingrese percepción de IIBB: ");
            percepcionIB = scanner.nextDouble();

            System.out.print("Ingrese otros impuestos: ");
            otrosImpuestos = scanner.nextDouble();

            IVA21 = calcularIVA21();
            IVA105 = calcularIVA105();

            setTotal(calculototal());

        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
            scanner.nextLine(); // limpiar buffer
        }
    }

    @Override
    public String toString() {
        return "\n---------- FACTURA A ----------" +
                "\nCUIT: " + getCuit() +
                "\nTipo: " + getTipo() +
                "\nOperación: " + getTipoOperacion() +
                "\nSucursal: " + getSucursal() +
                "\nN° Factura: " + getNumerodefactura() +
                "\nFecha: " + getFecha() +
                "\n------------------------------------" +
                "\nNo Gravado: $" + String.format("%.2f", getNogrado()) +
                "\nNeto 21%%: $" + String.format("%.2f", neto21) +
                "\nIVA 21%%: $" + String.format("%.2f", IVA21) +
                "\nNeto 10.5%%: $" + String.format("%.2f", neto105) +
                "\nIVA 10.5%%: $" + String.format("%.2f", IVA105) +
                "\nPercepción IVA: $" + String.format("%.2f", percepcionIVA) +
                "\nPercepción IB: $" + String.format("%.2f", percepcionIB) +
                "\nOtros impuestos: $" + String.format("%.2f", otrosImpuestos) +
                "\n------------------------------------" +
                "\nTOTAL: $" + String.format("%.2f", getTotal()) +
                "\n------------------------------------\n";
    }
}
