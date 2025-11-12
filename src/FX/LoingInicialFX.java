
package FX;

import Entidades.EmpresaCliente;
import Entidades.Enums.TipoCondiciondeIva;
import Manejodearchivos.JSONUtilesEmpresa;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONException;

import java.util.function.Consumer;

public class LoingInicialFX extends Application {

    @Override
    public void start(Stage stage) {
        mostrarMenu(stage, empresa -> {
            System.out.println("Empresa cargada: " + empresa.getNombredefantasia());
        }, () -> {
            System.out.println("Volver al menú principal");
        });
    }

    // 🔹 Menú principal de login
    public static void mostrarMenu(Stage stage, Consumer<EmpresaCliente> onEmpresaCargada, Runnable volverAlMenuPrincipal) {
        Label titulo = new Label("Menú Inicial - Empresa");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnCargarExistente = new Button("Ingresar con empresa existente");
        Button btnCrearNueva = new Button("Crear nueva empresa");
        Button btnVolver = new Button("Volver al menú principal");

        VBox root = new VBox(15, titulo, btnCargarExistente, btnCrearNueva, btnVolver);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Gestión de Empresa");
        stage.show();

        JSONUtilesEmpresa jsonUtil = new JSONUtilesEmpresa(); //  instancia necesaria

        // 🔹 Cargar empresa existente
        btnCargarExistente.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Cargar Empresa");
            dialog.setHeaderText("Ingrese el CUIT o el nombre de fantasía:");
            dialog.setContentText("Dato:");

            dialog.showAndWait().ifPresent(nombreCuit -> {
                Task<EmpresaCliente> tarea = new Task<>() {
                    @Override
                    protected EmpresaCliente call() throws Exception {
                        return jsonUtil.leerPorDato(nombreCuit);
                    }
                };

                tarea.setOnSucceeded(ev -> {
                    EmpresaCliente empresa = tarea.getValue();
                    if (empresa != null) {
                        mostrarMensaje("Éxito", "Empresa cargada correctamente:\n" + empresa.getNombredefantasia());
                        onEmpresaCargada.accept(empresa);
                        volverAlMenuPrincipal.run();
                    } else {
                        mostrarMensaje("Error", "No se encontró una empresa con ese dato.");
                    }
                });

                tarea.setOnFailed(ev -> mostrarMensaje("Error", "Hubo un problema al leer el archivo JSON."));
                Thread hilo = new Thread(tarea);
                hilo.setDaemon(true);
                hilo.start();
            });
        });

        // 🔹 Crear nueva empresa
        btnCrearNueva.setOnAction(e -> mostrarFormularioCreacion(stage, onEmpresaCargada, volverAlMenuPrincipal));

        // 🔹 Volver
        btnVolver.setOnAction(e -> volverAlMenuPrincipal.run());
    }

    // 🔹 Formulario para crear empresa
    private static void mostrarFormularioCreacion(Stage stage, Consumer<EmpresaCliente> onEmpresaCargada, Runnable volverAlMenuPrincipal) {
        Label lblAlias = new Label("Alias:");
        TextField alias = new TextField();

        Label lblRazon = new Label("Razón social:");
        TextField razon = new TextField();

        Label lblFantasia = new Label("Nombre de fantasía:");
        TextField fantasia = new TextField();

        Label lblCuit = new Label("CUIT:");
        TextField cuit = new TextField();

        Label lblIva = new Label("Condición de IVA:");
        ComboBox<TipoCondiciondeIva> iva = new ComboBox<>();
        iva.getItems().addAll(TipoCondiciondeIva.values());

        Label lblIB = new Label("Ingreso Bruto:");
        TextField ib = new TextField();

        Label lblActividad = new Label("Actividad:");
        TextField actividad = new TextField();

        Label lblDireccion = new Label("Dirección física:");
        TextField direccion = new TextField();

        Label lblLocalidad = new Label("Localidad:");
        TextField localidad = new TextField();

        Label lblProvincia = new Label("Provincia:");
        TextField provincia = new TextField();

        Label lblTelefono = new Label("Teléfono (opcional):");
        TextField telefono = new TextField();

        Label lblCorreo = new Label("Correo electrónico (opcional):");
        TextField correo = new TextField();

        Label lblWeb = new Label("Página web (opcional):");
        TextField web = new TextField();

        Button guardar = new Button("Guardar empresa");
        Button volver = new Button("Volver al menú");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(lblAlias, 0, 0); grid.add(alias, 1, 0);
        grid.add(lblRazon, 0, 1); grid.add(razon, 1, 1);
        grid.add(lblFantasia, 0, 2); grid.add(fantasia, 1, 2);
        grid.add(lblCuit, 0, 3); grid.add(cuit, 1, 3);
        grid.add(lblIva, 0, 4); grid.add(iva, 1, 4);
        grid.add(lblIB, 0, 5); grid.add(ib, 1, 5);
        grid.add(lblActividad, 0, 6); grid.add(actividad, 1, 6);
        grid.add(lblDireccion, 0, 7); grid.add(direccion, 1, 7);
        grid.add(lblLocalidad, 0, 8); grid.add(localidad, 1, 8);
        grid.add(lblProvincia, 0, 9); grid.add(provincia, 1, 9);
        grid.add(lblTelefono, 0, 10); grid.add(telefono, 1, 10);
        grid.add(lblCorreo, 0, 11); grid.add(correo, 1, 11);
        grid.add(lblWeb, 0, 12); grid.add(web, 1, 12);

        HBox botones = new HBox(10, guardar, volver);
        botones.setAlignment(Pos.CENTER);
        grid.add(botones, 0, 13, 2, 1);

        Scene scene = new Scene(grid, 600, 650);
        stage.setScene(scene);
        stage.setTitle("Crear Nueva Empresa");

        JSONUtilesEmpresa jsonUtil = new JSONUtilesEmpresa(); //  instancia

        guardar.setOnAction(ev -> {
            try {
                TipoCondiciondeIva ivaSeleccionado = iva.getValue();
                if (ivaSeleccionado == null) {
                    mostrarMensaje("Error", "Seleccione una condición de IVA.");
                    return;
                }

                EmpresaCliente empresa = new EmpresaCliente(
                        alias.getText(),
                        razon.getText(),
                        fantasia.getText(),
                        cuit.getText(),
                        ivaSeleccionado,
                        ib.getText(),
                        actividad.getText(),
                        direccion.getText(),
                        localidad.getText(),
                        provincia.getText(),
                        telefono.getText().isEmpty() ? "" : telefono.getText(),
                        correo.getText().isEmpty() ? "" : correo.getText(),
                        web.getText().isEmpty() ? "" : web.getText(),
                        null, // listas de compra vacías
                        null
                );

                // Guardar empresa (en background)
                Task<Void> tareaGuardar = new Task<>() {
                    @Override
                    protected Void call() throws JSONException {
                        jsonUtil.escribir(empresa);
                        return null;
                    }
                };

                tareaGuardar.setOnSucceeded(ev2 -> {
                    mostrarMensaje("Éxito", "Empresa creada y guardada correctamente.");
                    onEmpresaCargada.accept(empresa);
                    volverAlMenuPrincipal.run();
                });

                tareaGuardar.setOnFailed(ev2 -> {
                    mostrarMensaje("Error", "No se pudo guardar la empresa:\n" +
                            tareaGuardar.getException().getMessage());
                });

                Thread hilo = new Thread(tareaGuardar);
                hilo.setDaemon(true);
                hilo.start();

            } catch (Exception ex) {
                ex.printStackTrace();
                mostrarMensaje("Error", "Ocurrió un problema al crear la empresa.");
            }
        });

        volver.setOnAction(ev -> mostrarMenu(stage, onEmpresaCargada, volverAlMenuPrincipal));
    }

    // 🔹 Mostrar alertas seguras desde cualquier hilo
    private static void mostrarMensaje(String titulo, String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}