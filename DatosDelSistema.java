package com.mycompany.mavenproject1;
import java.awt.*;
import javax.swing.*;

public class DatosDelSistema extends JPanel {
    private final JTextField txtProducto;
    private final JTextField txtCliente;
    private final JButton btnAgregarProducto;
    private final JSpinner spinnerCantidad;
    private final Inventario inventario;
    private final Tabla tabla;
    private final ListaClientes listaClientes;
    private final PanelDeAbajo panelAbajo;
    private final Logo logo;

    @SuppressWarnings("unused")
    public DatosDelSistema(Font smallFont, Inventario inventario, Tabla tabla, PanelDeAbajo panelAbajo, Logo logo) {
        this.inventario = inventario;
        this.tabla = tabla;
        this.listaClientes = new ListaClientes();
        this.panelAbajo = panelAbajo;
        this.logo = logo;
        
        setBackground(Color.white);
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));

        // Panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tamaños iguales
        Dimension fieldSize = new Dimension(150, 25);
        Dimension buttonSize = new Dimension(80, 25);
        Dimension smallFieldSize = new Dimension(60, 25);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblEmpleado = new JLabel("Empleado:");
        lblEmpleado.setFont(smallFont);
        mainPanel.add(lblEmpleado, gbc);

        gbc.gridx = 1;
        String[] empleados = {"Roberto Espinoza", "Álvaro Aguirre"};
        JComboBox<String> comboEmpleados = new JComboBox<>(empleados);
        comboEmpleados.setFont(smallFont);
        comboEmpleados.setPreferredSize(fieldSize);
        mainPanel.add(comboEmpleados, gbc);

        // --- Sección CLIENTE ---
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblCliente = new JLabel("N° Cliente:");
        lblCliente.setFont(smallFont);
        mainPanel.add(lblCliente, gbc);

        gbc.gridx = 1;
        txtCliente = new JTextField();
        txtCliente.setFont(smallFont);
        txtCliente.setPreferredSize(fieldSize);
        mainPanel.add(txtCliente, gbc);

        gbc.gridx = 2;
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setFont(smallFont);
        btnBuscarCliente.setPreferredSize(buttonSize);
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        mainPanel.add(btnBuscarCliente, gbc);

        // --- Sección PRODUCTO ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblProducto = new JLabel("ID. Producto:");
        lblProducto.setFont(smallFont);
        mainPanel.add(lblProducto, gbc);

        // Inicialización del txtProducto
        txtProducto = new JTextField(); 
        txtProducto.setFont(smallFont);
        txtProducto.setPreferredSize(fieldSize);
        gbc.gridx = 1;
        mainPanel.add(txtProducto, gbc);

        // Botón de búsqueda
        JButton btnBuscarProducto = new JButton("Buscar");
        btnBuscarProducto.setFont(smallFont);
        btnBuscarProducto.setPreferredSize(buttonSize);
        btnBuscarProducto.addActionListener(e -> buscarProducto()); 
        gbc.gridx = 2;
        mainPanel.add(btnBuscarProducto, gbc);

        // --- Sección CANTIDAD ---
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(smallFont);
        mainPanel.add(lblCantidad, gbc);

        gbc.gridx = 1;
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerCantidad.setPreferredSize(smallFieldSize);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerCantidad, "#");
        spinnerCantidad.setEditor(editor);
        editor.getTextField().setFont(smallFont);
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(spinnerCantidad, gbc);
        
        // Botón de búsqueda
        btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.setFont(smallFont);
        btnAgregarProducto.setPreferredSize(buttonSize);
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        gbc.gridx = 2;
        mainPanel.add(btnAgregarProducto, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void agregarProducto(){
        try {
            int id = Integer.parseInt(txtProducto.getText());
            int cantidad = (int) spinnerCantidad.getValue();
            
            Producto producto = inventario.buscarProducto(id);
            if (producto != null){
                tabla.agregarProducto(producto, cantidad);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscarProducto(){
        VentanaProducto ventanaProd = new VentanaProducto(inventario, this);
        ventanaProd.setVisible(true);
    }
    
    public void buscarCliente(){
        VentanaCliente ventanaCl = new VentanaCliente(this, panelAbajo, logo);
        ventanaCl.setVisible(true);
    }
    
    public void setTxtProducto(String id){
        txtProducto.setText(id);
    }
    
    public void setTxtCliente(Cliente cliente){
        String numStr = String.valueOf(cliente.getNumero());
        txtCliente.setText(numStr);
    }
    
    public void setFieldProd(){
        txtProducto.setText("");
    }
    
    public void setFieldCliente(){
        txtCliente.setText("");
    }
}