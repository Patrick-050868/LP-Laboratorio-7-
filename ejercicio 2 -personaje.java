import java.io.Serializable;

public class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;
    
    // Constructor
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);
    }
    
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        setNombre(nombre);
        setNivel(nivel);
        // Los atributos base se establecen para nivel 1
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public int getVida() {
        return vida * nivel; // La vida escala con el nivel
    }
    
    public int getVidaBase() {
        return vida;
    }
    
    public int getAtaque() {
        return ataque * nivel; // El ataque escala con el nivel
    }
    
    public int getAtaqueBase() {
        return ataque;
    }
    
    public int getDefensa() {
        return defensa * nivel; // La defensa escala con el nivel
    }
    
    public int getDefensaBase() {
        return defensa;
    }
    
    public int getAlcance() {
        return alcance; // El alcance no escala con el nivel
    }
    
    public int getNivel() {
        return nivel;
    }
    
    // Setters con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre debe ser una cadena no vacía");
        }
        this.nombre = nombre.trim();
    }
    
    public void setVidaBase(int vida) {
        if (vida <= 0) {
            throw new IllegalArgumentException("La vida debe ser un número entero mayor que cero");
        }
        this.vida = vida;
    }
    
    public void setAtaqueBase(int ataque) {
        if (ataque <= 0) {
            throw new IllegalArgumentException("El ataque debe ser un número entero mayor que cero");
        }
        this.ataque = ataque;
    }
    
    public void setDefensaBase(int defensa) {
        if (defensa <= 0) {
            throw new IllegalArgumentException("La defensa debe ser un número entero mayor que cero");
        }
        this.defensa = defensa;
    }
    
    public void setAlcance(int alcance) {
        if (alcance <= 0) {
            throw new IllegalArgumentException("El alcance debe ser un número entero mayor que cero");
        }
        this.alcance = alcance;
    }
    
    public void setNivel(int nivel) {
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel debe ser un número entero mayor que cero");
        }
        this.nivel = nivel;
    }
    
    // Subir de nivel
    public void subirNivel() {
        this.nivel++;
        System.out.println(nombre + " ha subido al nivel " + nivel + "!");
        System.out.println("Nuevos atributos - Vida: " + getVida() + ", Ataque: " + getAtaque() + ", Defensa: " + getDefensa());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personaje personaje = (Personaje) obj;
        return nombre.equalsIgnoreCase(personaje.nombre);
    }
    
    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s: Nivel=%d, Vida=%d(%d), Ataque=%d(%d), Defensa=%d(%d), Alcance=%d", 
                           nombre, nivel, getVida(), vida, getAtaque(), ataque, 
                           getDefensa(), defensa, alcance);
    }
    
    // Para mostrar sin detalles de base
    public String toStringSimple() {
        return String.format("%s: Nivel=%d, Vida=%d, Ataque=%d, Defensa=%d, Alcance=%d", 
                           nombre, nivel, getVida(), getAtaque(), getDefensa(), alcance);
    }
}