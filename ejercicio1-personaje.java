import java.io.Serializable;

public class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    
    // Constructor
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        setNombre(nombre);
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public int getVida() {
        return vida;
    }
    
    public int getAtaque() {
        return ataque;
    }
    
    public int getDefensa() {
        return defensa;
    }
    
    public int getAlcance() {
        return alcance;
    }
    
    // Setters con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre debe ser una cadena no vacía");
        }
        this.nombre = nombre.trim();
    }
    
    public void setVida(int vida) {
        if (vida <= 0) {
            throw new IllegalArgumentException("La vida debe ser un número entero mayor que cero");
        }
        this.vida = vida;
    }
    
    public void setAtaque(int ataque) {
        if (ataque <= 0) {
            throw new IllegalArgumentException("El ataque debe ser un número entero mayor que cero");
        }
        this.ataque = ataque;
    }
    
    public void setDefensa(int defensa) {
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
        return String.format("%s: Vida=%d, Ataque=%d, Defensa=%d, Alcance=%d", 
                           nombre, vida, ataque, defensa, alcance);
    }
}