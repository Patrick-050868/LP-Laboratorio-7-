// Modelo/Empleado.java
package Modelo;

import java.io.Serializable;

public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int numero;
    private String nombre;
    private double sueldo;
    
    // Constructor
    public Empleado(int numero, String nombre, double sueldo) {
        setNumero(numero);
        setNombre(nombre);
        setSueldo(sueldo);
    }
    
    // Getters
    public int getNumero() {
        return numero;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getSueldo() {
        return sueldo;
    }
    
    // Setters con validación
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor que cero");
        }
        this.numero = numero;
    }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }
    
    public void setSueldo(double sueldo) {
        if (sueldo < 0) {
            throw new IllegalArgumentException("El sueldo no puede ser negativo");
        }
        this.sueldo = sueldo;
    }
    
    @Override
    public String toString() {
        return String.format("Empleado #%d: %s - Sueldo: $%.2f", numero, nombre, sueldo);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Empleado empleado = (Empleado) obj;
        return numero == empleado.numero;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}