// Modelo/GestorEmpleados.java
package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    private List<Empleado> empleados;
    private String archivo;
    
    public GestorEmpleados(String archivo) {
        this.archivo = archivo;
        this.empleados = new ArrayList<>();
        leerEmpleados(); // Cargar empleados al iniciar
    }
    
    public GestorEmpleados() {
        this("empleados.dat");
    }
    
    /**
     * Lee todos los empleados desde el archivo
     */
    @SuppressWarnings("unchecked")
    public void leerEmpleados() {
        File file = new File(archivo);
        if (!file.exists()) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al agregar empleados.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            empleados = (List<Empleado>) ois.readObject();
            System.out.println("Empleados cargados exitosamente: " + empleados.size() + " empleados");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura del archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Formato de archivo no compatible: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al leer empleados: " + e.getMessage());
        }
    }
    
    /**
     * Guarda todos los empleados en el archivo
     */
    private void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar empleados: " + e.getMessage(), e);
        }
    }
    
    /**
     * Agrega un nuevo empleado
     */
    public void agregarEmpleado(Empleado empleado) {
        if (buscarEmpleado(empleado.getNumero()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con el número: " + empleado.getNumero());
        }
        
        empleados.add(empleado);
        guardarEmpleados();
    }
    
    /**
     * Busca un empleado por número
     */
    public Empleado buscarEmpleado(int numero) {
        return empleados.stream()
                .filter(e -> e.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Elimina un empleado por número
     */
    public boolean eliminarEmpleado(int numero) {
        Empleado empleado = buscarEmpleado(numero);
        if (empleado != null) {
            empleados.remove(empleado);
            guardarEmpleados();
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene todos los empleados
     */
    public List<Empleado> getTodosLosEmpleados() {
        return new ArrayList<>(empleados);
    }
    
    /**
     * Verifica si existe un empleado con el número dado
     */
    public boolean existeEmpleado(int numero) {
        return buscarEmpleado(numero) != null;
    }
    
    /**
     * Obtiene la cantidad total de empleados
     */
    public int getTotalEmpleados() {
        return empleados.size();
    }
}