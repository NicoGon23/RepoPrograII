package FX;

import Entidades.Cliente;
import Entidades.Proveedor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrearEntidadFX {

    //  Crear un nuevo Cliente
    public static Cliente mostrarFormularioCliente(Stage owner, String cuitPreCargado) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Nuevo Cliente");

        TextField txtNombre = new TextField();
        TextField txtApellido = new TextField();
        TextField txtDireccion = new TextField();
        TextField txtTelefono = new TextField();
        TextField txtEmail = new TextField();
        TextField txtTipo = new TextField();
        TextField txtCuit = new TextField(cuitPreCargado);
        txtCuit.setEditable(false);

        Button btnGuardar = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.addRow(0, new Label("Nombre:"), txtNombre);
        grid.addRow(1, new Label("Apellido:"), txtApellido);
        grid.addRow(2, new Label("Dirección:"), txtDireccion);
        grid.addRow(3, new Label("Teléfono:"), txtTelefono);
        grid.addRow(4, new Label("Email:"), txtEmail);
        grid.addRow(5, new Label("Tipo Cliente:"), txtTipo);
        grid.addRow(6, new Label("CUIT:"), txtCuit);

        VBox layout = new VBox(10, grid, new VBox(10, btnGuardar, btnCancelar));
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 350, 400);
        dialog.setScene(scene);

        final Cliente[] clienteCreado = {null};

        btnGuardar.setOnAction(e -> {
            clienteCreado[0] = new Cliente(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtCuit.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    txtTipo.getText()
            );
            dialog.close();
        });

        btnCancelar.setOnAction(e -> {
            dialog.close();
        });

        dialog.showAndWait();
        return clienteCreado[0];
    }

    //  Crear un nuevo Proveedor
    public static Proveedor mostrarFormularioProveedor(Stage owner, String cuitPreCargado) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Nuevo Proveedor");

        TextField txtNombre = new TextField();
        TextField txtApellido = new TextField();
        TextField txtDireccion = new TextField();
        TextField txtTelefono = new TextField();
        TextField txtEmail = new TextField();
        TextField txtRubro = new TextField();
        TextField txtCuit = new TextField(cuitPreCargado);
        txtCuit.setEditable(false);

        Button btnGuardar = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.addRow(0, new Label("Nombre:"), txtNombre);
        grid.addRow(1, new Label("Apellido:"), txtApellido);
        grid.addRow(2, new Label("Dirección:"), txtDireccion);
        grid.addRow(3, new Label("Teléfono:"), txtTelefono);
        grid.addRow(4, new Label("Email:"), txtEmail);
        grid.addRow(5, new Label("Rubro:"), txtRubro);
        grid.addRow(6, new Label("CUIT:"), txtCuit);

        VBox layout = new VBox(10, grid, new VBox(10, btnGuardar, btnCancelar));
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 350, 400);
        dialog.setScene(scene);

        final Proveedor[] proveedorCreado = {null};

        btnGuardar.setOnAction(e -> {
            proveedorCreado[0] = new Proveedor(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtCuit.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    txtRubro.getText()
            );
            dialog.close();
        });

        btnCancelar.setOnAction(e -> dialog.close());

        dialog.showAndWait();
        return proveedorCreado[0];
    }
}