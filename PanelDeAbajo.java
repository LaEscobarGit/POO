import javax.swing.*;
import java.awt.*;

public class PanelDeAbajo extends JPanel {
    private Tabla tabla;
    private final JLabel lblTotalValor;
    private final JButton btnCancelar;
    private final JButton btnEliminar;

    @SuppressWarnings("unused")
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
        JLabel lblTotal = new JLabel("TOTAL A PAGAR:");
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
         public void actualizarTotal(double total) {
        lblTotalValor.setText(String.format("S/. %.2f", total));
    }
    private void mostrarTicketVenta() {
        JDialog ventanaTicket = new JDialog();
        ventanaTicket.setTitle("Ticket de Venta");
        ventanaTicket.setSize(350, 550);
        ventanaTicket.setResizable(false);
        
        JPanel panelTicket = new JPanel();
        panelTicket.setLayout(new BoxLayout(panelTicket, BoxLayout.Y_AXIS));
        panelTicket.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Fuente monoespaciada para alineación
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
        agregarLineaJustificada(panelTicket, "Fecha:", "23/05/2025", monoFont);
        agregarLineaJustificada(panelTicket, "Hora:", "03:11:00", monoFont);
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

        // Productos
        agregarProducto(panelTicket, "Pepto", 2, 2.50, monoFont);
        agregarProducto(panelTicket, "Arnica", 1, 12.80, monoFont);
        agregarProducto(panelTicket, "chicle porque no", 3, 1.20, monoFont);
        panelTicket.add(new JSeparator());
        panelTicket.add(Box.createVerticalStrut(5));

        // Totales
        agregarLineaJustificada(panelTicket, "Subtotal:", "S/ 22.10", monoFont);
        agregarLineaJustificada(panelTicket, "IVA (15%):", "S/ 5.00", monoFont);
        agregarLineaJustificada(panelTicket, "TOTAL:", "S/ 27.10", monoBold);
        panelTicket.add(new JSeparator());
        panelTicket.add(Box.createVerticalStrut(10));

        // Mensaje final
        agregarLineaCentrada(panelTicket, "¡Gracias por su compra!", monoFont);
        agregarLineaCentrada(panelTicket, "Vuelva pronto", monoFont);

        // Botón de impresión
        JButton btnImprimir = new JButton("IMPRIMIR TICKET");
        btnImprimir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ventanaTicket.dispose();
            }
        });

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