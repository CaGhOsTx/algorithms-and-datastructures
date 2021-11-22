package factories;

import datastructures.lists.ArrayList;
import datastructures.lists.LinkedList;
import org.testng.annotations.Factory;
import tests.ListTest;
import tests.QueueTest;
import tests.StackTest;

public class LLFactory {
    @Factory
    Object[] getList() {
        return new Object[] {new ListTest(new LinkedList<>())};
    }

    @Factory
    Object[] getQueue() {
        return new Object[] {new QueueTest(new LinkedList<>())};
    }

    @Factory
    Object[] getStack() {
        return new Object[] {new StackTest(new LinkedList<>())};
    }
}
