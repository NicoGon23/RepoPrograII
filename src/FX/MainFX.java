package FX;

import Entidades.EmpresaCliente;
import Manejodearchivos.JSONUtilesEmpresa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONException;

import java.util.function.Consumer;

public class MainFX extends Application {

    private EmpresaCliente empresaActual = null;

    @Override
    public void start(Stage stage) {
        mostrarMenuPrincipal(stage);
    }


    public void mostrarMenuPrincipal(Stage stage) {
        stage.setTitle("Gestor de Empresa");

        // Etiqueta principal
        Label lblTitulo = new Label("Seleccione una opción:");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Botones del menú
        Button btnCargarEmpresa = new Button("Cargar / Cambiar Empresa");
        Button btnCargarFacturas = new Button("Cargar Facturas");
        Button btnSalir = new Button("Cerrar aplicación");

        // Diseño visual
        VBox layout = new VBox(15, lblTitulo, btnCargarEmpresa, btnCargarFacturas, btnSalir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Escena principal
        Scene scene = new Scene(layout, 400, 250);
        stage.setScene(scene);
        stage.show();

        // Botón: Cargar / Cambiar Empresa
        btnCargarEmpresa.setOnAction(e -> {
            // callback que recibirá la empresa seleccionada desde LoingInicialFX
            Consumer<EmpresaCliente> callback = (empresaSeleccionada) -> {
                if (empresaSeleccionada != null) {
                    empresaActual = empresaSeleccionada;
                    mostrarAlerta("Empresa cargada: " + empresaActual.getNombredefantasia());
                }
                // Al volver nos aseguramos de mostrar el menú principal
                mostrarMenuPrincipal(stage);
            };

            // Llamada CORRECTA: pasamos stage, callback y la acción para "volver"
            LoingInicialFX.mostrarMenu(stage, callback, () -> mostrarMenuPrincipal(stage));
        });
        JSONUtilesEmpresa JSONUtilesEmpresa = new JSONUtilesEmpresa();
        //  Botón: Cargar Facturas
        btnCargarFacturas.setOnAction(e -> {
            try {
                if (empresaActual == null) {
                    mostrarAlerta("Debe cargar una empresa primero ⚠️");
                } else {
                    MenuGeneralFX.mostrar(stage, empresaActual, () -> mostrarMenuPrincipal(stage));
                    JSONUtilesEmpresa.escribir(empresaActual);
                    mostrarAlerta("Facturas cargadas correctamente ✅");
                }
            } catch (JSONException ex) {
                mostrarAlerta("Error al guardar la empresa: " + ex.getMessage());
            } catch (Exception ex) {
                mostrarAlerta("Error al cargar facturas: " + ex.getMessage());
            }
        });

        //  Botón: Salir
        btnSalir.setOnAction(e -> {
            mostrarAlerta("El programa se cerrará.");
            stage.close();
        });
    }


    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}