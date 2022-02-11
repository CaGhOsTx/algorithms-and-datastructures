package tests;

import datastructures.Stack;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StackTest {

    Stack<Object> s;

    public StackTest(Stack<Object> s) {
        this.s = s;
    }

    @BeforeMethod
    public void setUp() {
        s.clear();
    }

    @Test
    public void testPush1() {
        s.push(1);
        assertEquals(s.toString(), "[1]");
    }

    @Test
    public void testPush2() {
        s.push(1);
        s.push(2);
        assertEquals(s.toString(), "[2, 1]");
    }

    @Test
    public void testSizeIncreaseOnPush() {
        s.push(1);
        assertEquals(s.size(), 1);
    }

    @Test
    public void testPop1() {
        s.push(1);
        s.push(2);
        s.pop();
        assertEquals(s.toString(), "[1]");
    }

    @Test
    public void testSizeDecreaseOnPop() {
        s.push(1);
        s.push(2);
        s.pop();
        assertEquals(s.size(), 1);
    }

    @Test
    public void testThrowsWhenPoppingEmpty() {
        assertThrows(IllegalStateException.class, () -> s.pop());
    }

    @Test
    public void testThrowsWhenPeekingEmpty() {
        assertThrows(IllegalStateException.class, () -> s.pop());
    }

    @Test
    public void testPeek1() {
        s.push(1);
        assertEquals(1, s.peek());
    }

    @Test
    public void testPeek2() {
        s.push(1);
        s.push(2);
        assertEquals(2, s.peek());
    }

    @Test(dataProvider = "data")
    public void testContains(int i) {
        s.push(1);
        s.push(2);
        if(i < 3)
            assertTrue(s.contains(i));
        else
            assertFalse(s.contains(i));
    }

    @DataProvider(name = "data")
    Object[][] data() {
        return new Object[][] {{1},{2},{3}};
    }
}
