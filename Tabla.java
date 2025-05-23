import java.awt.*;
import javax.swing.*;

public class Tabla extends JPanel {
    public Tabla(Font smallFont) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(new Color(02, 02, 02, 02), 10)); 
        setPreferredSize(new Dimension(0, 250));

        String[] columnNames = {"N°", "ID. PRODUCTO", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"};
        Object[][] data = {
            {1, "099", "Medicina", 1, 150.0, 150.0},
            {2, "100", "Medicina", 2, 120.0, 240.0}
        };
        
        JTable table = new JTable(data, columnNames);
        table.setFont(smallFont);
        table.setRowHeight(20);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}