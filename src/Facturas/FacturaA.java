package Facturas;

import Facturas.Enums.Tipodecomprovante;

import java.time.LocalDate;
import java.util.Scanner;

public  class  FacturaA extends Factura {
     private double neto21;
     private double neto105;
     private double PercepcionIVA;
     private double PercepcionIB;
     private double OtrosImpuestos;
     private double IVA21;
     private double IVA105;

    public FacturaA(double nogrado, String cuit, Tipodecomprovante tipo, int sucursal, int numerodefactura, LocalDate fecha, double neto21, double neto105, double percepcionIVA, double percepcionIB, double otrosImpuestos) {
        super(nogrado, cuit, tipo, sucursal, numerodefactura, fecha);
        this.neto21 = neto21;
        this.neto105 = neto105;
        PercepcionIVA = percepcionIVA;
        PercepcionIB = percepcionIB;
        OtrosImpuestos = otrosImpuestos;
        this.IVA21 = calculodeiva21();
        this.IVA105 = calculoiva105();
        setTotal(calculototal());
    }
    public double getNeto21() {
        return neto21;
    }

    public void setNeto21(double neto21) {
        this.neto21 = neto21;
    }

    public double getNeto105() {
        return neto105;
    }

    public void setNeto105(double neto105) {
        this.neto105 = neto105;
    }

    public double getPercepcionIVA() {
        return PercepcionIVA;
    }

    public void setPercepcionIVA(double percepcionIVA) {
        PercepcionIVA = percepcionIVA;
    }

    public double getPercepcionIB() {
        return PercepcionIB;
    }

    public void setPercepcionIB(double percepcionIB) {
        PercepcionIB = percepcionIB;
    }

    public double getOtrosImpuestos() {
        return OtrosImpuestos;
    }

    public void setOtrosImpuestos(double otrosImpuestos) {
        OtrosImpuestos = otrosImpuestos;
    }

    public double getIVA21() {
        return IVA21;
    }

    public void setIVA21(double IVA21) {
        this.IVA21 = IVA21;
    }

    public double getIVA105() {
        return IVA105;
    }

    public void setIVA105(double IVA105) {
        this.IVA105 = IVA105;
    }
    public double calculodeiva21(){
        return getNeto21()*0.21 ;
    }
    public double calculoiva105(){
        return getNeto105()*0.105;
    }



    @Override
    public double calculototal() {
        double total = getNogrado() + getNeto21() + getNeto105()
                + getPercepcionIVA() + getPercepcionIB() + getOtrosImpuestos() + getIVA21() + getIVA105();
        return total;
    }

    @Override
    public void cargaDatos(Scanner scanner) {

        System.out.print("Ingrese el no gravado: ");
        setNogrado(scanner.nextDouble());
        System.out.print("Ingrese el neto 21%: ");
        neto21 = scanner.nextDouble();
        System.out.print("Ingrese el neto 10.5%: ");
        neto105 = scanner.nextDouble();
        System.out.print("Ingrese la percepción de IVA: ");
        PercepcionIVA = scanner.nextDouble();
        System.out.print("Ingrese la percepción de IIBB: ");
        PercepcionIB = scanner.nextDouble();
        System.out.print("Ingrese otros impuestos: ");
        OtrosImpuestos = scanner.nextDouble();

    }

    @Override
    public String toString() {
        return "FacturaAYBNormal{" + super.toString() +" Total: "+ getTotal() +
                " neto21=" + neto21 +
                ", neto105=" + neto105 +
                ", PercepcionIVA=" + PercepcionIVA +
                ", PercepcionIB=" + PercepcionIB +
                ", OtrosImpuestos=" + OtrosImpuestos +
                ", IVA21=" + IVA21 +
                ", IVA105=" + IVA105 +
                '}';
    }
}
