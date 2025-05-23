import java.awt.*;
import javax.swing.*;

public class Logo extends JPanel {
    public Logo(Font smallFont) {
        setPreferredSize(new Dimension(300, 150));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 10));
        setLayout(new BorderLayout(5, 10));
        
        // Panel contenedor para el margen derecho
        JPanel rightMarginPanel = new JPanel(new BorderLayout());
        rightMarginPanel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0)); // Margen izquierdo de 20px
        rightMarginPanel.setOpaque(false);
        
        // Panel para la información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Información de CAJA
        JPanel cajaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel lblCaja = new JLabel("CAJA NUMERO 01");
        lblCaja.setFont(smallFont.deriveFont(Font.BOLD, smallFont.getSize() + 2f));
        lblCaja.setForeground(new Color(0, 100, 0));
        cajaPanel.add(lblCaja);
        infoPanel.add(cajaPanel);
        
        infoPanel.add(Box.createVerticalStrut(15));
        
        // Información de PUNTOS
        JPanel puntosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel lblPuntos = new JLabel("PUNTOS:");
        lblPuntos.setFont(smallFont);
        JLabel lblPuntosValor = new JLabel("1000");
        lblPuntosValor.setFont(smallFont.deriveFont(Font.BOLD));
        lblPuntosValor.setForeground(Color.BLUE);
        puntosPanel.add(lblPuntos);
        puntosPanel.add(lblPuntosValor);
        infoPanel.add(puntosPanel);
        
        // Información de PRODUCTO
        JPanel productoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel lblProdNombre = new JLabel("PRODUCTO:");
        lblProdNombre.setFont(smallFont);
        JLabel lblProdValor = new JLabel("Medicina");
        lblProdValor.setFont(smallFont.deriveFont(Font.BOLD));
        lblProdValor.setForeground(new Color(150, 0, 0));
        productoPanel.add(lblProdNombre);
        productoPanel.add(lblProdValor);
        infoPanel.add(productoPanel);
        
        infoPanel.add(Box.createVerticalGlue());
        
        rightMarginPanel.add(infoPanel, BorderLayout.CENTER);
        add(rightMarginPanel, BorderLayout.WEST);
        
        // Espacio para logo/imagen
        JPanel logoSpace = new JPanel();
        logoSpace.setOpaque(false);
        add(logoSpace, BorderLayout.CENTER);
    }
}