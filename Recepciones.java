package com.mycompany.mavenproject1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Recepciones implements Serializable{
    private int referencia;
    private String proveedor;
    private LocalDate fechaIngreso;
    private List<Lote> productos;
    
    public Recepciones(int referencia, String proveedor, LocalDate fechaIngreso){
        this.referencia = referencia;
        this.proveedor = proveedor;
        this.fechaIngreso = fechaIngreso;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Lote producto){
        productos.add(producto);
    }

    public void eliminarProducto(Lote producto){
        productos.remove(producto);
    }

    public int getReferencia(){
        return referencia;
    }

    public String getProveedor(){
        return proveedor;
    }

    public LocalDate getFechaIngreso(){
        return fechaIngreso;
    }

    public List<Lote> getProductos(){
        return productos;
    }

    public void setReferencia(int referencia){
        this.referencia = referencia;
    }

    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }

    public void setFechaIngreso(LocalDate fechaIngreso){
        this.fechaIngreso = fechaIngreso;
    }

    public void setProductos(List<Lote> productos){
        this.productos = productos;
    }

    @Override
    public String toString(){
        return fechaIngreso + " - " + referencia + " - " + proveedor;
    }
    
    //archivos
    
    public static List<Recepciones> cargarLista() {
        File f = new File("recepciones.dat");
        if (!f.exists()){
            return new ArrayList<>();
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){
            return (List<Recepciones>) in.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarLista(List<Recepciones> lista){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("recepciones.dat"))){
            out.writeObject(lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void agregarAdmision(Recepciones nueva){
        List<Recepciones> lista = cargarLista();
        lista.add(nueva);
        guardarLista(lista);
    }

    public static void eliminarAdmision(Recepciones paraEliminar){
        List<Recepciones> lista = cargarLista();
        lista.removeIf(a -> a.getReferencia()==(paraEliminar.getReferencia()));
        guardarLista(lista);
    }
    
    public static Recepciones buscarPorReferencia(int refBuscada) {
        List<Recepciones> lista = cargarLista();
        for (Recepciones r : lista) {
            if (r.getReferencia() == refBuscada) {
                return r;
            }
        }
        return null;
    }
    
    public static boolean referenciaExiste(int referencia) {
        List<Recepciones> lista = cargarLista();
        for (Recepciones r : lista) {
            if (r.getReferencia()==referencia) {
                return true;
            }
        }
        return false;
    }
    
    public static void modificarAdmision(Recepciones modificada){
    List<Recepciones> lista = cargarLista();
    for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).getReferencia() == modificada.getReferencia()) {
            lista.set(i, modificada);
            break;
        }
    }
    guardarLista(lista);
}
}
