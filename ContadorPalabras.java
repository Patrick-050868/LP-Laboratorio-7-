import javax.swing.*;
import java.io.*;
import java.util.*;

public class ContadorPalabras {
  public static void main(String[] args){
    file archivo = null;
    while (archivo == null;{
      JFileChooser fc = new JFileChooser ();
      int opcion = fc. showOpenDialog(null);

      if (option == JFileChooser.APPROVE_OPTION){
        archivo = fc.getSelectedFile();
        if(!archivo.exists() || !archivo.isFile()){
          JOptionPane.showMessageDialog(null,"Archivo inválido. Intente de nuevo.");
          archivo = null;
        }
      }else{
        JOptionPane.showMessageDialog(null,"No se seleccion archivo");
        return;
      }
    }
    int lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Integer> frecuencia = new HashMap<>();
    try (BufferedReader br = new BufferedReader (new FileReader (archivo))){
      String linea;
      while ((linea = br.readLine()) != null) {
        lineas++;
        caracteres += linea.length();

        StringBilder palabra = new StringBuilder ();
        for (char c : linea.toCharArray()) {
          if (Character.isLetterOrDigit(c)) palabra.append(c);
          else if (palabra.length() > 0) {
            String p = palabra.toString().toLowerCase();
            palabras++;
            frecuencia.put(p, frecuencia.getOrDefault(p, 0) + 1);
            palabra.setLength(0);
          }
        }
        if(palabra.length()>0){
          String p = palabra.toString().toLowerCase();
          palabras++;
          frecuencia.put(p,frecuencia.getOrDefault(p,0)+1);
        }
      }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
        return;
    }
    double promedio = lineas > 0 ? (double) palabras / lineas : 0;
    int max = frecuencia.values().stream().max(Integer::compare).orElse(0);
        List<String> masFrecuentes = new ArrayList<>();
        for (var e : frecuencia.entrySet())
            if (e.getValue() == max) masFrecuentes.add(e.getKey());

        System.out.println("===== RESULTADOS =====");
        System.out.println("Archivo: " + archivo.getName());
        System.out.println("Total líneas: " + lineas);
        System.out.println("Total palabras: " + palabras);
        System.out.println("Total caracteres (sin saltos): " + caracteres);
        System.out.printf("Promedio de palabras por línea: %.2f%n", promedio);
        System.out.println("Palabras más frecuentes (" + max + " apariciones): " + masFrecuentes);
    }
}
    
      
 
    
        
        
}

