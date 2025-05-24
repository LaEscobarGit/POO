import java.awt.*;
import javax.swing.*;

public class DatosDelSistema extends JPanel {
    // Campos de clase (asegúrate que estén declarados aquí)
    private final JTextField txtProducto;
    private final JButton btnBuscarProducto;
    private final JSpinner spinnerCantidad;
    private final Inventario inventario;
    private final Tabla tabla;

    @SuppressWarnings("unused")
    public DatosDelSistema(Font smallFont, Inventario inventario, Tabla tabla) {
        this.inventario = inventario;
        this.tabla = tabla;

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));

        // Panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tamaños uniformes
        Dimension fieldSize = new Dimension(150, 25);
        Dimension buttonSize = new Dimension(80, 25);
        Dimension smallFieldSize = new Dimension(60, 25);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblEmpleado = new JLabel("Empleado:");
        lblEmpleado.setFont(smallFont);
        mainPanel.add(lblEmpleado, gbc);

        gbc.gridx = 1;
        String[] empleados = {"Roberto Espinoza", "Alvaro Aguirre"};
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
        JTextField txtCliente = new JTextField("12345678");
        txtCliente.setFont(smallFont);
        txtCliente.setPreferredSize(fieldSize);
        mainPanel.add(txtCliente, gbc);

        gbc.gridx = 2;
        JButton btnBuscarCliente = new JButton("Agregar");
        btnBuscarCliente.setFont(smallFont);
        btnBuscarCliente.setPreferredSize(buttonSize);
        mainPanel.add(btnBuscarCliente, gbc);

        // --- Sección PRODUCTO (PARTE CRÍTICA) ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblProducto = new JLabel("ID. Producto:");
        lblProducto.setFont(smallFont);
        mainPanel.add(lblProducto, gbc);

        // Inicialización CORRECTA del txtProducto
        txtProducto = new JTextField("1"); // Valor por defecto "1"
        txtProducto.setFont(smallFont);
        txtProducto.setPreferredSize(fieldSize);
        gbc.gridx = 1;
        mainPanel.add(txtProducto, gbc);

        // Botón de búsqueda
        btnBuscarProducto = new JButton("Agregar");
        btnBuscarProducto.setFont(smallFont);
        btnBuscarProducto.setPreferredSize(buttonSize);
        btnBuscarProducto.addActionListener(e -> buscarProducto()); // Listener correcto
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

        add(mainPanel, BorderLayout.CENTER);
    }

    private void buscarProducto() {
        try {
            int id = Integer.parseInt(txtProducto.getText()); // Ahora txtProducto NO es null
            int cantidad = (int) spinnerCantidad.getValue();
            
            Producto producto = inventario.buscarProducto(id);
            if (producto != null) {
                tabla.agregarProducto(producto, cantidad);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}