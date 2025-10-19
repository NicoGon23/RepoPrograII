package Gestor;

import Facturas.*;

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

       Factura factura = CargaFact.crearFactura(tipo,cuit,sucursal,numFactura,fecha);;

       if (factura != null) {
           factura.cargaDatos(scanner);
           Lista.add(factura);
           System.out.println("Factura " + factura.getTipo() + " agregada correctamente.");
       }


   }
   public void verFacturasCargadas(){

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
