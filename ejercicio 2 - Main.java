import java.util.Scanner;

public class Main {
    private static Gestor gestor;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        gestor = new Gestor();
        scanner = new Scanner(System.in);
        
        System.out.println("=== GESTOR DE PERSONAJES DE VIDEOJUEGO MEJORADO ===");
        System.out.println("Integrantes: El Cuevara, Ángel Montezuma, Mario Santillana, Diego Iquira, Marisol Gairza, Nobel Villaverde");
        mostrarMenu();
    }
    
    private static void mostrarMenu() {
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Mostrar todos los personajes");
            System.out.println("2. Añadir nuevo personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Buscar personaje");
            System.out.println("=== NUEVAS FUNCIONALIDADES ===");
            System.out.println("6. Filtrar y ordenar personajes por atributos");
            System.out.println("7. Generar personajes aleatorios");
            System.out.println("8. Actualizar atributo individual");
            System.out.println("9. Mostrar estadísticas");
            System.out.println("10. Importar personajes desde archivo");
            System.out.println("11. Sistema de niveles");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    gestor.mostrarPersonajes();
                    break;
                case "2":
                    añadirPersonaje();
                    break;
                case "3":
                    modificarPersonaje();
                    break;
                case "4":
                    eliminarPersonaje();
                    break;
                case "5":
                    buscarPersonaje();
                    break;
                case "6":
                    filtrarPersonajes();
                    break;
                case "7":
                    generarPersonajesAleatorios();
                    break;
                case "8":
                    actualizarAtributoIndividual();
                    break;
                case "9":
                    gestor.mostrarEstadisticas();
                    break;
                case "10":
                    importarPersonajes();
                    break;
                case "11":
                    menuNiveles();
                    break;
                case "12":
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione 1-12.");
            }
        }
    }
    
    // Métodos originales
    private static void añadirPersonaje() {
        try {
            System.out.print("Nombre del personaje: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("Vida base: ");
            int vida = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Ataque base: ");
            int ataque = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Defensa base: ");
            int defensa = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Alcance: ");
            int alcance = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Nivel (opcional, presione Enter para 1): ");
            String nivelInput = scanner.nextLine().trim();
            int nivel = nivelInput.isEmpty() ? 1 : Integer.parseInt(nivelInput);
            
            gestor.añadirPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance, nivel));
            System.out.println("Personaje '" + nombre + "' añadido exitosamente");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Todos los atributos deben ser números enteros");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void modificarPersonaje() {
        try {
            System.out.print("Nombre del personaje a modificar: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.println("Deje en blanco los atributos que no desea modificar:");
            
            System.out.print("Nueva vida base: ");
            String vidaInput = scanner.nextLine().trim();
            Integer vida = vidaInput.isEmpty() ? null : Integer.parseInt(vidaInput);
            
            System.out.print("Nuevo ataque base: ");
            String ataqueInput = scanner.nextLine().trim();
            Integer ataque = ataqueInput.isEmpty() ? null : Integer.parseInt(ataqueInput);
            
            System.out.print("Nueva defensa base: ");
            String defensaInput = scanner.nextLine().trim();
            Integer defensa = defensaInput.isEmpty() ? null : Integer.parseInt(defensaInput);
            
            System.out.print("Nuevo alcance: ");
            String alcanceInput = scanner.nextLine().trim();
            Integer alcance = alcanceInput.isEmpty() ? null : Integer.parseInt(alcanceInput);
            
            System.out.print("Nuevo nivel: ");
            String nivelInput = scanner.nextLine().trim();
            Integer nivel = nivelInput.isEmpty() ? null : Integer.parseInt(nivelInput);
            
            gestor.modificarPersonaje(nombre, vida, ataque, defensa, alcance, nivel);
            System.out.println("Personaje '" + nombre + "' modificado exitosamente");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Los atributos deben ser números enteros válidos");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void eliminarPersonaje() {
        try {
            System.out.print("Nombre del personaje a eliminar: ");
            String nombre = scanner.nextLine().trim();
            
            gestor.borrarPersonaje(nombre);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void buscarPersonaje() {
        System.out.print("Nombre del personaje a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        gestor.obtenerPersonaje(nombre);
    }
    
    // Nuevos métodos para las funcionalidades adicionales
    
    // 6. Filtrar personajes por atributos - Mario Santillana
    private static void filtrarPersonajes() {
        System.out.print("Atributo para ordenar (vida/ataque/defensa/alcance/nivel): ");
        String atributo = scanner.nextLine().trim();
        
        System.out.print("Orden (asc/desc): ");
        String orden = scanner.nextLine().trim();
        
        gestor.filtrarYOrdenarPersonajes(atributo, orden);
    }
    
    // 7. Generar personajes aleatorios - Ángel Montezuma
    private static void generarPersonajesAleatorios() {
        try {
            System.out.print("Cantidad de personajes aleatorios a generar: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());
            
            gestor.generarPersonajesAleatorios(cantidad);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: La cantidad debe ser un número entero");
        }
    }
    
    // 8. Actualizar atributo individual - Diego Iquira
    private static void actualizarAtributoIndividual() {
        try {
            System.out.print("Nombre del personaje: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("Atributo a actualizar (vida/ataque/defensa/alcance/nivel): ");
            String atributo = scanner.nextLine().trim();
            
            System.out.print("Nuevo valor: ");
            int valor = Integer.parseInt(scanner.nextLine().trim());
            
            gestor.actualizarAtributoIndividual(nombre, atributo, valor);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: El valor debe ser un número entero");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // 10. Importar personajes desde archivo - Nobel Villaverde
    private static void importarPersonajes() {
        try {
            System.out.print("Nombre del archivo a importar (ej: personajes.txt): ");
            String nombreArchivo = scanner.nextLine().trim();
            
            gestor.importarPersonajesDesdeArchivo(nombreArchivo);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // 11. Sistema de niveles - El Cuevara
    private static void menuNiveles() {
        System.out.println("\n=== SISTEMA DE NIVELES ===");
        System.out.println("1. Subir nivel a un personaje");
        System.out.println("2. Subir múltiples niveles a un personaje");
        System.out.println("3. Subir nivel a todos los personajes");
        System.out.print("Seleccione una opción: ");
        
        String opcion = scanner.nextLine().trim();
        
        try {
            switch (opcion) {
                case "1":
                    System.out.print("Nombre del personaje: ");
                    String nombre = scanner.nextLine().trim();
                    gestor.subirNivelPersonaje(nombre);
                    break;
                case "2":
                    System.out.print("Nombre del personaje: ");
                    nombre = scanner.nextLine().trim();
                    System.out.print("Niveles a subir: ");
                    int niveles = Integer.parseInt(scanner.nextLine().trim());
                    gestor.subirNivelMultiple(niveles, nombre);
                    break;
                case "3":
                    gestor.subirNivelATodos();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El número de niveles debe ser un entero");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}