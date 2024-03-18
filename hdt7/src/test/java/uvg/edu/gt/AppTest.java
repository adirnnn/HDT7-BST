package uvg.edu.gt;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private BinarySearchTree dictionary;

    @Before
    public void setUp() {
        dictionary = new BinarySearchTree();
        dictionary.insert("house", "casa");
        dictionary.insert("dog", "perro");
    }

    @Test
    public void testInsert() {
        // Verificar que los nodos se hayan insertado correctamente
        assertEquals("casa", dictionary.root.value);
        assertEquals("perro", dictionary.root.left.value);
    }

    @Test
    public void testTranslateExisting() {
        // Verificar traducción de asociaciones existentes
        assertEquals("casa ", dictionary.translate("house"));
        assertEquals("perro ", dictionary.translate("dog"));
    }

    @Test
    public void testTranslateNonExisting() {
        // Verificar que la traducción de asociaciones inexistentes devuelva entre asteriscos
        assertEquals("*bird* ", dictionary.translate("bird"));
        assertEquals("*apple* ", dictionary.translate("apple"));
        assertEquals("*car* ", dictionary.translate("car"));
    }
}