package Facturas;

import Facturas.Enums.Tipodecomprobante;

import java.lang.reflect.Constructor;
import java.time.LocalDate;

public class CargaFact<T extends Factura>{

    private Tipodecomprobante tipo; // en lugar de Class<T>

    public CargaFact(Tipodecomprobante tipo) {
        this.tipo = tipo;
    }

    public Factura crearFactura(String cuit, int sucursal, int numFactura, LocalDate fecha) {
        Factura factura = null;

        switch (tipo) {
            case A:
                factura = new FacturaA(cuit, sucursal, numFactura, fecha);
                break;
            case B:
                factura = new FacturaB(cuit, sucursal, numFactura, fecha);
                break;
            case C:
                factura = new FacturaC(cuit, sucursal, numFactura, fecha);
                break;
            default:
                System.out.println("⚠️ Tipo de factura no reconocido.");
        }

        return factura;
    }

}