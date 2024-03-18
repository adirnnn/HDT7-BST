package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class App 
{
    public static void main(String[] args) {
        BinarySearchTree dictionary = new BinarySearchTree();

        // Leer el archivo diccionario.txt y construir el árbol binario de búsqueda
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/gt/resources/diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String key = st.nextToken().trim().toLowerCase();
                String value = st.nextToken().trim().toLowerCase();
                dictionary.insert(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir el árbol en orden
        System.out.println("Diccionario ordenado en inglés:");
        dictionary.inorder(dictionary.root);

        // Traducir el archivo texto.txt
        System.out.println("\nTraducción del archivo texto.txt:");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/gt/resources/texto.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Línea original: " + line);
                StringBuilder translatedLine = new StringBuilder();
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    String word = st.nextToken().toLowerCase();
                    String translation = dictionary.translate(word);
                    if (translation.startsWith("*") && translation.endsWith("*")) {
                        translatedLine.append("*").append(word).append("*").append(" ");
                    } else {
                        translatedLine.append(translation).append(" ");
                    }
                }
                System.out.println("Línea traducida: " + translatedLine.toString());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
