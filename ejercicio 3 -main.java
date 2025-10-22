// Main.java
import Modelo.GestorEmpleados;
import Vista.VistaEmpleados;
import Controlador.ControladorEmpleados;

public class Main {
    public static void main(String[] args) {
        // Inicializar componentes MVC
        GestorEmpleados gestor = new GestorEmpleados();
        VistaEmpleados vista = new VistaEmpleados();
        ControladorEmpleados controlador = new ControladorEmpleados(gestor, vista);
        
        try {
            // Iniciar aplicación
            controlador.iniciar();
        } catch (Exception e) {
            System.out.println("Error crítico en la aplicación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Aplicación finalizada.");
        }
    }
}