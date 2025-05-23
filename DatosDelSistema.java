import java.awt.*;
import javax.swing.*;

public class DatosDelSistema extends JPanel {
    public DatosDelSistema(Font smallFont) {
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
        Dimension smallFieldSize = new Dimension(60, 25); // Para el spinner

        // Sección EMPLEADO
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

        // Sección CLIENTE
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
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setFont(smallFont);
        btnBuscarCliente.setPreferredSize(buttonSize);
        mainPanel.add(btnBuscarCliente, gbc);

        // Sección PRODUCTO
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblProducto = new JLabel("ID. Producto:");
        lblProducto.setFont(smallFont);
        mainPanel.add(lblProducto, gbc);

        gbc.gridx = 1;
        JTextField txtProducto = new JTextField("1");
        txtProducto.setFont(smallFont);
        txtProducto.setPreferredSize(fieldSize);
        mainPanel.add(txtProducto, gbc);

        gbc.gridx = 2;
        JButton btnBuscarProducto = new JButton("Buscar");
        btnBuscarProducto.setFont(smallFont);
        btnBuscarProducto.setPreferredSize(buttonSize);
        mainPanel.add(btnBuscarProducto, gbc);

        // Sección CANTIDAD (ahora alineada con los demás)
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(smallFont);
        mainPanel.add(lblCantidad, gbc);

        gbc.gridx = 1;
        JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerCantidad.setPreferredSize(smallFieldSize);
        
        // Ajustamos el editor del spinner para que coincida con el estilo
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerCantidad, "#");
        spinnerCantidad.setEditor(editor);
        editor.getTextField().setFont(smallFont);
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        mainPanel.add(spinnerCantidad, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}