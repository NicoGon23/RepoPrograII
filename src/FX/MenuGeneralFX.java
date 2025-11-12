package FX;

import Entidades.EmpresaCliente;
import Facturas.Factura;
import GestorFX.GestorFX;
import Manejodearchivos.JSONUtilesEmpresa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuGeneralFX {

    public static void mostrar(Stage stage, EmpresaCliente empresa, Runnable volverAlMenuPrincipal) {
        GestorFX gestorFX = new GestorFX();

        Label titulo = new Label("Menú de Facturación - " + empresa.getNombredefantasia());
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnCargarCompra = new Button("Cargar factura de compra");
        Button btnVerCompras = new Button("Ver facturas de compra");
        Button btnCargarVenta = new Button("Cargar factura de venta");
        Button btnVerVentas = new Button("Ver facturas de venta");
        Button btnVolver = new Button("Volver al menú principal");

        VBox root = new VBox(15, titulo, btnCargarCompra, btnVerCompras, btnCargarVenta, btnVerVentas, btnVolver);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        Scene scene = new Scene(root, 450, 350);
        stage.setScene(scene);
        stage.setTitle("Gestión de Facturas");
        stage.show();

        // 🔹 Evento: cargar factura de compra
        btnCargarCompra.setOnAction(e -> {
            Factura nuevaFactura = gestorFX.cargarFacturaCompraFX(stage, empresa,
                    () -> mostrar(stage, empresa, volverAlMenuPrincipal)
            );

            if (nuevaFactura != null) {
                empresa.getListafacturadecompras().add(nuevaFactura);
                mostrarAlerta(" Factura agregada correctamente a las compras de " + empresa.getNombredefantasia());
            }
        });

        // 🔹 Evento : carga de factura de venta
        btnCargarVenta.setOnAction(e -> {
            Factura nuevaFactura = gestorFX.cargarFacturaVentaFX(stage, empresa,
                    () -> mostrar(stage, empresa, volverAlMenuPrincipal)
            );

            if (nuevaFactura != null) {
                empresa.getListafacturadeventas().add(nuevaFactura);
                mostrarAlerta(" Factura agregada correctamente a las ventas de " + empresa.getNombredefantasia());
            }
        });


        // 🔹 Ver facturas de venta
        btnVerVentas.setOnAction(e ->
                gestorFX.verFacturasFX(stage, empresa.getListafacturadeventas(),
                        () -> mostrar(stage, empresa, volverAlMenuPrincipal))
        );
        // 🔹 Ver facturas de venta
        btnVerCompras.setOnAction(e ->
                gestorFX.verFacturasFX(stage, empresa.getListafacturadecompras(),
                        () -> mostrar(stage, empresa, volverAlMenuPrincipal))
        );
        JSONUtilesEmpresa JSONUtilesEmpresa = new JSONUtilesEmpresa();
        // 🔹 Volver al menú principal y guardar en JSON
        btnVolver.setOnAction(e -> {
            try {
                // 💾 Guardar la empresa actual con sus facturas en el JSON
                JSONUtilesEmpresa.escribir(empresa);

                mostrarAlerta(" Cambios guardados correctamente en el archivo JSON.");

                // 👉 Volver al menú principal
                volverAlMenuPrincipal.run();

            } catch (Exception ex) {
                ex.printStackTrace();
                mostrarAlerta(" Error al guardar los datos en el JSON.");
            }
        });
    }

    // 🔔 Método auxiliar para mostrar mensajes
    private static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}