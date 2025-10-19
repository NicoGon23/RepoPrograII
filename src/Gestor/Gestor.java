package Gestor;

import Facturas.FacturaC;
import Facturas.Enums.Tipodecomprovante;
import Facturas.Factura;
import Facturas.FacturaA;
import Facturas.FacturaB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class Gestor {
    List<Factura> Lista = new ArrayList<>();

   public void Cargadefacturas(Scanner scanner) {
       int opc;
       do {
           System.out.println("Ingrese la opcion");
           System.out.println("0-Salir");
           System.out.println("1-Cargadefacura");
           opc = scanner.nextInt();
           switch (opc){
               case 0:{
                   System.out.println("El programa se cerrara");
               }break;
               case 1:{
                   System.out.println("Ingrese el CUIL");
                   String Cuil = scanner.nextLine();
                   scanner.nextLine();
                   System.out.println("Ingrese el tipo de factura");
                   System.out.println("1-A");
                   System.out.println("2-B");
                   System.out.println("3-C");
                   int tipo = scanner.nextInt();
                   System.out.println("Ingrese el numero de sucursal");
                   int sucursal = scanner.nextInt();
                   System.out.println("Ingrese el numero de factura");
                   int numfact = scanner.nextInt();
                   scanner.nextLine();
                   System.out.print("Ingrese una fecha (formato AAAA-MM-DD): ");
                   String entrada = scanner.nextLine();
                   LocalDate fecha = LocalDate.parse(entrada);
                   switch (tipo){
                       case 1:{
                           System.out.println("Ingrese el no gravado");
                           double nogravado = scanner.nextDouble();
                           System.out.println("Ingrese el neto 21%");
                           double neto21 = scanner.nextDouble();
                           System.out.println("Ingrese el neto10.5 %");
                           double neto105 = scanner.nextDouble();
                           System.out.println("Ingrese la percepcion de IVA 21%");
                           double perIVA21 = scanner.nextDouble();
                           System.out.println("Ingrese la percepcion de IVA 10.5%");
                           double perIVA105 = scanner.nextDouble();
                           System.out.println("Ingrese otros impuestos");
                           double otrosimpuestos = scanner.nextDouble();
                           FacturaA p1 = new FacturaA(nogravado, Cuil , Tipodecomprovante.A , sucursal , numfact , fecha ,   neto21 , neto105 , perIVA21 , perIVA105 , otrosimpuestos);
                           Lista.add(p1);
                           System.out.println("Factura A agregada correctamente.");
                       }break;
                       case 2:{
                           System.out.println("Ingrese el no gravado");
                           double nogravado = scanner.nextDouble();
                           System.out.println("Ingrese el neto 21%");
                           double neto21 = scanner.nextDouble();
                           System.out.println("Ingrese el neto10.5 %");
                           double neto105 = scanner.nextDouble();
                           System.out.println("Ingrese la percepcion de IVA 21%");
                           double perIVA21 = scanner.nextDouble();
                           System.out.println("Ingrese la percepcion de IVA 10.5%");
                           double perIVA105 = scanner.nextDouble();
                           System.out.println("Ingrese otros impuestos");
                           double otrosimpuestos = scanner.nextDouble();
                           FacturaB p1 = new FacturaB(nogravado, Cuil , Tipodecomprovante.B , sucursal , numfact , fecha ,   neto21 , neto105 , perIVA21 , perIVA105 , otrosimpuestos);
                           Lista.add(p1);
                           System.out.println("Factura B agregada correctamente.");
                       }break;
                       case 3:{
                           System.out.println("Ingrese el no gravado");
                           double nogravado = scanner.nextDouble();
                           FacturaC p1 = new FacturaC(nogravado, Cuil , Tipodecomprovante.C , sucursal , numfact , fecha );
                           Lista.add(p1);
                           System.out.println("Factura C agregada correctamente.");
                       }break;
                       default:{
                           System.out.println("Factura incorrecta");
                       }break;
                   }

               }break;
               default:{
                   System.out.println("Error");
               }break;
           }

       } while (opc != 0);
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
