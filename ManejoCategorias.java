package com.mycompany.mavenproject1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManejoCategorias{
    private List<Categoria> categorias;

    public ManejoCategorias(){
        cargarCategorias();
    }
    
    public List<Categoria> getCategorias(){
        return categorias;
    }
    
    public void agregarCategoria(Categoria c){
        categorias.add(c);
        guardarCategorias();
    }

    public void eliminarCategoria(Categoria c){
        categorias.remove(c);
        guardarCategorias();
    }

    public void modificarCategoria(int index, Categoria nuevaCategoria){
        categorias.set(index, nuevaCategoria);
        guardarCategorias();
    }
    
    public Categoria buscarCategoria(String nombre){
        for (Categoria a : categorias) {
            if (a.getNombre().equals(nombre)){
                return a;
            }
        }
        return null;
    }
    
    private void guardarCategorias(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("categorias.obj"))){
            out.writeObject(categorias);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarCategorias() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("categorias.obj"))){
            categorias = (List<Categoria>) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            categorias = new ArrayList<>();
        }
    }
}
