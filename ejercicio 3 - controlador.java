// Controlador/ControladorEmpleados.java
package Controlador;

import Modelo.Empleado;
import Modelo.GestorEmpleados;
import Vista.VistaEmpleados;
import java.util.List;

public class ControladorEmpleados {
    private GestorEmpleados gestor;
    private VistaEmpleados vista;
    private boolean ejecutando;
    
    public ControladorEmpleados(GestorEmpleados gestor, VistaEmpleados vista) {
        this.gestor = gestor;
        this.vista = vista;
        this.ejecutando = true;
    }
    
    /**
     * Inicia la aplicación
     */
    public void iniciar() {
        vista.mostrarMensaje("=== BIENVENIDO AL SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        vista.mostrarMensaje("Empleados cargados: " + gestor.getTotalEmpleados());
        
        while (ejecutando) {
            try {
                vista.mostrarMenu();
                String opcion = vista.scanner.nextLine().trim();
                
                switch (opcion) {
                    case "1":
                        listarEmpleados();
                        break;
                    case "2":
                        agregarEmpleado();
                        break;
                    case "3":
                        buscarEmpleado();
                        break;
                    case "4":
                        eliminarEmpleado();
                        break;
                    case "5":
                        salir();
                        break;
                    default:
                        vista.mostrarError("Opción no válida. Por favor, seleccione 1-5.");
                }
                
                // Pausa antes de continuar
                if (ejecutando) {
                    vista.mostrarMensaje("\nPresione Enter para continuar...");
                    vista.scanner.nextLine();
                    vista.limpiarPantalla();
                }
                
            } catch (Exception e) {
                vista.mostrarError("Error inesperado: " + e.getMessage());
            }
        }
    }
    
    /**
     * Lista todos los empleados
     */
    private void listarEmpleados() {
        try {
            List<Empleado> empleados = gestor.getTodosLosEmpleados();
            vista.mostrarEmpleados(empleados);
        } catch (Exception e) {
            vista.mostrarError("Error al listar empleados: " + e.getMessage());
        }
    }
    
    /**
     * Agrega un nuevo empleado
     */
    private void agregarEmpleado() {
        try {
            Empleado nuevoEmpleado = vista.solicitarDatosEmpleado();
            gestor.agregarEmpleado(nuevoEmpleado);
            vista.mostrarExito("Empleado agregado exitosamente: " + nuevoEmpleado.getNombre());
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (Exception e) {
            vista.mostrarError("Error al agregar empleado: " + e.getMessage());
        }
    }
    
    /**
     * Busca un empleado por número
     */
    private void buscarEmpleado() {
        try {
            int numero = vista.solicitarNumeroEmpleado("buscar");
            Empleado empleado = gestor.buscarEmpleado(numero);
            vista.mostrarEmpleado(empleado);
        } catch (Exception e) {
            vista.mostrarError("Error al buscar empleado: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un empleado por número
     */
    private void eliminarEmpleado() {
        try {
            int numero = vista.solicitarNumeroEmpleado("eliminar");
            Empleado empleado = gestor.buscarEmpleado(numero);
            
            if (empleado != null) {
                vista.mostrarEmpleado(empleado);
                vista.mostrarMensaje("¿Está seguro de que desea eliminar este empleado? (s/n): ");
                String confirmacion = vista.scanner.nextLine().trim().toLowerCase();
                
                if (confirmacion.equals("s") || confirmacion.equals("si")) {
                    boolean eliminado = gestor.eliminarEmpleado(numero);
                    if (eliminado) {
                        vista.mostrarExito("Empleado eliminado exitosamente");
                    } else {
                        vista.mostrarError("No se pudo eliminar el empleado");
                    }
                } else {
                    vista.mostrarMensaje("Eliminación cancelada");
                }
            } else {
                vista.mostrarError("No se encontró un empleado con el número: " + numero);
            }
        } catch (Exception e) {
            vista.mostrarError("Error al eliminar empleado: " + e.getMessage());
        }
    }
    
    /**
     * Sale del programa
     */
    private void salir() {
        vista.mostrarExito("Gracias por usar el Sistema de Gestión de Empleados");
        vista.mostrarMensaje("Total de empleados registrados: " + gestor.getTotalEmpleados());
        ejecutando = false;
        vista.cerrar();
    }
}