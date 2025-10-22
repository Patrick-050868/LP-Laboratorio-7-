import java.util.Scanner;

public class Main {
    private static Gestor gestor;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        gestor = new Gestor();
        scanner = new Scanner(System.in);
        
        System.out.println("=== GESTOR DE PERSONAJES DE VIDEOJUEGO ===");
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
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    mostrarPersonajes();
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
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione 1-6.");
            }
        }
    }
    
    private static void mostrarPersonajes() {
        gestor.mostrarPersonajes();
    }
    
    private static void añadirPersonaje() {
        try {
            System.out.print("Nombre del personaje: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("Vida: ");
            int vida = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Ataque: ");
            int ataque = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Defensa: ");
            int defensa = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Alcance: ");
            int alcance = Integer.parseInt(scanner.nextLine().trim());
            
            gestor.añadirPersonaje(nombre, vida, ataque, defensa, alcance);
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
            
            System.out.print("Nueva vida: ");
            String vidaInput = scanner.nextLine().trim();
            Integer vida = vidaInput.isEmpty() ? null : Integer.parseInt(vidaInput);
            
            System.out.print("Nuevo ataque: ");
            String ataqueInput = scanner.nextLine().trim();
            Integer ataque = ataqueInput.isEmpty() ? null : Integer.parseInt(ataqueInput);
            
            System.out.print("Nueva defensa: ");
            String defensaInput = scanner.nextLine().trim();
            Integer defensa = defensaInput.isEmpty() ? null : Integer.parseInt(defensaInput);
            
            System.out.print("Nuevo alcance: ");
            String alcanceInput = scanner.nextLine().trim();
            Integer alcance = alcanceInput.isEmpty() ? null : Integer.parseInt(alcanceInput);
            
            gestor.modificarPersonaje(nombre, vida, ataque, defensa, alcance);
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
}