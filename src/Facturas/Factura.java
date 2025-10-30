package Facturas;

import Facturas.Enums.Tipodecomprobante;
import Facturas.Enums.TipoOperacion;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Factura {

    private double nogrado;
    private String cuit;
    private Tipodecomprobante tipo;
    private int sucursal;
    private int numerodefactura;
    private LocalDate fecha;
    private double total;
    private TipoOperacion tipoOperacion; // NUEVO: compra o venta

    public Factura(double nogrado, String cuit, Tipodecomprobante tipo, int sucursal, int numerodefactura, LocalDate fecha) {
        this.nogrado = nogrado;
        this.cuit = cuit;
        this.tipo = tipo;
        this.sucursal = sucursal;
        this.numerodefactura = numerodefactura;
        this.fecha = fecha;
    }

    public double getNogrado() { return nogrado; }
    public void setNogrado(double nogrado) { this.nogrado = nogrado; }

    public String getCuit() { return cuit; }
    public void setCuit(String cuit) { this.cuit = cuit; }

    public Tipodecomprobante getTipo() { return tipo; }
    public void setTipo(Tipodecomprobante tipo) { this.tipo = tipo; }

    public int getSucursal() { return sucursal; }
    public void setSucursal(int sucursal) { this.sucursal = sucursal; }

    public int getNumerodefactura() { return numerodefactura; }
    public void setNumerodefactura(int numerodefactura) { this.numerodefactura = numerodefactura; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public TipoOperacion getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(TipoOperacion tipoOperacion) { this.tipoOperacion = tipoOperacion; }

    // Método abstracto para calcular el total, cada subclase lo implementa
    public abstract double calculototal();

    // Método abstracto para cargar datos desde Scanner
    public abstract void cargaDatos(Scanner scanner);

    @Override
    public String toString() {
        return "\nFactura " + tipo +
                "\nCUIT: " + cuit +
                "\nOperación: " + tipoOperacion +
                "\nSucursal: " + sucursal +
                "\nN° Factura: " + numerodefactura +
                "\nFecha: " + fecha +
                "\nNo Gravado: $" + String.format("%.2f", nogrado) +
                "\nTotal: $" + String.format("%.2f", total);
    }
}
