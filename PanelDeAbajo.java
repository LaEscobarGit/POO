import java.awt.*;
import javax.swing.*;

public class PanelDeAbajo extends JPanel {
    public PanelDeAbajo(Font smallFont) {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10)); 
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(smallFont);
        btnCancelar.setPreferredSize(new Dimension(100, 25));
                btnCancelar.addActionListener((var a) -> {
            // Mostrar diálogo de confirmación
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea Cancelar?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                // Aquí va la lógica para cancelar
                JOptionPane.showMessageDialog(
                    this,
                    "Venta Cancelada",
                    "Operación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        // Botón Eliminar Producto con acción
        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setFont(smallFont);
        btnEliminar.setPreferredSize(new Dimension(130, 25));
        btnEliminar.addActionListener((var e) -> {
            // Mostrar diálogo de confirmación
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar el producto seleccionado?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                // Aquí va la lógica para eliminar el producto
                JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente",
                    "Operación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        
        JButton btnGenerarVenta = new JButton("Generar Venta");
        btnGenerarVenta.setFont(smallFont);
        btnGenerarVenta.setPreferredSize(new Dimension(120, 25));

        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        JLabel lblTotal = new JLabel("TOTAL A PAGAR:");
        lblTotal.setFont(smallFont);
        JLabel lblTotalValor = new JLabel("S/. 390.00");
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

    
}