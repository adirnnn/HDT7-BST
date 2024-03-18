package uvg.edu.gt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinarySearchTree {
    public TreeNode root;

    public BinarySearchTree() {
        root = null; // Inicializa el nodo raíz como nulo al crear un nuevo árbol binario de búsqueda
    }

    // Método para insertar una nueva asociación en el árbol
    public void insert(String key, String value) {
        root = insertRec(root, key.toLowerCase(), value.toLowerCase()); // Inserta la asociación en minúsculas para garantizar la comparación sin importar de si es mayúsculas o minúsculas
    }

    // Método auxiliar recursivo para insertar una nueva asociación en el árbol
    public TreeNode insertRec(TreeNode root, String key, String value) {
        // Si el nodo actual es nulo, crea un nuevo nodo con la clave y el valor dados y lo devuelve como raíz
        if (root == null) {
            root = new TreeNode(key, value);
            return root;
        }

        // Compara la clave con la clave del nodo actual y decide en qué subárbol insertar la nueva asociación
        if (key.compareTo(root.key) < 0)
            root.left = insertRec(root.left, key, value); // Si la clave es menor que la clave del nodo actual, inserta en el subárbol izquierdo
        else if (key.compareTo(root.key) > 0)
            root.right = insertRec(root.right, key, value); // Si la clave es mayor que la clave del nodo actual, inserta en el subárbol derecho

        return root;
    }

    // Método para recorrer el árbol en orden y imprimir las asociaciones clave-valor
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left); // Recorre el subárbol izquierdo
            System.out.println("(" + root.key + ", " + root.value + ")"); // Imprime la asociación del nodo actual
            inorder(root.right); // Recorre el subárbol derecho
        }
    }

    // Método para traducir una oración dada utilizando las asociaciones almacenadas en el árbol
    public String translate(String sentence) {
        StringBuilder translatedSentence = new StringBuilder(); // StringBuilder para construir la oración traducida
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b"); // Patrón para buscar las palabras en la oración
        Matcher matcher = pattern.matcher(sentence.toLowerCase()); // Matcher para buscar coincidencias en la oración en minúsculas

        // Itera sobre todas las palabras encontradas en la oración
        while (matcher.find()) {
            String word = matcher.group(1); // Obtiene la palabra actual
            String translation = translateRec(root, word); // Traduce la palabra actual
            // Agrega la traducción (o la palabra original entre asteriscos si no se encuentra en el diccionario) a la oración traducida
            if (translation.startsWith("*") && translation.endsWith("*")) {
                translatedSentence.append("*").append(word).append("*").append(" ");
            } else {
                translatedSentence.append(translation).append(" ");
            }
        }

        return translatedSentence.toString(); // Devuelve la oración traducida como una cadena
    }    

    // Método auxiliar recursivo para traducir una palabra dada utilizando las asociaciones almacenadas en el árbol
    public String translateRec(TreeNode root, String word) {
        // Si el nodo actual es nulo, devuelve la palabra original entre asteriscos
        if (root == null)
            return "*" + word + "*";

        // Compara la palabra con la clave del nodo actual y devuelve la traducción si coincide
        if (word.equalsIgnoreCase(root.key))
            return root.value;

        // Decide en qué subárbol buscar la traducción y llama recursivamente al método
        int compareResult = word.compareToIgnoreCase(root.key);
        if (compareResult < 0)
            return translateRec(root.left, word); // Busca en el subárbol izquierdo
        else
            return translateRec(root.right, word); // Busca en el subárbol derecho
    }
}