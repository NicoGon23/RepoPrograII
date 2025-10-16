package Facturas.Enums;

import Facturas.Factura;

import java.time.LocalDate;

public class FacturaC extends Factura {

    public FacturaC(double nogrado, String cuit, Tipodecomprovante tipo, int sucursal, int numerodefactura, LocalDate fecha) {
        super(nogrado, cuit, tipo, sucursal, numerodefactura, fecha);
        setTotal(calculototal());
    }

    @Override
    public double calculototal() {
        return getNogrado();
    }

    @Override
    public String toString() {
        return super.toString() + " Total: " + getTotal();
    }
}
