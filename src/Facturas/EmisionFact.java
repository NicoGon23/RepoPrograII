package Facturas;

import java.time.LocalDate;

public class EmisionFact {

    public static Factura crearFactura(int tipo, String cuil, int sucursal, int numFactura, LocalDate fecha) {
        Factura factura = null;

        switch (tipo) {
            case 1: {
                factura = new FacturaA(cuil, sucursal, numFactura, fecha);
                break;
            }
            case 2: {
                factura = new FacturaB(cuil, sucursal, numFactura, fecha);
                break;
            }
            case 3: {
                factura = new FacturaC(cuil, sucursal, numFactura, fecha);
                break;
            }
            default: {
                System.out.println("Tipo de factura inválido.");
                break;
            }
        }

        return factura;
    }




}
