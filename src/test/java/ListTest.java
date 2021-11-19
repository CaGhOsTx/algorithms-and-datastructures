import datastructures.lists.ArrayList;
import datastructures.lists.DoublyLinkedList;
import datastructures.lists.LinkedList;
import datastructures.lists.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ListTest {

    @DataProvider (name = "data")
    Object[][] getdata() {
        return new Object[][] {
                {new ArrayList<>()},
                {new LinkedList<>()},
                {new DoublyLinkedList<>()}
        };
    }

    @Test(dataProvider = "data")
    public void add1element(List<Object> list) {
        list.add(1);
        Assert.assertEquals(list.toString(), "[1]");
    }
}
