package Facturas;

import Facturas.Enums.Tipodecomprovante;

import java.time.LocalDate;
import java.util.Scanner;

public class FacturaC extends Factura {

    public FacturaC(double nogrado, String cuit, Tipodecomprovante tipo, int sucursal, int numerodefactura, LocalDate fecha) {
        super(nogrado, cuit, tipo, sucursal, numerodefactura, fecha);
        setTotal(calculototal());
    }

    public FacturaC(String cuit, int sucursal, int numeroDeFactura, LocalDate fecha) {
        super(0, cuit, Tipodecomprovante.A, sucursal, numeroDeFactura, fecha);
    }

    @Override
    public double calculototal() {
        return getNogrado();
    }

    @Override
    public void cargaDatos(Scanner scanner) {

        System.out.print("Ingrese el no gravado: ");
        setNogrado(scanner.nextDouble());

    }

    @Override
    public String toString() {
        return super.toString() + " Total: " + getTotal();
    }
}
