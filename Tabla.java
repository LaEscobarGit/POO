package com.mycompany.mavenproject1;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JPanel {
    private final DefaultTableModel model;
    private final JTable table;
    private PanelDeAbajo panelDeAbajo;
    
    public Tabla(Font smallFont, PanelDeAbajo panelDeAbajo) {
        this.panelDeAbajo = panelDeAbajo;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(new Color(2, 2, 2, 2), 10)); 
        setPreferredSize(new Dimension(0, 100));

        String[] columnNames = {"N°", "ID. PRODUCTO", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setFont(smallFont);
        table.setRowHeight(20);
        
        // Configuración de anchos de columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0,100));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarProducto(Producto producto, int cantidad) {
        double totalFila = producto.getPrecio() * cantidad; // Calcula el total para esta fila
        
        Object[] rowData = {
            model.getRowCount() + 1,
            producto.getId(),
            producto.getNombre(),
            cantidad,
            producto.getPrecio(),
            totalFila  // Usamos la variable recién calculada
        };
        
        model.addRow(rowData);
        actualizarTotal();
    }

    public double calcularTotal() {
        double totalGeneral = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object valor = model.getValueAt(i, 5);
            if (valor != null) {
                totalGeneral += Double.parseDouble(valor.toString());
            }
        }
        return totalGeneral;
    }

    public void actualizarTotal() {
        if (panelDeAbajo != null) {
            panelDeAbajo.actualizarTotal(calcularTotal());
        }
    }

    public void limpiarTabla() {
        model.setRowCount(0);
        actualizarTotal();
    }

    public void eliminarFilaSeleccionada() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Está seguro que desea eliminar el producto seleccionado?",
                "Confirmar Eliminación",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                model.removeRow(filaSeleccionada);
                actualizarNumeracion();
                actualizarTotal();
                JOptionPane.showMessageDialog(this,"Producto eliminado","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarNumeracion() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }
    
    public DefaultTableModel getModel() {
        return this.model;
    }
}