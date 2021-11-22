package factories;

import datastructures.lists.ArrayList;
import org.testng.annotations.Factory;
import tests.ListTest;
import tests.QueueTest;
import tests.StackTest;

public class ALFactory {
    @Factory
    Object[] getList() {
        return new Object[] {new ListTest(new ArrayList<>())};
    }

    @Factory
    Object[] getQueue() {
        return new Object[] {new QueueTest(new ArrayList<>())};
    }

    @Factory
    Object[] getStack() {
        return new Object[] {new StackTest(new ArrayList<>())};
    }
}
