import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {
    public Main() {
        // Configuración del panel principal
        setLayout(new BorderLayout(5, 5));
        
        // Fuente pequeña para todos los componentes
        Font smallFont = new Font("Arial", Font.PLAIN, 11);


        // 2. PANELES SUPERIORES
        JPanel topContainerPanel = new JPanel(new BorderLayout(5, 5));
        topContainerPanel.add(new DatosDelSistema(smallFont), BorderLayout.WEST);
        topContainerPanel.add(new Logo(smallFont), BorderLayout.CENTER);

        // 3. TABLA DE PRODUCTOS
        Tabla tablePanel = new Tabla(smallFont);

        // 4. PANEL INFERIOR
        PanelDeAbajo bottomPanel = new PanelDeAbajo(smallFont);

        // Agregar componentes al panel principal
        add(topContainerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
            
    }
    
    // Método para usarlo en un JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Ventas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(963, 630);
        
        // Crear el panel principal y agregarlo al frame
        Main mainPanel = new Main();
        frame.add(mainPanel);

        
        // Mostrar ventana
        frame.setVisible(true);
    }
}