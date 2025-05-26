/**
* Esta clase denominada Producto modela un Producto de una
* farmacia.
*/
package com.mycompany.mavenproject1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Producto implements Serializable {
	private String nombre; 
	private Categoria categoria; 
	private Tipo tipo; 
	private String medida; 
	private String descripcion;
	private double precio; 
	private int id; 
	private int cantidad;
	private boolean preescripcion;
        private List<Lote> lotes;
	/**
	 * Constructor de la clase Producto.
	 * @param nombre Nombre del producto.
	 * @param categoria Categoría del producto (enum Categoria).
	 * @param tipo Tipo del producto (enum Tipo).
	 * @param medida Unidad de medida del producto.
	 * @param descripcion Breve descripción del producto.
	 * @param precio Precio unitario del producto.
	 * @param id Identificador único del producto.
	 * @param cantidad Cantidad comprada.
	 * @param preescripcion Indica si requiere prescripción médica.
	 */
	public Producto(String nombre, Categoria categoria, Tipo tipo, String medida,
	String descripcion, double precio, int id, boolean preescripcion){
            this.nombre = nombre;
            this.categoria = categoria; 
            this.tipo = tipo; 
            this.medida = medida; 
            this.descripcion = descripcion;
            this.precio = precio; 
            this.id = id; 
            this.cantidad = 0;
            this.preescripcion = preescripcion;
            this.lotes = new ArrayList<>();
	}
	
	//getters
	public String getNombre(){
            return nombre;
	}
	public Categoria getCategoria(){
            return categoria;
	}
	public Tipo getTipo(){
            return tipo;
	}
	public String getMedida(){
            return medida;
	}
	public String getDescripcion(){
            return descripcion;
	}
	public double getPrecio(){
            return precio;
	}
	public int getId(){
            return id;
	}
	public int getCantidad(){
            return cantidad;
	}
	public boolean getPreescripcion(){
            return preescripcion;
	}

	//setters
        public void setNombre(String nombre){
            this.nombre = nombre;
	}
	public void setCategoria(Categoria categoria){
            this.categoria = categoria;
	}
	public void setTipo(Tipo tipo){
            this.tipo = tipo;
	}
	public void setMedida(String medida){
            this.medida = medida;
	}
	public void setDescripcion(String descripcion){
            this.descripcion = descripcion;
	}
	public void setPrecio(double precio){
            this.precio = precio;
	}
	public void setId(int id){
            this.id = id;
	}
	public void setPreescripcion(boolean preescripcion){
            this.preescripcion = preescripcion;
	}
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}

	//metodos
	public double calcularPago(){
		return (cantidad * precio);
	}
        
        public void agregarLote(int cantidad, LocalDate fechaCaducidad){
            lotes.add(new Lote(this, cantidad, fechaCaducidad));
        }
        
        public void eliminarLote(int cantidad, LocalDate fechaCaducidad) {
            lotes.remove(new Lote(this, cantidad, fechaCaducidad));
        }

        public void actualizarLote(int cantidadVieja, LocalDate fechaVieja, int cantidadNueva, LocalDate fechaNueva) {
            eliminarLote(cantidadVieja, fechaVieja);
            agregarLote(cantidadNueva, fechaNueva);
        }
        public int getStockTotal(){
            int total = 0;
            for(Lote lote:lotes){
                total = total + lote.getCantidad();
            }
            return total;
        }

        public int getStockDisponible(){
            int total = 0;
            for(Lote lote:lotes){
                if(!lote.caducado()){
                    total = total + lote.getCantidad();
                }
            }
            return total;
        }

        public int getStockCaducado(){
            int total = 0;
            for(Lote lote:lotes){
                if(lote.caducado()){
                    total = total + lote.getCantidad();
                }
            }
            return total;
        }

        public List<Lote> getLotes(){
            return lotes;
        }
        
        @Override public String toString(){
            return nombre;
        }
}