package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) {
        // Se crea un nuevo objeto BinarySearchTree para almacenar el diccionario
        BinarySearchTree dictionary = new BinarySearchTree();

        // Para leer el archivo diccionario.txt y construir el árbol binario de búsqueda
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/gt/resources/diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divide la línea en clave y valor usando la coma como delimitador
                StringTokenizer st = new StringTokenizer(line, ",");
                String key = st.nextToken().trim().toLowerCase(); // Obtiene la palabra en inglés
                String value = st.nextToken().trim().toLowerCase(); // Obtiene el valor traducción en español
                
                // Inserta la asociación en el árbol binario de búsqueda
                dictionary.insert(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error de lectura del archivo
        }

        // Imprime el árbol en orden
        System.out.println("Diccionario ordenado en inglés:");
        dictionary.inorder(dictionary.root);

        // Traduce el archivo texto.txt
        System.out.println("\nTraducción del archivo texto.txt:");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/gt/resources/texto.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Línea original: " + line); // Imprime la línea original del archivo de texto
                StringBuilder translatedLine = new StringBuilder(); // Crea un StringBuilder para construir la línea traducida
                StringTokenizer st = new StringTokenizer(line); // Tokeniza la línea en palabras
                while (st.hasMoreTokens()) {
                    String word = st.nextToken().toLowerCase(); // Obtiene cada palabra en minúsculas
                    String translation = dictionary.translate(word); // Traduce la palabra utilizando el diccionario

                    // Construye la línea traducida
                    if (translation.startsWith("*") && translation.endsWith("*")) {
                        translatedLine.append("*").append(word).append("*").append(" "); // Mantiene la palabra original entre asteriscos si no se encontró en el diccionario
                    } else {
                        translatedLine.append(translation).append(" "); // Agrega la traducción de la palabra
                    }
                }
                System.out.println("Línea traducida: " + translatedLine.toString()); // Imprime la línea traducida
                System.out.println(); // Imprime una línea en blanco para separar las traducciones de las líneas
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error de lectura del archivo
        }
    }
}