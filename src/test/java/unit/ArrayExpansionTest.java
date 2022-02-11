package unit;

import datastructures.List;
import datastructures.lists.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

public class ArrayExpansionTest {

    List<Object> list = new ArrayList<>(4);
    Field f;

    @BeforeMethod
    public void setUp() throws NoSuchFieldException {
        f = list.getClass().getDeclaredField("elements");
        f.setAccessible(true);
    }

    @Test
    void testInitialSize() throws IllegalAccessException {
        list.addAll(1,2,3,4);
        Assert.assertEquals(((Object[]) f.get(list)).length, 4);
    }

    @Test(dependsOnMethods = "testInitialSize")
    void testArrayExpansion() throws IllegalAccessException {
        list.add(1);
        Assert.assertEquals(((Object[]) f.get(list)).length, 8);
        list.addAll(1,1,1,1);
        Assert.assertEquals(((Object[]) f.get(list)).length, 16);
    }

    @Test(dependsOnMethods = "testArrayExpansion")
    void testArrayCompression() throws IllegalAccessException {
        list.removeAll(3);
        Assert.assertEquals(((Object[]) f.get(list)).length, 8);
        list.clear();
        Assert.assertEquals(((Object[]) f.get(list)).length, 4);
    }
}
