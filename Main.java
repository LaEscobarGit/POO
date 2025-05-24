import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {
    private final Inventario inventario;
    private final Tabla tablePanel;
    private final PanelDeAbajo bottomPanel;

    public Main() {
        setLayout(new BorderLayout(5, 5));
        Font smallFont = new Font("Arial", Font.PLAIN, 11);

        // 1. INVENTARIO 
        inventario = new Inventario();  

        // 2. COMPONENTES PRINCIPALES
        bottomPanel = new PanelDeAbajo(smallFont);
        tablePanel = new Tabla(smallFont, bottomPanel);
        bottomPanel.setTabla(tablePanel);

        // 3. PANELES SUPERIORES
        JPanel topContainerPanel = new JPanel(new BorderLayout(5, 5));
        DatosDelSistema datosPanel = new DatosDelSistema(smallFont, inventario, tablePanel);
        topContainerPanel.add(datosPanel, BorderLayout.WEST);
        topContainerPanel.add(new Logo(smallFont), BorderLayout.CENTER);

        // 4. AGREGAR AL PANEL PRINCIPAL
        add(topContainerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Ventas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(963, 630);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        frame.add(new Main());
        frame.setVisible(true);
    }
}