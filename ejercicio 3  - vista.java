// Vista/VistaEmpleados.java
package Vista;

import Modelo.Empleado;
import java.util.List;
import java.util.Scanner;

public class VistaEmpleados {
    private Scanner scanner;
    
    public VistaEmpleados() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra el menú principal
     */
    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por número");
        System.out.println("4. Eliminar un empleado por número");
        System.out.println("5. Salir del programa");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Muestra la lista de empleados
     */
    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println((i + 1) + ". " + empleados.get(i));
        }
        System.out.println("Total: " + empleados.size() + " empleados");
    }
    
    /**
     * Muestra un empleado específico
     */
    public void mostrarEmpleado(Empleado empleado) {
        if (empleado != null) {
            System.out.println("\n=== EMPLEADO ENCONTRADO ===");
            System.out.println(empleado);
        } else {
            System.out.println("\nEmpleado no encontrado.");
        }
    }
    
    /**
     * Solicita datos para un nuevo empleado
     */
    public Empleado solicitarDatosEmpleado() {
        System.out.println("\n=== AGREGAR NUEVO EMPLEADO ===");
        
        int numero = leerEntero("Número de empleado: ");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        double sueldo = leerDouble("Sueldo: ");
        
        return new Empleado(numero, nombre, sueldo);
    }
    
    /**
     * Solicita número de empleado para búsqueda/eliminación
     */
    public int solicitarNumeroEmpleado(String operacion) {
        System.out.println("\n=== " + operacion.toUpperCase() + " EMPLEADO ===");
        return leerEntero("Ingrese el número del empleado: ");
    }
    
    /**
     * Lee un entero con validación
     */
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }
    
    /**
     * Lee un double con validación
     */
    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }
    
    /**
     * Muestra mensajes al usuario
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Muestra mensajes de error
     */
    public void mostrarError(String error) {
        System.out.println("❌ Error: " + error);
    }
    
    /**
     * Muestra mensajes de éxito
     */
    public void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }
    
    /**
     * Limpia la pantalla (consola)
     */
    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla el limpiado, simplemente imprimir líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    /**
     * Cierra los recursos
     */
    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}