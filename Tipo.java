package com.mycompany.mavenproject1;

public enum Tipo{
    BUCAL("Bucal"), CAPSULA("Cápsula"), GOTAS("Gotas"), IMPLANTEPARCHE("Implante/Parche"),INHALADOR("Inhalador"), INYECCION("Inyección"),
    LIQUIDO("Líquido"), SUPOSITORIO("Supositorio"), TABLETA("Tableta"), TOPICO("Tópico");
    
    private final String nombre;

    private Tipo(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){
	return nombre;
    }
}
