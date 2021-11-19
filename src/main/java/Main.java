import datastructures.DataStructure;
import datastructures.deques.queues.Queue;
import datastructures.lists.DoublyLinkedList;
import datastructures.lists.List;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Queue<String> queue = new DoublyLinkedList<>();
        try(var sc = new Scanner(System.in)) {
            while(sc.hasNext()) {
                var command = sc.nextLine().split("\\s+");
                switch(command[0].toLowerCase()) {
                    case "insert" -> queue.enqueue(command[1]);
                    case "remove" -> {if(!queue.isEmpty()) queue.dequeue();}
                }
                System.out.println(queue);
            }
        }
        List<String> list = DataStructure.convert(queue);
        System.out.println(!list.isEmpty() ? list.get((list.size() - 1) / 2) : "empty");
    }
}
