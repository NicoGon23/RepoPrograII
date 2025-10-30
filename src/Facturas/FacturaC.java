package Facturas;

import Facturas.Enums.Tipodecomprobante;
import Facturas.Enums.TipoOperacion;

import java.time.LocalDate;
import java.util.Scanner;

public class FacturaC extends Factura {

    // Constructor principal
    public FacturaC(String cuit, int sucursal, int numeroDeFactura, LocalDate fecha, TipoOperacion tipoOperacion) {
        super(0, cuit, Tipodecomprobante.C, sucursal, numeroDeFactura, fecha);
        setTipoOperacion(tipoOperacion);
    }

    @Override
    public double calculototal() {
        return getNogrado();
    }

    @Override
    public void cargaDatos(Scanner scanner) {
        try {
            // System.out.print("Ingrese el tipo de operación (VENTA/COMPRA): "); esto quedo innecesario pero no lo borro por si acaso.
            // setTipoOperacion(TipoOperacion.valueOf(scanner.next().toUpperCase()));

            System.out.print("Ingrese el no gravado: ");
            setNogrado(scanner.nextDouble());

            setTotal(calculototal());
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
            scanner.nextLine(); // limpiar buffer
        }
    }

    @Override
    public String toString() {
        return "\n---------- FACTURA C ----------" +
                "\nCUIT: " + getCuit() +
                "\nTipo: " + getTipo() +
                "\nOperación: " + getTipoOperacion() +
                "\nSucursal: " + getSucursal() +
                "\nN° Factura: " + getNumerodefactura() +
                "\nFecha: " + getFecha() +
                "\n------------------------------------" +
                "\nNo Gravado: $" + String.format("%.2f", getNogrado()) +
                "\n------------------------------------" +
                "\nTOTAL: $" + String.format("%.2f", getTotal()) +
                "\n------------------------------------\n";
    }
}
