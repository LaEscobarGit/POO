package com.mycompany.mavenproject1;

import java.util.*;
import java.util.ArrayList;
/*
   Esta clase denominada ListaProductos define un vector de objetos
   Producto y un total de la nomina de Productos.
*/

public class ListaProductos{
    public ArrayList<Producto> lista; // Atributo que identifica un vector de Productos
    public double totalRecibo = 0; // Atributo que identifica el total de la nomina de la empresa

    public ListaProductos(){
	lista = new ArrayList<>(); // Crea el vector de Productos
    }
	
    /**
    * Metodo que agrega un Producto a la lista de Productos
    * @param a Parámetro que define un Producto a agregar a la lista
    * de Productos
    */
    public void agregarProducto(Producto a){
	lista.add(a);
    }
	
    /**
    * Metodo que calcula la nomina total mensual de la empresa
    * @return La nomina total mensual de la empresa
    */
    public double calculartotalRecibo(){
	for (int i = 0; i < lista.size(); i++){ 
            /* Recorre el vector de Productos */
            Producto e = lista.get(i);	// Obtiene un elemento de la lista de Productos
            totalRecibo = totalRecibo + e.calcularPago();	// Calcula el salario de un Producto y lo totaliza
	}
	return totalRecibo;
    }
	
    /**
    * Metodo que convierte los datos de la lista de Productos en una
    * matriz
    */
    public String[][] obtenerMatriz() {
	String datos[][] = new String[lista.size()][3]; // Se crea la matriz
	totalRecibo = 0;
	for (int i = 0; i < lista.size(); i++){ 
            // Recorre el vector de Productos
            Producto e = lista.get(i); // Obtiene un elemento de la lista de Productos

            /* Coloca el nombre del Producto en la primera columna de la matriz */
            datos[i][0] = e.getNombre();
			
            /* Coloca los categoria del Producto en la segunda columna de la matriz */
            datos[i][1] = e.getCategoria().toString();
			
            /* Coloca el salario del Producto en la tercera columna de la matriz */
            datos[i][2] = Double.toString(e.calcularPago());
			
            // Va acumulando el total de nomina mensual de la empresa
            totalRecibo = totalRecibo + e.calcularPago();
	}
	return datos;
    }
	
    /**
    * Metodo que convierte los datos de la lista de Productos a texto
    */
    public String convertirTexto(){
	String texto = "";
	for (int i = 0; i < lista.size(); i++){
            // Recorre el vector de Productos
            // Obtiene un elemento de la lista de Productos
            Producto e = lista.get(i);
            // Concatena en una variable String los datos de un Producto
            texto = texto + "Nombre = " + e.getNombre() + "\n" + "Categoria = " + e.getCategoria() + "\n" + "Tipo = " +
            e.getTipo() + "\n" + "Medida = " + e.getMedida() + "\n" + "Precio = $" + e.getPrecio() + "\n" + 
            "Descripcion = " + e.getDescripcion() + "\n" + "Id = " + e.getId() + "\n" + 
            "Stock = " + e.getStock() + "\n" + "Cantidad = " + e.getCantidad() + "\n---------\n";
	}
	// Concatena en una variable String el total de la nomina
	texto = texto + "Total recibo = $" + String.format("%.2f", calculartotalRecibo());
	return texto;
    }
}