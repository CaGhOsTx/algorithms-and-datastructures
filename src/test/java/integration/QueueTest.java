package integration;

import datastructures.Queue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class QueueTest {
    Queue<Object> q;

    @BeforeMethod
    public void setUp() {
        q.clear();
    }

    public QueueTest(Queue<Object> q) {
        this.q = q;
    }

    @Test
    public void testClear() {
        q.enqueue(1);
        q.enqueue(2);
        q.clear();
        assertTrue(q.isEmpty());
    }

    @Test
    public void testEnqueue1() {
        q.enqueue(1);
        assertEquals(q.toString(), "[1]");
    }

    @Test
    public void testEnqueue2() {
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(q.toString(), "[1, 2]");
    }

    @Test
    public void testSizeIncreaseOnEnqueue() {
        q.enqueue(1);
        assertEquals(q.size(), 1);
    }

    @Test
    public void testEnqueueDequeueEqual() {
        q.enqueue(1);
        q.enqueue(1);
        q.dequeue();
        q.dequeue();
        assertEquals(q.toString(), "[]");
    }

    @Test
    public void testISEOnRemovingWhenEmpty() {
        q.enqueue(1);
        q.dequeue();
        assertThrows(IllegalStateException.class, () -> q.dequeue());
    }

    @Test
    public void testPeek() {
        q.enqueue(1);
        assertEquals(q.peek(), 1);
    }

    @Test
    public void testPeekWhenLarger() {
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(q.peek(), 1);
    }

    @Test
    public void testISEWhenPeekingEmpty() {
        assertThrows(IllegalStateException.class, () -> q.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(q.isEmpty());
        q.enqueue(1);
        q.dequeue();
        assertTrue(q.isEmpty());
    }

    @DataProvider(name = "data")
    Object[][] data() {
        return new Object[][] {{1},{2},{3},{4}};
    }
    @Test(dataProvider = "data")
    public void testContains(int i) {
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        if(i < 4)
            assertTrue(q.contains(i));
        else
            assertFalse(q.contains(i));
    }

    @Test
    public void testSizeDecreaseOnDeque() {
        q.enqueue(1);
        q.enqueue(1);
        q.dequeue();
        assertEquals(q.size(), 1);
    }

    @Test
    public void testDequeue1() {
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        assertEquals(q.toString(), "[2]");
    }
}
