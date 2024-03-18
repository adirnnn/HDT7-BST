package uvg.edu.gt;

// Representa un nodo en un árbol binario de búsqueda
public class TreeNode {
    String key; // Clave (palabra en inglés)
    String value; // Valor (traducción en español)
    TreeNode left, right; // Referencias a los lados izquierdo y derecho del nodo

    public TreeNode(String key, String value) {
        this.key = key; // Inicializa la clave del nodo con la clave dada
        this.value = value; // Inicializa el valor del nodo con el valor dado
        left = right = null; // Inicializa las referencias como nulas
    }
}