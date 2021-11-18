import datastructures.lists.ArrayList;
import datastructures.lists.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

public class ArrayListTest {
    List<Integer> list;
    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.addAll(1,2,3,4,5);
    }

    @Test
    void addTest() {
        list.add(1);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 1]", list.toString());
    }

    @Test
    void insertTest() {
        list.insert(2,7);
        Assertions.assertEquals("[1, 2, 7, 3, 4, 5]", list.toString());
    }

    @Test
    void clearTest() {
        list.clear();
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void equalsTest() {
        Assertions.assertNotEquals(list, List.of(1));
        Assertions.assertNotEquals(list, null);
        Assertions.assertNotEquals(list, new Object());
        Assertions.assertEquals(List.of(1,2,3), List.of(1,2,3));
    }

    @Nested
    class InnerArrayTester {
        Field f = ArrayList.class.getDeclaredField("elements");;

        InnerArrayTester() throws NoSuchFieldException, IllegalAccessException {
            f.setAccessible(true);
        }

        @BeforeEach
        void setUp() {
            list = new ArrayList<>(8);
            list.addAll(1,2,3,4,5,6,7,8,9);
        }

        @Test
        void initialSizeTest() throws NoSuchFieldException, IllegalAccessException {
            list.clear();
            Assertions.assertEquals(8, ((Object[]) f.get(list)).length);
        }

        @Test
        void expansionTest() throws IllegalAccessException {
            Assertions.assertEquals(16, ((Object[]) f.get(list)).length);
        }

        @Test
        void compressionTest() throws IllegalAccessException {
            list.addAll(1,2,3,4,5,6,7,8);
            list.remove(0);
            Assertions.assertEquals(16, ((Object[]) f.get(list)).length);
        }

    }

    @Test
    void hashCodeTest() {
        Assertions.assertNotEquals(list.hashCode(), List.of(1,2,4,3,5).hashCode());
        Assertions.assertEquals(list.hashCode(), List.of(1,2,3,4,5).hashCode());

    }

    @Test
    void removeTest() {
        list.remove(3);
        Assertions.assertEquals("[1, 2, 3, 5]", list.toString());
    }

    @Test
    void setTest() {
        list.set(2,25);
        Assertions.assertEquals("[1, 2, 25, 4, 5]", list.toString());
    }

    @Test
    void getTest() {
        Assertions.assertEquals(3, list.get(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void containsTest(int p) {
        if(p > 0 && p < 6)
            Assertions.assertTrue(list.contains(p));
        else
            Assertions.assertFalse(list.contains(p));
    }

    @Test
    void removeAllTest() {
        list.add(2);
        list.add(3);
        list.removeAll(2);
        Assertions.assertEquals("[1, 3, 4, 5, 3]", list.toString());
    }

    @Test
    void indexOfTest() {
        Assertions.assertEquals(1, list.indexOf(2));
    }

    @Test
    void sizeTest() {
        Assertions.assertEquals(5, list.size());
    }

    @Test
    void addAllTest() {
        list.addAll(6,7,8,9,10);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());
    }

    @Nested
    class OutOfBoundsTester {

        @ParameterizedTest
        @ValueSource(ints = {-5, -1, 5, 6})
        void setOutOfBoundsTest(int index) {
            Assertions.assertThrows(NullPointerException.class, () -> list.set(index, 5));
        }

        @ParameterizedTest
        @ValueSource(ints = {-5, -1, 5, 6})
        void removeOutOfBoundsTest(int index) {
            Assertions.assertThrows(NullPointerException.class, () -> list.remove(index));
        }

        @ParameterizedTest
        @ValueSource(ints = {-5, -1, 5, 6})
        void getOutOfBoundsTest(int index) {
            Assertions.assertThrows(NullPointerException.class, () -> list.get(index));
        }
    }
}
