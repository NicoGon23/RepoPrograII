package Gestor;

import Facturas.*;
import Facturas.Enums.Tipodecomprovante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class Gestor {
    List<Factura> Lista = new ArrayList<>();

   public void Cargadefacturas(Scanner scanner) {
       System.out.print("Ingrese tipo de factura (1=A, 2=B, 3=C): ");
       int tipo = scanner.nextInt();

       System.out.print("Ingrese el CUIT: ");
       String cuit = scanner.next();

       System.out.print("Ingrese número de sucursal: ");
       int sucursal = scanner.nextInt();

       System.out.print("Ingrese número de factura: ");
       int numFactura = scanner.nextInt();

       LocalDate fecha = LocalDate.now();

       Factura factura = EmisionFact.crearFactura(tipo,cuit,sucursal,numFactura,fecha);;

       if (factura != null) {
           factura.cargaDatos(scanner);
           Lista.add(factura);
           System.out.println("Factura " + factura.getTipo() + " agregada correctamente.");
       }


   }
   public void verarreglo(){
       for (Factura i : Lista){
           if (i instanceof FacturaA){
               FacturaA p1 = (FacturaA) i;
               System.out.println(p1.toString());
           }
           if (i instanceof FacturaB){
               FacturaB p1 = (FacturaB) i;
               System.out.println(p1.toString());
           }if (i instanceof FacturaC){
               FacturaC p1 = (FacturaC) i;
               System.out.println(p1.toString());
           }
       }
   }
}
