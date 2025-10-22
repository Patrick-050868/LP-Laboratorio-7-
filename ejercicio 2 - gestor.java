import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
    
    // 1. CARGAR PERSONAJES ALEATORIOS - Ángel Montezuma
    private void cargarPersonajesIniciales() {
        try {
            añadirPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
            añadirPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
            añadirPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
            
            // Personajes aleatorios adicionales
            generarPersonajesAleatorios(3);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error al cargar personajes iniciales: " + e.getMessage());
        }
    }
    
    public void generarPersonajesAleatorios(int cantidad) {
        String[] nombres = {"Mago", "Asesino", "Tanque", "Sanador", "Berserker", "Ninja", "Paladin", "Druida"};
        Random rand = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[rand.nextInt(nombres.length)] + (i + 1);
            int vida = rand.nextInt(5) + 1;
            int ataque = rand.nextInt(5) + 1;
            int defensa = rand.nextInt(5) + 1;
            int alcance = rand.nextInt(5) + 1;
            int nivel = rand.nextInt(5) + 1;
            
            try {
                Personaje personaje = new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
                if (!existePersonaje(nombre)) {
                    personajes.add(personaje);
                }
            } catch (IllegalArgumentException e) {
                // Si hay error, continuar con el siguiente
            }
        }
        guardarEnArchivo();
        System.out.println(cantidad + " personajes aleatorios generados exitosamente");
    }
    
    // 2. FILTRAR PERSONAJES POR ATRIBUTOS - Mario Santillana
    public void filtrarYOrdenarPersonajes(String atributo, String orden) {
        List<Personaje> personajesFiltrados = new ArrayList<>(personajes);
        
        switch (atributo.toLowerCase()) {
            case "vida":
                personajesFiltrados.sort(orden.equals("asc") ? 
                    Comparator.comparingInt(Personaje::getVida) : 
                    Comparator.comparingInt(Personaje::getVida).reversed());
                break;
            case "ataque":
                personajesFiltrados.sort(orden.equals("asc") ? 
                    Comparator.comparingInt(Personaje::getAtaque) : 
                    Comparator.comparingInt(Personaje::getAtaque).reversed());
                break;
            case "defensa":
                personajesFiltrados.sort(orden.equals("asc") ? 
                    Comparator.comparingInt(Personaje::getDefensa) : 
                    Comparator.comparingInt(Personaje::getDefensa).reversed());
                break;
            case "alcance":
                personajesFiltrados.sort(orden.equals("asc") ? 
                    Comparator.comparingInt(Personaje::getAlcance) : 
                    Comparator.comparingInt(Personaje::getAlcance).reversed());
                break;
            case "nivel":
                personajesFiltrados.sort(orden.equals("asc") ? 
                    Comparator.comparingInt(Personaje::getNivel) : 
                    Comparator.comparingInt(Personaje::getNivel).reversed());
                break;
            default:
                System.out.println("Atributo no válido");
                return;
        }
        
        System.out.println("\n=== PERSONAJES ORDENADOS POR " + atributo.toUpperCase() + " (" + orden + ") ===");
        for (int i = 0; i < personajesFiltrados.size(); i++) {
            System.out.println((i + 1) + ". " + personajesFiltrados.get(i).toStringSimple());
        }
    }
    
    // 3. ACTUALIZAR ATRIBUTOS INDIVIDUALES - Diego Iquira
    public void actualizarAtributoIndividual(String nombre, String atributo, int valor) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        switch (atributo.toLowerCase()) {
            case "vida":
                personaje.setVidaBase(valor);
                break;
            case "ataque":
                personaje.setAtaqueBase(valor);
                break;
            case "defensa":
                personaje.setDefensaBase(valor);
                break;
            case "alcance":
                personaje.setAlcance(valor);
                break;
            case "nivel":
                personaje.setNivel(valor);
                break;
            default:
                throw new IllegalArgumentException("Atributo '" + atributo + "' no válido");
        }
        
        guardarEnArchivo();
        System.out.println("Atributo '" + atributo + "' del personaje '" + nombre + "' actualizado a " + valor);
    }
    
    // 4. MOSTRAR ESTADÍSTICAS - Marisol Gairza
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas");
            return;
        }
        
        System.out.println("\n=== ESTADÍSTICAS GENERALES ===");
        System.out.println("Total de personajes: " + personajes.size());
        
        // Estadísticas de nivel
        double nivelPromedio = personajes.stream().mapToInt(Personaje::getNivel).average().orElse(0);
        int nivelMax = personajes.stream().mapToInt(Personaje::getNivel).max().orElse(0);
        int nivelMin = personajes.stream().mapToInt(Personaje::getNivel).min().orElse(0);
        
        // Estadísticas de atributos
        double vidaPromedio = personajes.stream().mapToInt(Personaje::getVida).average().orElse(0);
        double ataquePromedio = personajes.stream().mapToInt(Personaje::getAtaque).average().orElse(0);
        double defensaPromedio = personajes.stream().mapToInt(Personaje::getDefensa).average().orElse(0);
        double alcancePromedio = personajes.stream().mapToInt(Personaje::getAlcance).average().orElse(0);
        
        System.out.printf("Nivel promedio: %.2f (Máx: %d, Mín: %d)\n", nivelPromedio, nivelMax, nivelMin);
        System.out.printf("Vida promedio: %.2f\n", vidaPromedio);
        System.out.printf("Ataque promedio: %.2f\n", ataquePromedio);
        System.out.printf("Defensa promedio: %.2f\n", defensaPromedio);
        System.out.printf("Alcance promedio: %.2f\n", alcancePromedio);
        
        // Personaje más fuerte (mayor suma de atributos)
        Personaje masFuerte = personajes.stream()
            .max(Comparator.comparingInt(p -> p.getVida() + p.getAtaque() + p.getDefensa()))
            .orElse(null);
        
        if (masFuerte != null) {
            System.out.println("\nPersonaje más fuerte: " + masFuerte.getNombre() + 
                             " (Poder total: " + (masFuerte.getVida() + masFuerte.getAtaque() + masFuerte.getDefensa()) + ")");
        }
    }
    
    // 5. IMPORTAR PERSONAJES DESDE ARCHIVO - Nobel Villaverde
    public void importarPersonajesDesdeArchivo(String nombreArchivo) {
        File archivoImport = new File(nombreArchivo);
        if (!archivoImport.exists()) {
            throw new IllegalArgumentException("El archivo '" + nombreArchivo + "' no existe");
        }
        
        int contador = 0;
        try (Scanner scanner = new Scanner(archivoImport)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue; // Saltar líneas vacías o comentarios
                }
                
                try {
                    String[] partes = linea.split(",");
                    if (partes.length >= 5) {
                        String nombre = partes[0].trim();
                        int vida = Integer.parseInt(partes[1].trim());
                        int ataque = Integer.parseInt(partes[2].trim());
                        int defensa = Integer.parseInt(partes[3].trim());
                        int alcance = Integer.parseInt(partes[4].trim());
                        int nivel = partes.length > 5 ? Integer.parseInt(partes[5].trim()) : 1;
                        
                        if (!existePersonaje(nombre)) {
                            Personaje personaje = new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
                            personajes.add(personaje);
                            contador++;
                        }
                    }
                } catch (NumberFormatException | IllegalArgumentException e) {
                    System.out.println("Error en línea: " + linea + " - " + e.getMessage());
                }
            }
            
            guardarEnArchivo();
            System.out.println(contador + " personajes importados exitosamente desde " + nombreArchivo);
            
        } catch (IOException e) {
            throw new IllegalArgumentException("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    // 6. MEJORAR ATRIBUTOS CON NIVELES - El Cuevara
    public void subirNivelPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        personaje.subirNivel();
        guardarEnArchivo();
    }
    
    public void subirNivelMultiple(int niveles, String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        for (int i = 0; i < niveles; i++) {
            personaje.subirNivel();
        }
        guardarEnArchivo();
    }
    
    public void subirNivelATodos() {
        for (Personaje personaje : personajes) {
            personaje.subirNivel();
        }
        guardarEnArchivo();
        System.out.println("Todos los personajes han subido de nivel!");
    }
    
    // Métodos originales (actualizados)
    public boolean existePersonaje(String nombre) {
        return personajes.stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre));
    }
    
    public void añadirPersonaje(Personaje personaje) {
        if (existePersonaje(personaje.getNombre())) {
            throw new IllegalArgumentException("El personaje '" + personaje.getNombre() + "' ya existe");
        }
        personajes.add(personaje);
        guardarEnArchivo();
    }
    
    public void añadirPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        añadirPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance));
    }
    
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DE PERSONAJES ===");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + ". " + personajes.get(i).toStringSimple());
        }
    }
    
    public void mostrarPersonajesDetallados() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        
        System.out.println("\n=== LISTA DETALLADA DE PERSONAJES ===");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println((i + 1) + ". " + personajes.get(i));
        }
    }
    
    public void modificarPersonaje(String nombre, Integer vida, Integer ataque, Integer defensa, Integer alcance, Integer nivel) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        if (vida != null) personaje.setVidaBase(vida);
        if (ataque != null) personaje.setAtaqueBase(ataque);
        if (defensa != null) personaje.setDefensaBase(defensa);
        if (alcance != null) personaje.setAlcance(alcance);
        if (nivel != null) personaje.setNivel(nivel);
        
        guardarEnArchivo();
    }
    
    public void borrarPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje == null) {
            throw new IllegalArgumentException("Personaje '" + nombre + "' no encontrado");
        }
        
        personajes.remove(personaje);
        guardarEnArchivo();
        System.out.println("Personaje '" + nombre + "' eliminado exitosamente");
    }
    
    public Personaje buscarPersonaje(String nombre) {
        return personajes.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }
    
    public void obtenerPersonaje(String nombre) {
        Personaje personaje = buscarPersonaje(nombre);
        if (personaje != null) {
            System.out.println(personaje);
        } else {
            System.out.println("Personaje '" + nombre + "' no encontrado");
        }
    }
    
    public List<Personaje> getPersonajes() {
        return new ArrayList<>(personajes);
    }
}