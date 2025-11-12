package GestorFX;

import Entidades.EmpresaCliente;
import Entidades.Entidad;
import Entidades.GestoraEntidades;
import Entidades.Proveedor;
import FX.CrearEntidadFX;
import Facturas.*;
import Facturas.Enums.Tipodecomprobante;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import Entidades.Cliente;
public class GestorFX {

    private GestoraEntidades gestoraEntidades = new GestoraEntidades();

    /**
     *  Carga una factura de compra mediante formulario FX y la retorna
     */
    public Factura cargarFacturaCompraFX(Stage stage, EmpresaCliente empresa, Runnable volverAlMenu) {
        Label lblTitulo = new Label("Carga de Factura de Compra");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ChoiceBox<String> tipoFactura = new ChoiceBox<>();
        tipoFactura.getItems().addAll("A", "B", "C");
        tipoFactura.setValue("A");

        TextField txtCuit = new TextField();
        txtCuit.setPromptText("CUIT del proveedor");

        TextField txtSucursal = new TextField();
        txtSucursal.setPromptText("Número de sucursal");

        TextField txtNumFactura = new TextField();
        txtNumFactura.setPromptText("Número de factura");

        DatePicker dpFecha = new DatePicker(LocalDate.now());

        TextField txtNoGravado = new TextField();
        txtNoGravado.setPromptText("No gravado");

        TextField txtIVA21 = new TextField();
        txtIVA21.setPromptText("IVA 21%");

        TextField txtIVA105 = new TextField();
        txtIVA105.setPromptText("IVA 10.5%");

        TextField txtPercepcionIVA = new TextField();
        txtPercepcionIVA.setPromptText("Percepción de IVA");

        TextField txtIngresosBrutos = new TextField();
        txtIngresosBrutos.setPromptText("Percepción de Ingresos Brutos");

        TextField txtOtrosImp = new TextField();
        txtOtrosImp.setPromptText("Otros impuestos");

        Button btnGuardar = new Button("Guardar Factura");
        Button btnVolver = new Button("Volver");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(new Label("Tipo de factura:"), 0, 1);
        grid.add(tipoFactura, 1, 1);
        grid.add(new Label("CUIT:"), 0, 2);
        grid.add(txtCuit, 1, 2);
        grid.add(new Label("Sucursal:"), 0, 3);
        grid.add(txtSucursal, 1, 3);
        grid.add(new Label("Número de Factura:"), 0, 4);
        grid.add(txtNumFactura, 1, 4);
        grid.add(new Label("Fecha:"), 0, 5);
        grid.add(dpFecha, 1, 5);
        grid.add(new Label("No Gravado:"), 0, 6);
        grid.add(txtNoGravado, 1, 6);
        grid.add(new Label("IVA 21%:"), 0, 7);
        grid.add(txtIVA21, 1, 7);
        grid.add(new Label("IVA 10.5%:"), 0, 8);
        grid.add(txtIVA105, 1, 8);
        grid.add(new Label("Percepción IVA:"), 0, 9);
        grid.add(txtPercepcionIVA, 1, 9);
        grid.add(new Label("Ingresos Brutos:"), 0, 10);
        grid.add(txtIngresosBrutos, 1, 10);
        grid.add(new Label("Otros Impuestos:"), 0, 11);
        grid.add(txtOtrosImp, 1, 11);
        grid.add(btnGuardar, 0, 12);
        grid.add(btnVolver, 1, 12);

        Scene scene = new Scene(grid, 450, 600);
        stage.setScene(scene);

        final Factura[] facturaResultado = {null};

        btnGuardar.setOnAction(e -> {
            try {
                String tipo = tipoFactura.getValue();
                String cuit = txtCuit.getText().trim();

                if (cuit.isEmpty()) {
                    mostrarMensaje("Debe ingresar el CUIT del proveedor.");
                    return;
                }

                //  usamos la gestora propia de la empresa
                GestoraEntidades gestora = empresa.getGestoraEntidades();
                Entidad personaEncontrada = gestoraEntidades.buscarPorCuit(cuit, Cliente.class);
                Proveedor proveedor;

                if (personaEncontrada != null && personaEncontrada instanceof Proveedor) {
                    proveedor = (Proveedor) personaEncontrada;
                } else {
                    // No existe → mostramos aviso y pedimos crear uno nuevo
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Proveedor no encontrado");
                    alert.setHeaderText("No se encontró un proveedor con ese CUIT.");
                    alert.setContentText("Se abrirá una ventana para crear un nuevo proveedor.");
                    alert.showAndWait();

                    proveedor = CrearEntidadFX.mostrarFormularioProveedor(stage, cuit);

                    if (proveedor == null) {
                        mostrarMensaje("Operación cancelada. No se cargó la factura.");
                        return;
                    }

                    gestora.agregarEntidad(proveedor);
                }

                int sucursal = Integer.parseInt(txtSucursal.getText());
                int numFactura = Integer.parseInt(txtNumFactura.getText());
                LocalDate fecha = dpFecha.getValue();

                double noGravado = Double.parseDouble(txtNoGravado.getText());
                double iva21 = Double.parseDouble(txtIVA21.getText());
                double iva105 = Double.parseDouble(txtIVA105.getText());
                double perIva = Double.parseDouble(txtPercepcionIVA.getText());
                double ingBrutos = Double.parseDouble(txtIngresosBrutos.getText());
                double otros = Double.parseDouble(txtOtrosImp.getText());

                Factura factura;
                switch (tipo) {
                    case "A" -> {
                        CargaFact<FacturaA> cargaA = new CargaFact<>(Tipodecomprobante.A);
                        factura = cargaA.crearFactura(noGravado, cuit, Tipodecomprobante.A, sucursal, numFactura, fecha, iva21, iva105, perIva, ingBrutos, otros);
                    }
                    case "B" -> {
                        CargaFact<FacturaB> cargaB = new CargaFact<>(Tipodecomprobante.B);
                        factura = cargaB.crearFactura(noGravado, cuit, Tipodecomprobante.B, sucursal, numFactura, fecha, iva21, iva105, perIva, ingBrutos, otros);
                    }
                    case "C" -> {
                        CargaFact<FacturaC> cargaC = new CargaFact<>(Tipodecomprobante.C);
                        factura = cargaC.crearFactura(noGravado, cuit, Tipodecomprobante.C, sucursal, numFactura, fecha, 0, 0, 0, 0, 0);
                    }
                    default -> {
                        mostrarMensaje("Tipo inválido");
                        return;
                    }
                }

                empresa.cargadecompra(factura);
                facturaResultado[0] = factura;
                mostrarMensaje("Factura cargada correctamente ✅");
                volverAlMenu.run();

            } catch (Exception ex) {
                mostrarMensaje("Error: " + ex.getMessage());
            }
        });

        btnVolver.setOnAction(e -> volverAlMenu.run());

        return facturaResultado[0];
    }

    /**
     * 🔹 Muestra las facturas cargadas (compra o venta)
     */
    public void verFacturasFX(Stage stage, ArrayList<Factura> lista, Runnable volver) {
        if (lista.isEmpty()) {
            mostrarMensaje("No hay facturas cargadas.");
            volver.run();
            return;
        }

        ListView<String> listaView = new ListView<>();
        int i = 1;
        for (Factura f : lista) {
            listaView.getItems().add(i++ + ") " + f.toString());
        }

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> volver.run());

        VBox layout = new VBox(15, new Label("Listado de Facturas"), listaView, btnVolver);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
    }

    private void mostrarMensaje(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public Factura cargarFacturaVentaFX(Stage stage, EmpresaCliente empresa, Runnable volverAlMenu) {
        Label lblTitulo = new Label("Carga de Factura de Venta");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ChoiceBox<String> tipoFactura = new ChoiceBox<>();
        tipoFactura.getItems().addAll("A", "B", "C");
        tipoFactura.setValue("A");

        TextField txtCuit = new TextField();
        txtCuit.setPromptText("CUIT del cliente o 1 si es consumidor final");

        TextField txtSucursal = new TextField();
        txtSucursal.setPromptText("Número de sucursal");

        TextField txtNumFactura = new TextField();
        txtNumFactura.setPromptText("Número de factura");

        DatePicker dpFecha = new DatePicker(LocalDate.now());

        TextField txtNoGravado = new TextField();
        txtNoGravado.setPromptText("No gravado");

        TextField txtIVA21 = new TextField();
        txtIVA21.setPromptText("IVA 21%");

        TextField txtIVA105 = new TextField();
        txtIVA105.setPromptText("IVA 10.5%");

        TextField txtPercepcionIVA = new TextField();
        txtPercepcionIVA.setPromptText("Percepción de IVA");

        TextField txtIngresosBrutos = new TextField();
        txtIngresosBrutos.setPromptText("Percepción de Ingresos Brutos");

        TextField txtOtrosImp = new TextField();
        txtOtrosImp.setPromptText("Otros impuestos");

        Button btnGuardar = new Button("Guardar Factura");
        Button btnVolver = new Button("Volver");

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(new Label("Tipo de factura:"), 0, 1);
        grid.add(tipoFactura, 1, 1);
        grid.add(new Label("CUIT:"), 0, 2);
        grid.add(txtCuit, 1, 2);
        grid.add(new Label("Sucursal:"), 0, 3);
        grid.add(txtSucursal, 1, 3);
        grid.add(new Label("Número de Factura:"), 0, 4);
        grid.add(txtNumFactura, 1, 4);
        grid.add(new Label("Fecha:"), 0, 5);
        grid.add(dpFecha, 1, 5);
        grid.add(new Label("No Gravado:"), 0, 6);
        grid.add(txtNoGravado, 1, 6);
        grid.add(new Label("IVA 21%:"), 0, 7);
        grid.add(txtIVA21, 1, 7);
        grid.add(new Label("IVA 10.5%:"), 0, 8);
        grid.add(txtIVA105, 1, 8);
        grid.add(new Label("Percepción IVA:"), 0, 9);
        grid.add(txtPercepcionIVA, 1, 9);
        grid.add(new Label("Ingresos Brutos:"), 0, 10);
        grid.add(txtIngresosBrutos, 1, 10);
        grid.add(new Label("Otros Impuestos:"), 0, 11);
        grid.add(txtOtrosImp, 1, 11);
        grid.add(btnGuardar, 0, 12);
        grid.add(btnVolver, 1, 12);

        Scene scene = new Scene(grid, 450, 600);
        stage.setScene(scene);

        final Factura[] facturaResultado = {null};

        btnGuardar.setOnAction(e -> {
            try {
                String tipo = tipoFactura.getValue();
                String cuit = txtCuit.getText().trim();

                if (cuit.isEmpty()) {
                    mostrarMensaje("Debe ingresar el CUIT del cliente.");
                    return;
                }

                // <-- USAR LA GESTORA DE LA EMPRESA (persistente) -->
                Entidad personaEncontrada = gestoraEntidades.buscarPorCuit(cuit, Proveedor.class);
                Cliente cliente;

                if (personaEncontrada != null && personaEncontrada instanceof Cliente) {
                    cliente = (Cliente) personaEncontrada;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cliente no encontrado");
                    alert.setHeaderText("No se encontró un cliente con ese CUIT.");
                    alert.setContentText("Se abrirá una ventana para crear un nuevo cliente.");
                    alert.showAndWait();

                    cliente = CrearEntidadFX.mostrarFormularioCliente(stage, cuit);

                    if (cliente == null) {
                        mostrarMensaje("Operación cancelada. No se cargó la factura.");
                        return;
                    }

                    // <-- agregar en la gestora de la empresa -->
                    empresa.getGestoraEntidades().agregarEntidad(cliente);
                }

                int sucursal = Integer.parseInt(txtSucursal.getText());
                int numFactura = Integer.parseInt(txtNumFactura.getText());
                LocalDate fecha = dpFecha.getValue();

                double noGravado = Double.parseDouble(txtNoGravado.getText());
                double iva21 = Double.parseDouble(txtIVA21.getText());
                double iva105 = Double.parseDouble(txtIVA105.getText());
                double perIva = Double.parseDouble(txtPercepcionIVA.getText());
                double ingBrutos = Double.parseDouble(txtIngresosBrutos.getText());
                double otros = Double.parseDouble(txtOtrosImp.getText());

                Factura factura;
                switch (tipo) {
                    case "A" -> {
                        CargaFact<FacturaA> cargaA = new CargaFact<>(Tipodecomprobante.A);
                        factura = cargaA.crearFactura(noGravado, cuit, Tipodecomprobante.A, sucursal, numFactura, fecha, iva21, iva105, perIva, ingBrutos, otros);
                    }
                    case "B" -> {
                        CargaFact<FacturaB> cargaB = new CargaFact<>(Tipodecomprobante.B);
                        factura = cargaB.crearFactura(noGravado, cuit, Tipodecomprobante.B, sucursal, numFactura, fecha, iva21, iva105, perIva, ingBrutos, otros);
                    }
                    case "C" -> {
                        CargaFact<FacturaC> cargaC = new CargaFact<>(Tipodecomprobante.C);
                        factura = cargaC.crearFactura(noGravado, cuit, Tipodecomprobante.C, sucursal, numFactura, fecha, 0, 0, 0, 0, 0);
                    }
                    default -> {
                        mostrarMensaje("Tipo inválido");
                        return;
                    }
                }

                empresa.cargadeventa(factura);
                facturaResultado[0] = factura;
                mostrarMensaje("Factura cargada correctamente ✅");
                volverAlMenu.run();

            } catch (Exception ex) {
                mostrarMensaje("Error: " + ex.getMessage());
            }
        });

        btnVolver.setOnAction(e -> volverAlMenu.run());

        return facturaResultado[0];
    }
}