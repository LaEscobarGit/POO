package com.mycompany.mavenproject1;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
/*
   Esta clase denominada ListaProductos define un vector de objetos
   Producto y un total de la nomina de Productos.
*/

public class Inventario implements Serializable {
    private ArrayList<Producto> lista; // Atributo que identifica un vector de Productos
    private boolean guardado = false;
    private File ruta;
    
    public Inventario() {
        lista = new ArrayList<>();
        ruta = cargarRuta();
        getInventario();
    }
    
    public ArrayList<Producto> getLista(){
        return lista;
    }
    public void agregarProducto(Producto a){
	lista.add(a);
    }

    public void eliminarProducto(int id){
        lista.removeIf(a -> a.getId() == id);
    }

    public Producto buscarProducto(int id){
        for (Producto a : lista) {
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public ArrayList<Producto> getProductos(){
        return lista;
    }

    public void guardarInventario(Component parent){
        try {
            if(guardado==false && ruta==null){
                guardado = true;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar inventario");
                int seleccion = fileChooser.showSaveDialog(parent);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    ruta = fileChooser.getSelectedFile();
                    if (!ruta.getName().endsWith(".obj")) {
                        ruta = new File(ruta.getAbsolutePath() + ".obj");
                    }
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ruta.txt"))) {
                        writer.write(ruta.getAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("Error al guardar la ruta en config.txt");
                    }
                    guardado = true;
                } else {
                    System.out.println("Cancelado");
                    return;
                }
            }
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
                out.writeObject(lista);
                System.out.println("Archivo guardado.");
            }
        }catch (IOException e){
            System.out.println("Error con el archivo.");
        }
    }

    public void getInventario(){
        if (ruta != null && ruta.exists()){
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))){
                lista = (ArrayList<Producto>) in.readObject();
            }catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al tratar con el archivo.");
                e.printStackTrace();
            }
        }
    }
    
    public File cargarRuta(){
        File config = new File("ruta.txt");
        if (config.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(config))) {
                String rutaLeida = reader.readLine();
                if (rutaLeida != null && !rutaLeida.isEmpty()) {
                    return new File(rutaLeida);
                }
            } catch (IOException e) {
                System.out.println("Error al leer config.txt");
            }
        }
        return null;
    }
}