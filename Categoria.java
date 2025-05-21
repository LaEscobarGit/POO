package com.mycompany.mavenproject1;

public enum Categoria {
    ANTIBIOTICO("Antibiótico"),
    ANTISEPTICO("Antiséptico"),
    ANALGESICO("Analgésico");

    private final String nombre;

    private Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Override
	public String toString() {
		return nombre;
	}
}
