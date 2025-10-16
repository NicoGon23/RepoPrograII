import Gestor.Gestor;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Gestor gestor = new Gestor();
    int opc;
    do{
        System.out.println("Ingrese lo que Desea");
        System.out.println("0-Salir");
        System.out.println("1-Cargar factura");
        System.out.println("2-Ver facturas cargadas");
        opc = scanner.nextInt();
        switch (opc){
            case 0:{
                System.out.println("El programa se cerrara");
            }break;
            case 1:{gestor.Cargadefacturas(scanner);}break;
            case 2:{gestor.verarreglo();}break;
            default :{
                System.out.println("Error");
            }break;
        }
    }while (opc!=0);
 /// Cosas a corregir:
 ///el primer dato que se muestra es el nogrado que es el No Gravado es mover el dato en todos los tostring
 /// No se muestra correctamente el Cuit/Cuil , calculo que es por algun dato mal pasado al constructor
 ///Añadir comentarios de como funciona el codigo , comentario de "Nico" me quedan 10 min para irme a la facu por eso no los agrege , lo hago despues.
 ///Subirlo a git , lo mismo del anterior , lo hago en la facu ..... espero
 ///Mover la factura C a facturas no se porque esta en los Enums , se me habra escapado

    }
}