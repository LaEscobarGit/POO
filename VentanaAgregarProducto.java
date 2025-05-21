package com.mycompany.mavenproject1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarProducto extends JFrame implements ActionListener {
    private Container contenedor; /* Un contenedor de elementos gráficos */
    private Inventario lista;
    private AdminPanel adminPanel;
	
    // Etiquetas estáticas para indicar los datos a ingresar
    private JLabel nombre, categoria, tipo, precio, cantidad, descripcion, id, stock, medida, preescripcion;
	
    // Campos de texto a ingresar de un Producto
    private JTextField campoNombre, campoPrecio, campoMedida, campoDescripcion, campoId;

    private JCheckBox checkPreescripcion; // Botones de radios
    private JComboBox<String> campoCategoria;
    private JComboBox<String> campoTipo;
    private JSpinner campoStock;
    private JSpinner campoCantidad; // Un selector de datos numerico
    private SpinnerNumberModel modeloSpinner; /* Modelo numerico para el selector numerico */
    private SpinnerNumberModel modeloSpinner2;
    
    /* Botones para agregar un Producto y para borrar la lista de
    Productos */
    private JButton agregar, limpiar;
	
    public VentanaAgregarProducto(Inventario inventario, AdminPanel adminPanel){
        this.lista = inventario;
        this.adminPanel = adminPanel;
    	inicio();
    	setTitle("Agregar Producto"); // Establece el titulo de la ventana
    	setSize(360,400); // Establece el tamaño de la ventana
    	setLocationRelativeTo(null); /* La ventana se posiciona en el centro de la pantalla */
    	setResizable(false); /* Establece que la ventana no puede cambiar de tamaño */
    }

    public VentanaAgregarProducto(Inventario inventario, AdminPanel adminPanel, Producto productoEditar){ //constructor de ventana modificar
        this.lista = inventario;
        this.adminPanel = adminPanel;
        inicio();
        
        campoId.setText(String.valueOf(productoEditar.getId()));
        campoId.setEnabled(false); // ID no modificable
        campoNombre.setText(productoEditar.getNombre());
        campoCategoria.setSelectedItem(productoEditar.getCategoria());
        campoTipo.setSelectedItem(productoEditar.getTipo());
        campoPrecio.setText(String.valueOf(productoEditar.getPrecio()));
        modeloSpinner.setValue(productoEditar.getStock());
        campoDescripcion.setText(productoEditar.getDescripcion());
        campoMedida.setText(productoEditar.getMedida());
        checkPreescripcion.setSelected(productoEditar.getPreescripcion());

        // Cambiar el texto del botón a "Modificar"
        agregar.setText("Modificar");
        
        agregar.addActionListener(e -> {
            productoEditar.setNombre(campoNombre.getText());
            productoEditar.setCategoria((Categoria) campoCategoria.getSelectedItem());
            productoEditar.setTipo((Tipo) campoTipo.getSelectedItem());
            productoEditar.setPrecio(Double.parseDouble(campoPrecio.getText()));
            productoEditar.setStock((Integer)modeloSpinner.getValue());
            productoEditar.setDescripcion(campoDescripcion.getText());
            productoEditar.setMedida(campoMedida.getText());
            productoEditar.setPreescripcion(checkPreescripcion.isSelected());

            adminPanel.actualizarTabla();
            JOptionPane.showMessageDialog(this,"El Producto ha sido modificado","Mensaje", JOptionPane.INFORMATION_MESSAGE,null);
        });
    }
    /**
    * Metodo que crea la ventana con sus diferentes componentes gráficos
    */
    public void inicio(){
    	contenedor = getContentPane(); /* Obtiene el panel de contenidos de la ventana */
    	contenedor.setLayout(null); /* Establece que el contenedor no tiene un layout */
        		// Establece la etiqueta y el campo de texto id del Producto
        id = new JLabel();
        id.setText("ID:");
	id.setBounds(20, 20, 135, 23);
	campoId = new JTextField();
	campoId.setBounds(160, 20, 160, 23);
	// Establece la etiqueta y el campo de texto nombre del Producto
	nombre = new JLabel();
	nombre.setText("Nombre:");
	nombre.setBounds(20, 50, 135, 23);
	campoNombre = new JTextField();
	campoNombre.setBounds(160, 50, 160, 23);
	
	// Establece la etiqueta y el combo box del categoria del Producto
	categoria = new JLabel();
	categoria.setText("Categoria:");
	categoria.setBounds(20, 80, 135, 23); 
	campoCategoria = new JComboBox<String>();
	campoCategoria.addItem("Antibiotico");
	campoCategoria.addItem("Antiseptico");
	campoCategoria.addItem("Analgesico");
	campoCategoria.setBounds(160, 80, 160, 23);
	
	// Establece la etiqueta y el combo box del tipo del Producto
	tipo = new JLabel();
	tipo.setText("Tipo:");
        tipo.setBounds(20,110,100,23); 
	campoTipo = new JComboBox<String>();
	campoTipo.addItem("Capsula");
	campoTipo.addItem("Liquido");
	campoTipo.addItem("Tableta");
	campoTipo.setBounds(160,110,160,23);
        // Establece la etiqueta y la medida del Producto
	medida = new JLabel();
	medida.setText("Medida:");
	medida.setBounds(20, 140, 135, 23);
	campoMedida = new JTextField();
	campoMedida.setBounds(160, 140, 160, 23);
	// Establece la etiqueta y el descripcion del Producto
	descripcion = new JLabel();
	descripcion.setText("Descripcion:");
	descripcion.setBounds(20, 170, 135, 23);
	campoDescripcion = new JTextField();
	campoDescripcion.setBounds(160, 170, 160, 23);
	// Establece la etiqueta y el precio del Producto
	precio = new JLabel();
	precio.setText("Precio:");
	precio.setBounds(20, 200, 135, 23);
	campoPrecio = new JTextField();
	campoPrecio.setBounds(160, 200, 160, 23);

	// Establece la etiqueta y el checkbox del Producto
	preescripcion = new JLabel();
	preescripcion.setText("Preescripcion:");
	preescripcion.setBounds(20,230,170,30);
	checkPreescripcion = new JCheckBox("Necesita preescripcion", false);
	checkPreescripcion.setBounds(160,230,160,30); 
	
	// Establece la etiqueta y el stock del Producto
	stock = new JLabel();
	stock.setText("Stock:");
	stock.setBounds(20, 260, 135, 23);
	campoStock = new JSpinner(); // Crea un spinner
	modeloSpinner = new SpinnerNumberModel(); /* Crea un modelo numerico para el spinner */
	modeloSpinner.setMinimum(1);
	modeloSpinner.setMaximum(101);
	modeloSpinner.setValue(30);
	campoStock.setModel(modeloSpinner); /* Asocia el modelo numerico al spinner */
	campoStock.setBounds(160, 260, 40, 23); /* Establece la posicion del spinner */
	
		/* CAMBIAR Establece la etiqueta y la cantidad del Producto
		cantidad = new JLabel();
		cantidad.setText("Cantidad:");
		cantidad.setBounds(20, 290, 135, 23);
		campoCantidad = new JSpinner(); // Crea un spinner
		modeloSpinner2 = new SpinnerNumberModel();
		modeloSpinner2.setMinimum(1);
		modeloSpinner2.setMaximum(5);
		modeloSpinner2.setValue(1);
		campoCantidad.setModel(modeloSpinner2); 
		campoCantidad.setBounds(160, 290, 40, 23);
		*/
		
	// Establece el boton agregar Producto
	agregar = new JButton();
	agregar.setText("Agregar");
	agregar.setBounds(50, 320, 100, 23); 
	agregar.addActionListener(this);
	
	// Establece el boton borrar Productos
	limpiar = new JButton();
	limpiar.setText("Borrar");
	limpiar.setBounds(195, 320, 100, 23);
	limpiar.addActionListener(this);
	
	// Se añade cada componente gráfico al contenedor de la ventana
	contenedor.add(id);
	contenedor.add(campoId);
	contenedor.add(nombre);
	contenedor.add(campoNombre);
	contenedor.add(categoria);
	contenedor.add(campoCategoria);
	contenedor.add(tipo);
	contenedor.add(campoTipo);
	contenedor.add(medida);
	contenedor.add(campoMedida);
	contenedor.add(descripcion);
	contenedor.add(campoDescripcion);
	contenedor.add(precio);
	contenedor.add(campoPrecio);
	contenedor.add(preescripcion);
	contenedor.add(checkPreescripcion);
	contenedor.add(stock);
	contenedor.add(campoStock);
	/*contenedor.add(cantidad);
	contenedor.add(campoCantidad);CAMBIAR*/
	contenedor.add(agregar);
	contenedor.add(limpiar);
    }
    /**
    * Metodo que borra los campos de texto ingresados en la ventana de
    * agregar Producto
    */
    public void limpiarCampos(){
	campoNombre.setText("");
	campoCategoria.setSelectedIndex(0);
	campoTipo.setSelectedIndex(0);
	campoPrecio.setText("");
	//campoCantidad.setValue(0); CAMBIAR
	campoMedida.setText("");
	campoDescripcion.setText("");
	campoId.setText("");
	checkPreescripcion.setSelected(false);
    }
	
    /**
    * Metodo que gestiona los eventos generados en la ventana principal
    */
    @Override
    public void actionPerformed(ActionEvent evento){
	if (evento.getSource() == agregar) { 
            añadirProducto();
	}
	if (evento.getSource() == limpiar) { 
            limpiarCampos();
	}
    }
	
    /**
    * Metodo que agrega un Producto a la lista de Productos
    * throws Exception Excepcion de campo nulo o error en formato de
    * numero
    */
    private void añadirProducto() {
	Tipo tipoT;
		
	// Obtiene el cargo seleccionado del combobox
	String itemSeleccionado = (String) campoTipo.getSelectedItem();
		
	/* De acuerdo al tipo seleccionado, se asigna el valor de atributo correspondiente */
	switch (itemSeleccionado) {
            case "Capsula": tipoT = Tipo.CAPSULA;
            break;
            case "Liquido": tipoT = Tipo.LIQUIDO;
            break;
            default: tipoT = Tipo.TABLETA;
        }
		
	Categoria tipoG;
	
	// Obtiene lo seleccionado del combobox
	String itemSeleccionado2 = (String) campoCategoria.getSelectedItem();
		
	/* De acuerdo al cargo seleccionado, se asigna el valor de atributo correspondiente */
        switch (itemSeleccionado2) {
            case "Antibiotico": tipoG = Categoria.ANTIBIOTICO;
            break;
            case "Antiseptico": tipoG = Categoria.ANTISEPTICO;
            break;
            default: tipoG = Categoria.ANALGESICO;
        }
		
	try {
            String valor1 = campoNombre.getText();
            String valor2 = campoMedida.getText();
            String valor3 = campoDescripcion.getText();
            double valor4 = Double.parseDouble(campoPrecio.getText());
            int valor5 = Integer.parseInt(campoId.getText());
            int valor6 = (int) campoStock.getValue();
            boolean valor7 = true; //CAMBIAR ESTA PARTE
            Producto nuevo = new Producto(valor1, tipoG, tipoT, valor2, valor3, valor4, valor5, valor6, valor7); // Se crea un Producto
            lista.agregarProducto(nuevo); /* Se agrega un Producto a la lista de Productos */
	
            // Mensaje de confirmacion de Producto agregado a la lista
            JOptionPane.showMessageDialog(this,"El Producto ha sido agregado","Mensaje", JOptionPane.INFORMATION_MESSAGE,null);
            if (adminPanel != null) {
                adminPanel.actualizarTabla();
            }
            limpiarCampos();
	}catch (Exception e) { 
            /* Si se produce algún tipo de error, se muestra un mensaje */
            JOptionPane.showMessageDialog(null,"Campo nulo o error en formato de numero", "Error", JOptionPane.ERROR_MESSAGE);
	}
    }
}
