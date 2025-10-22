import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
    private List<Personaje> personajes;
    private String archivo;
    
    public Gestor(String archivo) {
        this.archivo = archivo;
        this.personajes = new ArrayList<>();
        cargarDesdeArchivo();
    }
    
    public Gestor() {
        this("personajes.dat");
    }
    
    // Método para cargar desde archivo binario
    @SuppressWarnings("unchecked")
    private void cargarDesdeArchivo() {
        File file = new File(archivo);
        if (!file.exists()) {
            // Si el archivo no existe, cargar personajes iniciales
            cargarPersonajesIniciales();
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            personajes = (List<Personaje>) ois.readObject();
            System.out.println("Personajes cargados desde archivo: " + personajes.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el archivo. Se iniciará con lista vacía.");
            personajes = new ArrayList<>();
            cargarPersonajesIniciales();
        }
    }
    
    // Método para guardar en archivo binario
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personajes);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
    
    // Cargar personajes iniciales
    private void cargarPersonajesIniciales() {
        try {
            añadirPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
            añadirPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
            añadirPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
            System.out.println("Personajes iniciales cargados automáticamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al cargar personajes iniciales: " + e.getMessage());
        }
    }
    
    // Verificar si existe un personaje
    public boolean existePersonaje(String nombre) {
        return personajes.stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre));
    }
    
    // Añadir personaje
    public void añadirPersonaje(Personaje personaje) {
        if (existePersonaje(personaje.getNombre())) {
            throw new IllegalArgumentException("El personaje '" + personaje.getNombre() + "' ya existe");
        }
        personajes.add(personaje);
        guardarEnArchivo();
    }
    
    // Añadir personaje con parámetros individuales
    public void añadirPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        añadirPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance));
    }
    
    // Mostrar todos los personajes
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + ". " + personajes.get(i));
        }
    }
    
    // Modificar personaje
    public void modificarPersonaje(String nombre, Integer vida, Integer ataque, Integer defensa, Integer alcance) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        if (vida != null) personaje.setVida(vida);
        if (ataque != null) personaje.setAtaque(ataque);
        if (defensa != null) personaje.setDefensa(defensa);
        if (alcance != null) personaje.setAlcance(alcance);
        
        guardarEnArchivo();
    }
    
    // Eliminar personaje
    public void borrarPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        personajes.remove(personaje);
        guardarEnArchivo();
        System.out.println("Personaje '" + nombre + "' eliminado exitosamente");
    }
    
    // Buscar personaje
    public Personaje buscarPersonaje(String nombre) {
        return personajes.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
    
    // Obtener información de personaje
    public void obtenerPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje != null) {
            System.out.println(personaje);
        } else {
            System.out.println("Personaje '" + nombre + "' no encontrado");
        }
    }
    
    // Getter para la lista de personajes (solo lectura)
    public List<Personaje> getPersonajes() {
        return new ArrayList<>(personajes);
    }
}