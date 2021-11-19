import datastructures.lists.CircularLinkedList;
import datastructures.lists.List;

import java.util.stream.IntStream;

public class Main2 {
    public static void main(String[] args) {
        List<String> list = new CircularLinkedList<>();
        list.addAll("North", "East", "South", "West");
        var it = list.iterator();
        IntStream.range(0,16).forEach(i -> System.out.println(i + " " + it.next()));
    }
}
