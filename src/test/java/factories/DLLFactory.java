package factories;

import datastructures.lists.DoublyLinkedList;
import org.testng.annotations.Factory;
import tests.ListTest;
import tests.QueueTest;
import tests.StackTest;

public class DLLFactory {

    @Factory
    Object[] getList() {
        return new Object[]{new ListTest(new DoublyLinkedList<>())};
    }

    @Factory
    Object[] getQueue() {
        return new Object[] {new QueueTest(new DoublyLinkedList<>())};
    }

    @Factory
    Object[] getStack() {
        return new Object[] {new StackTest(new DoublyLinkedList<>())};
    }
}
