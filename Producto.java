/**
* Esta clase denominada Producto modela un Producto de una
* farmacia.
*/

import java.io.Serializable;

public class Producto implements Serializable {
	private String nombre; 
	private Categoria categoria; 
	private String tipo; 
	private String medida; 
	private String descripcion;
	private double precio; 
	private int id; 
	private int stock; 
	private int cantidad;
	private boolean preescripcion;
	
	/**
	 * Constructor de la clase Producto.
	 * @param nombre Nombre del producto.
	 * @param categoria Categoría del producto (enum Categoria).
	 * @param tipo Tipo del producto (enum Tipo).
	 * @param medida Unidad de medida del producto.
	 * @param descripcion Breve descripción del producto.
	 * @param precio Precio unitario del producto.
	 * @param id Identificador único del producto.
	 * @param stock Cantidad disponible en inventario.
	 * @param cantidad Cantidad comprada.
	 * @param preescripcion Indica si requiere prescripción médica.
	 */
	public Producto(String nombre, Categoria categoria, String tipo, String medida,
	String descripcion, double precio, int id, int stock, boolean preescripcion){
		this.nombre = nombre;
		this.categoria = categoria; 
		this.tipo = tipo; 
		this.medida = medida; 
		this.descripcion = descripcion;
		this.precio = precio; 
		this.id = id; 
		this.stock = stock; 
		this.cantidad = 0;
		this.preescripcion = preescripcion;
	}
	
	//getters
	public String getNombre(){
		return nombre;
	}
	public Categoria getCategoria(){
		return categoria;
	}
	public String getTipo(){
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
	public int getStock(){
		return stock;
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
	public void setTipo(String tipo){
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
	public void setStock(int stock){
            this.stock = stock;
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

	public void agregarStock(int cantidad){
		stock = stock + cantidad;
	}
}