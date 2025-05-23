package com.mycompany.mavenproject1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListaClientes {
    private List<Cliente> clientes;

    public ListaClientes(){
        cargarClientes();
    }
    
    public List<Cliente> getClientes(){
        return clientes;
    }
    
    public void agregarCliente(Cliente c){
        clientes.add(c);
        guardarClientes();
    }

    public void eliminarCliente(Cliente c){
        clientes.remove(c);
        guardarClientes();
    }

    public void modificarCliente(int index, Cliente nuevoCliente){
        clientes.set(index, nuevoCliente);
        guardarClientes();
    }
    
    public Cliente buscarCliente(int num){
        for (Cliente a : clientes) {
            if (a.getNumero() == num){
                return a;
            }
        }
        return null;
    }
    
    private void guardarClientes(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clientes.obj"))){
            out.writeObject(clientes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarClientes() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("clientes.obj"))){
            clientes = (List<Cliente>) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            clientes = new ArrayList<>();
        }
    }
}
