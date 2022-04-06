import org.junit.jupiter.api.Assertions;
import lesson14_ОбзорСредствРазработки.HomeWork.ArrayExample;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ArrayExampleTest {

    ArrayExample arrayExample;

    public ArrayExampleTest() {
        this.arrayExample = new ArrayExample();
    }

    @Test
    @DisplayName("Проверка нормальной работы")
    void testCreateNewArray1() {
        int[] a = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] b = {1, 7};
        Assertions.assertArrayEquals(b, arrayExample.createNewArray(a));
    }

    @Test
    @DisplayName("Проверка на исключение")
    void testCreateNewArray2() {
        int[] a = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        Assertions.assertThrows(RuntimeException.class, () -> arrayExample.createNewArray(a));
    }

    @Test
    @DisplayName("Проверка на то, что если четверка стоит последней")
    void testCreateNewArray3() {
        int[] a = {4, 4, 4, 4, 4, 4, 4, 4, 4};
        int[] b = {};
        Assertions.assertArrayEquals(b, arrayExample.createNewArray(a));
    }

    @Test
    @DisplayName("Проверка на 4 и 1")
    void testCheckFonOneAndFour1() {
        int[] a = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assertions.assertTrue(arrayExample.checkForOneAndFour(a));
    }

    @Test
    @DisplayName("Проверка на 4")
    void testCheckFonOneAndFour2() {
        int[] a = {8, 2, 3, 6, 2, 3, 4, 0, 7};
        Assertions.assertTrue(arrayExample.checkForOneAndFour(a));
    }

    @Test
    @DisplayName("Проверка на 1")
    void testCheckFonOneAndFour3() {
        int[] a = {8, 2, 3, 6, 2, 3, 0, 1, 7};
        Assertions.assertTrue(arrayExample.checkForOneAndFour(a));
    }

    @Test
    @DisplayName("Проверка на отсутствие 4 и 1")
    void testCheckFonOneAndFour4() {
        int[] a = {8, 2, 3, 6, 2, 3, 8, 0, 7};
        Assertions.assertFalse(arrayExample.checkForOneAndFour(a));
    }

}
