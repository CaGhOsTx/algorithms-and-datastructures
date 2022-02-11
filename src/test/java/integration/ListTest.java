package integration;

import datastructures.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test
public class ListTest {

    List<Object> list;

    public ListTest(List<Object> list) {
        this.list = list;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        list.clear();
    }
    @Test(dependsOnMethods = "testToString")
    public void testAdd1element() {
        list.add(1);
        assertEquals(list.toString(), "[1]");
    }

    @DataProvider(name = "data")
    Object[][] data() {
        return new Object[][] {{0}, {1}, {2}, {3}, {4}, {5}, {6}};
    }

    @Test(dependsOnMethods = {"testAdd1element", "testToString"})
    public void testAddAll() {
        list.addAll(1,2,3,4,5);
        assertEquals(list.toString(), "[1, 2, 3, 4, 5]");
    }

    public void testRemoveWhenEmpty() {
        assertThrows(IllegalStateException.class, () -> list.remove(0));
    }

    @Test(dependsOnMethods = {"testAdd1element", "testAddAll"})
    public void testAdd2Remove3Elements() {
        list.addAll(1,2);
        list.remove(0);
        assertEquals(list.toString(), "[2]");
        list.remove(0);
        assertEquals(list.toString(), "[]");
        assertThrows(IllegalStateException.class, () -> list.remove(0));
    }
    @Test(dependsOnMethods = {"testAddAll", "testToString"})
    public void testSet() {
        list.addAll(1,2,3,4,5);
        list.set(0, "X");
        list.set(2, "X");
        list.set(4, "X");
        assertEquals(list.toString(), "[X, 2, X, 4, X]");
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
        list.remove(0);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(dependsOnMethods = {"testAddAll", "testToString"})
    public void testRemoveAll() {
        list.addAll(1,2,3,4,5,2,3);
        list.removeAll(2);
        assertEquals(list.toString(), "[1, 3, 4, 5, 3]");
    }

    @Test(dataProvider = "data", dependsOnMethods = "testAddAll")
    public void testIndexOf(int i) {
        list.addAll(6,5,4,3,2,1,0);
        assertEquals(list.indexOf(i), list.size() - i - 1);
    }

    @Test
    public void testIndexOfNonElement() {
        list.addAll(1,2,3);
        assertThrows(NoSuchElementException.class, () -> list.indexOf(4));
    }

    @Test
    public void testSizeIncreaseWhenAdding() {
        list.add(1);
        assertEquals(list.size(), 1);
    }

    @Test(dependsOnMethods = "testSizeIncreaseWhenAdding")
    public void testSizeDecreaseWhenRemoving() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(list.size(), 1);
    }

    @Test
    public void testSizeIncreaseWhenAddingAll() {
        list.addAll(1,2,3,4,5);
        assertEquals(list.size(), 5);
    }

    public void testGetMethod() {
        list.addAll(1,2,3,4,5);
        assertEquals(list.get(0), 1);
        assertEquals(list.get(4), 5);
    }

    @Test(dataProvider = "data")
    public void testContains(int i) {
        list.addAll(1,2,3,4,5);
        if(i > 0 && i < 6)
            Assert.assertTrue(list.contains(i));
        else
            Assert.assertFalse(list.contains(i));
    }

    @Test
    public void testClear() {
        list.addAll(1,2,3);
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @Test(dependsOnMethods = {"testSizeIncreaseWhenInserting", "testToString"})
    public void testInsert() {
        list.addAll(1,2,3);
        list.insert(0, "X");
        list.insert(list.size(), "X");
        list.insert(2, "X");
        assertEquals(list.toString(), "[X, 1, X, 2, 3, X]");
    }

    @Test
    public void testSizeIncreaseWhenInserting() {
        list.insert(0, 2);
        assertEquals(list.size(), 1);
    }

    public void testToString() {
        list.addAll(1,2,3,4,5);
        assertEquals(list.toString(), "[1, 2, 3, 4, 5]");
    }
}
