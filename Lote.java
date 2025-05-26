package com.mycompany.mavenproject1;
import java.time.LocalDate;
import java.io.Serializable;

public class Lote implements Serializable{
    private Producto producto;
    private int cantidad;
    private LocalDate fechaCaducidad;

    public Lote(Producto producto, int cantidad, LocalDate fechaCaducidad){
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public Producto getProducto(){
        return producto;
    }
    
    public int getCantidad(){
        return cantidad;
    }

    public LocalDate getFechaCaducidad(){
        return fechaCaducidad;
    }

    public boolean caducado(){
        return fechaCaducidad.isBefore(LocalDate.now());
    }
    
    @Override public String toString(){
        return producto.getNombre();
    }
}
