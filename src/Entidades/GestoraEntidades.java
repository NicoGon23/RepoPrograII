package Entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class GestoraEntidades {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Proveedor> proveedores = new ArrayList<>();


    public Proveedor crearProveedor(Scanner scanner, String cuit) {
        System.out.print("Nombre: "); String nombre = scanner.nextLine();
        System.out.print("Apellido: "); String apellido = scanner.nextLine();
        System.out.print("Dirección: "); String direccion = scanner.nextLine();
        System.out.print("Teléfono: "); String telefono = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Rubro: "); String rubro = scanner.nextLine();
        return new Proveedor(nombre, apellido, cuit, direccion, telefono, email, rubro);
    }

    public Cliente crearCliente(Scanner scanner, String cuit) {
        System.out.print("Nombre: "); String nombre = scanner.nextLine();
        System.out.print("Apellido: "); String apellido = scanner.nextLine();
        System.out.print("Dirección: "); String direccion = scanner.nextLine();
        System.out.print("Teléfono: "); String telefono = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Tipo de cliente (ej: minorista/mayorista): "); String tipoCliente = scanner.nextLine();
        return new Cliente(nombre, apellido, cuit, direccion, telefono, email, tipoCliente);
    }

    public void agregarCliente(Cliente c) {
        if (c != null) {
            clientes.add(c);
            System.out.println("Cliente agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar el cliente.");
        }
    }

    public void agregarProveedor(Proveedor p) {
        if (p != null) {
            proveedores.add(p);
            System.out.println("Proveedor agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar el proveedor.");
        }
    }


    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes cargados.");
            return;
        }
        System.out.println("----- LISTADO DE CLIENTES -----");
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores cargados.");
            return;
        }
        System.out.println("----- LISTADO DE PROVEEDORES -----");
        for (Proveedor p : proveedores) {
            System.out.println(p);
        }
    }


    public Entidad buscarPorCuit(String cuit) {
        for (Cliente c : clientes)
            if (c.getCuit().equals(cuit))
                return c;
        for (Proveedor p : proveedores)
            if (p.getCuit().equals(cuit))
                return p;
        return null;
    }


}
