package com.mycompany.mavenproject1;
import java.io.Serializable;

public class Cliente implements Serializable{
    private int numero;
    private String nombre;
    private long telefono;
    private String correo;
    private int puntos;

    public Cliente(int numero, String nombre, long telefono, String correo){
        this.numero = numero;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.puntos = 0;
    }

    public int getNumero(){
        return numero;
    }
    public String getNombre(){
        return nombre;
    }
    public long getTelefono(){
        return telefono;
    }
    public String getCorreo(){
        return correo;
    }
    public int getPuntos(){
        return puntos;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setTelefono(long telefono){
        this.telefono = telefono;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
}
