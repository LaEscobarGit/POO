import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.Date; 
import javax.swing.event.DocumentListener;  
import javax.swing.event.DocumentEvent;  
import java.awt.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")
public class PanelDeAbajo extends JPanel {
    private Tabla tabla;
    private final JLabel lblTotalValor;
    private final JButton btnCancelar;
    private final JButton btnEliminar;

    public PanelDeAbajo(Font smallFont) {  // Eliminamos Tabla del constructor
        this.tabla = null;  // Inicializamos como null
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10)); 
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        
        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(smallFont);
        btnCancelar.setPreferredSize(new Dimension(100, 25));
        btnCancelar.addActionListener(e -> {
            if (tabla != null) {
                confirmarCancelacion();
            } else {
                mostrarErrorTablaNoDisponible();
            }
        });
        
        // Botón Eliminar
        btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setFont(smallFont);
        btnEliminar.setPreferredSize(new Dimension(130, 25));
        btnEliminar.addActionListener(e -> {
            if (tabla != null) {
                confirmarEliminacion();
            } else {
                mostrarErrorTablaNoDisponible();
            }
        });
        
        JButton btnGenerarVenta = new JButton("Generar Venta");
        btnGenerarVenta.setFont(smallFont);
        btnGenerarVenta.setPreferredSize(new Dimension(120, 25));
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                mostrarTicketVenta();
            }
        });

        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        JLabel lblTotal = new JLabel("SUBTOTAL TOTAL:");
        lblTotal.setFont(smallFont);
        lblTotalValor = new JLabel("S/. 0.00");
        lblTotalValor.setFont(smallFont.deriveFont(Font.BOLD, 12));
        lblTotalValor.setForeground(Color.BLUE);
        pnlTotal.add(lblTotal);
        pnlTotal.add(lblTotalValor);
        
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnEliminar);  
        buttonPanel.add(btnGenerarVenta);
        buttonPanel.add(pnlTotal);
        
        add(buttonPanel, BorderLayout.EAST);
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    private void confirmarCancelacion() {
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea Cancelar?",
            "Confirmar cancelacion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            tabla.limpiarTabla();
            JOptionPane.showMessageDialog(
                this,
                "Venta Cancelada",
                "Operación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void confirmarEliminacion() {
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea eliminar el producto seleccionado?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            tabla.eliminarFilaSeleccionada();
            JOptionPane.showMessageDialog(
                this,
                "Producto eliminado correctamente",
                "Operación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void mostrarErrorTablaNoDisponible() {
        JOptionPane.showMessageDialog(
            this,
            "Error: La tabla no está disponible",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private JTextField crearCampoTexto(Font font) {
    JTextField campo = new JTextField(10);
    campo.setFont(font);
    campo.setHorizontalAlignment(JTextField.RIGHT);
    return campo;
}

private void agregarCampoConEtiqueta(JPanel panel, String etiqueta, JTextField campo, Font font) {
    JPanel panelCampo = new JPanel(new BorderLayout());
    panelCampo.add(new JLabel(etiqueta), BorderLayout.WEST);
    panelCampo.add(campo, BorderLayout.EAST);
    ((JLabel) panelCampo.getComponent(0)).setFont(font);
    panel.add(panelCampo);
}
         public void actualizarTotal(double total) {
        lblTotalValor.setText(String.format("S/. %.2f", total));
    }

    private void mostrarTicketVenta() {

        if (tabla == null || tabla.getModel().getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, 
            "No hay productos en la venta", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    JDialog ventanaTicket = new JDialog();
    ventanaTicket.setTitle("Ticket de Venta");
    ventanaTicket.setSize(350, 550);
    ventanaTicket.setResizable(false);
    
    JPanel panelTicket = new JPanel();
    panelTicket.setLayout(new BoxLayout(panelTicket, BoxLayout.Y_AXIS));
    panelTicket.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // Fuentes
    Font monoFont = new Font("Monospaced", Font.PLAIN, 12);
    Font monoBold = new Font("Monospaced", Font.BOLD, 12);

    // Espacio para logo
    JPanel panelLogo = new JPanel();
    panelLogo.setPreferredSize(new Dimension(80, 60));
    panelLogo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    panelLogo.add(new JLabel("[LOGO]"));
    panelTicket.add(panelLogo);
    panelTicket.add(Box.createVerticalStrut(10));

    // Datos de empresa
    agregarLineaCentrada(panelTicket, "La MotherFucking Farmacia", monoBold);
    agregarLineaCentrada(panelTicket, "Av. Tu Mama", monoFont);
    agregarLineaCentrada(panelTicket, "Tel: Prguntale a tu mama", monoFont);
    panelTicket.add(new JSeparator());
    panelTicket.add(Box.createVerticalStrut(5));

    // Información de venta
    agregarLineaJustificada(panelTicket, "Fecha:", new SimpleDateFormat("dd/MM/yyyy").format(new Date()), monoFont);
    agregarLineaJustificada(panelTicket, "Hora:", new SimpleDateFormat("HH:mm:ss").format(new Date()), monoFont);
    agregarLineaJustificada(panelTicket, "Ticket N°:", "00001234", monoFont);
    panelTicket.add(new JSeparator());
    panelTicket.add(Box.createVerticalStrut(5));

    // Encabezado productos
    JPanel encabezado = new JPanel(new GridLayout(1, 3));
    agregarCelda(encabezado, "PRODUCTO", monoBold, SwingConstants.LEFT);
    agregarCelda(encabezado, "CANT.", monoBold, SwingConstants.CENTER);
    agregarCelda(encabezado, "PRECIO", monoBold, SwingConstants.RIGHT);
    panelTicket.add(encabezado);
    panelTicket.add(new JSeparator());

    // Obtener productos de la tabla y calcular subtotal
    DefaultTableModel model = tabla.getModel();
    double subtotal = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        String producto = model.getValueAt(i, 2).toString();
        int cantidad = Integer.parseInt(model.getValueAt(i, 3).toString());
        double precio = Double.parseDouble(model.getValueAt(i, 4).toString());
        double totalFila = cantidad * precio;
        
        subtotal += totalFila;
        agregarProducto(panelTicket, producto, cantidad, precio, monoFont);
    }

    panelTicket.add(new JSeparator());
    panelTicket.add(Box.createVerticalStrut(5));

    // Calcular IVA (04%) y total
    double iva = subtotal * 0.04;
    double total = subtotal + iva;

    // Mostrar montos
    agregarLineaJustificada(panelTicket, "Subtotal:", String.format("S/ %.2f", subtotal), monoFont);
    agregarLineaJustificada(panelTicket, "IVA (15%):", String.format("S/ %.2f", iva), monoFont);
    agregarLineaJustificada(panelTicket, "TOTAL:", String.format("S/ %.2f", total), monoBold);
    panelTicket.add(new JSeparator());
    panelTicket.add(Box.createVerticalStrut(5));

    // Campos para CARGO y CAMBIO
    JTextField txtCargo = new JTextField(10);
    txtCargo.setFont(monoFont);
    txtCargo.setHorizontalAlignment(JTextField.RIGHT);
    
    JLabel lblCambio = new JLabel("S/ 0.00");
    lblCambio.setFont(monoBold);
    
    // Listener para calcular cambio automáticamente
    txtCargo.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { calcularCambio(); }
        @Override
        public void removeUpdate(DocumentEvent e) { calcularCambio(); }
        @Override
        public void changedUpdate(DocumentEvent e) { calcularCambio(); }
        
        private void calcularCambio() {
            try {
                if (!txtCargo.getText().isEmpty()) {
                    double cargo = Double.parseDouble(txtCargo.getText());
                    double cambio = cargo - total;
                    lblCambio.setText(String.format("S/ %.2f", cambio));
                } else {
                    lblCambio.setText("S/ 0.00");
                }
            } catch (NumberFormatException ex) {
                lblCambio.setText("Inválido");
            }
        }
    });

    JPanel panelCargo = new JPanel(new BorderLayout());
    panelCargo.add(new JLabel("CARGO:"), BorderLayout.WEST);
    panelCargo.add(txtCargo, BorderLayout.EAST);
    ((JLabel)panelCargo.getComponent(0)).setFont(monoFont);
    
    JPanel panelCambio = new JPanel(new BorderLayout());
    panelCambio.add(new JLabel("CAMBIO:"), BorderLayout.WEST);
    panelCambio.add(lblCambio, BorderLayout.EAST);
    ((JLabel)panelCambio.getComponent(0)).setFont(monoFont);
    
    panelTicket.add(panelCargo);
    panelTicket.add(panelCambio);
    panelTicket.add(new JSeparator());
    panelTicket.add(Box.createVerticalStrut(10));

    // Mensaje final
    agregarLineaCentrada(panelTicket, "¡Gracias por su compra!", monoFont);
    agregarLineaCentrada(panelTicket, "Vuelva pronto", monoFont);

    // Botón de impresión
    JButton btnImprimir = new JButton("IMPRIMIR TICKET");
    btnImprimir.setFont(monoFont);
    btnImprimir.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnImprimir.addActionListener(e -> ventanaTicket.dispose());
       // Listener para calcular cambio y validar el cargo
    DocumentListener docListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { validarCargo(); }
        @Override
        public void removeUpdate(DocumentEvent e) { validarCargo(); }
        @Override
        public void changedUpdate(DocumentEvent e) { validarCargo(); }
        
        private void calcularCambio() {
            try {
                if (!txtCargo.getText().isEmpty()) {
                    double cargo = Double.parseDouble(txtCargo.getText());
                    double cambio = cargo - total;
                    lblCambio.setText(String.format("S/ %.2f", cambio));
                } else {
                    lblCambio.setText("S/ 0.00");
                }
            } catch (NumberFormatException ex) {
                lblCambio.setText("Inválido");
            }
        }
        
        private void validarCargo() {
            calcularCambio();
            try {
                if (!txtCargo.getText().isEmpty()) {
                    double cargo = Double.parseDouble(txtCargo.getText());
                    btnImprimir.setEnabled(cargo >= total);
                } else {
                    btnImprimir.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                btnImprimir.setEnabled(false);
            }
        }
    };

    txtCargo.getDocument().addDocumentListener(docListener);

    JPanel panelContenedor = new JPanel(new BorderLayout());
    panelContenedor.add(panelTicket, BorderLayout.CENTER);
    panelContenedor.add(btnImprimir, BorderLayout.SOUTH);
    panelContenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    ventanaTicket.add(panelContenedor);
    ventanaTicket.setLocationRelativeTo(this);
    ventanaTicket.setVisible(true);
    }

    // Métodos auxiliares
    private void agregarLineaCentrada(JPanel panel, String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }

    private void agregarLineaJustificada(JPanel panel, String izquierda, String derecha, Font font) {
        JPanel linea = new JPanel(new BorderLayout());
        linea.add(new JLabel(izquierda), BorderLayout.WEST);
        linea.add(new JLabel(derecha), BorderLayout.EAST);
        ((JLabel)linea.getComponent(0)).setFont(font);
        ((JLabel)linea.getComponent(1)).setFont(font);
        panel.add(linea);
    }

    private void agregarCelda(JPanel panel, String texto, Font font, int alineacion) {
        JLabel label = new JLabel(texto, alineacion);
        label.setFont(font);
        panel.add(label);
    }

    private void agregarProducto(JPanel panel, String nombre, int cantidad, double precio, Font font) {
        JPanel producto = new JPanel(new GridLayout(1, 3));
        agregarCelda(producto, nombre, font, SwingConstants.LEFT);
        agregarCelda(producto, String.valueOf(cantidad), font, SwingConstants.CENTER);
        agregarCelda(producto, String.format("%.2f", precio), font, SwingConstants.RIGHT);
        panel.add(producto);
    }
}