package com.mycompany.mavenproject1;
import java.io.Serializable;

public class Categoria implements Serializable{
    private String nombre;

    public Categoria(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
