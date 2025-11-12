import javafx.application.Application;

import FX.MainFX;
public class Main {
    public static void main(String[] args) {
        Application.launch(MainFX.class, args);
    }
}

    /// Explicacion de como funciona el main se debe cargar primero una empresa llamando al case 1 , apartir de ahi se da la opcion de 1 entrar con una empresa
    /// ya existente lo cual es obligatorio o 2 crear una empresa nueva cargar sus datos y elegir la opcion 1 , ambas opciones llaman a Log in el cual tiene las
    /// funciones para crear o cargar la empresa. Una vez la empresa esta cargada las funciones de log in devuelve el objeto de esa empresa y apartir de ahi
    /// podemos ir a la opcion dos "Carga facturas" , la cual nos lleva al menu principal y cuando salimos de el guarda los datos en el archivo

    /// Cosas a corregir/añadir:
    /// Tenemos que hacer algo con el provedor o cliente , ya que aunque lo guardamos en un arreglo esa informacion no se usa o se pierde al cerrar la aplicacion
    /// lo que se me ocurre es en la factura añadir una atributo el cual sea el nombre del provedor.

    /// En log in abria que añadir una opcion para modificar los datos de la empresa.

    ///Añadir validaciones "Prueben el codigo y traten de romperlo, ubiquen porque sucede y añadan o una excepcion o una validacion" , principalmente en la carga
    /// de los datos de la factura ocurre mucho.
    /// De lo que pide el tp solo falta la interfas , que no se donde meterla




