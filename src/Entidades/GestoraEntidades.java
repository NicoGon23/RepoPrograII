package Entidades;

import java.util.ArrayList;

public class GestoraEntidades {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Proveedor> proveedores = new ArrayList<>();


    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }
    public void agregarProveedor(Proveedor p) {
        proveedores.add(p);
    }


    public void listarClientes() {
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public void listarProveedores() {
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
