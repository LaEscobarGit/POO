package com.mycompany.mavenproject1;
import java.awt.*;
import javax.swing.*;

public class VendedorPanel extends JPanel{
    private final Inventario inventario;
    private final Tabla tablePanel;
    private final PanelDeAbajo bottomPanel;
    private final Logo logo;

    public VendedorPanel(){
        setBackground(Color.white);
        setSize(978,626);
        setLayout(new BorderLayout(5, 5));
        Font smallFont = new Font("Arial", Font.PLAIN, 11);

        // INVENTARIO 
        inventario = new Inventario();  

        // COMPONENTES PRINCIPALES
        bottomPanel = new PanelDeAbajo(smallFont);
        bottomPanel.setBackground(Color.white);
        tablePanel = new Tabla(smallFont, bottomPanel);
        tablePanel.setBackground(Color.white);
        bottomPanel.setTabla(tablePanel);

        // PANELES SUPERIORES
        logo = new Logo(smallFont);
        JPanel topContainerPanel = new JPanel(new BorderLayout(5, 5));
        topContainerPanel.setBackground(Color.white);
        DatosDelSistema datosPanel = new DatosDelSistema(smallFont, inventario, tablePanel, bottomPanel, logo);
        topContainerPanel.add(datosPanel, BorderLayout.WEST);
        topContainerPanel.add(logo, BorderLayout.CENTER);

        // AGREGAR AL PANEL PRINCIPAL
        add(topContainerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}