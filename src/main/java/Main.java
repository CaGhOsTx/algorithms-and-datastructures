import datastructures.DataStructure;
import datastructures.Queue;
import datastructures.lists.DoublyLinkedList;
import datastructures.List;
import datastructures.lists.LinkedList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> queue = new DoublyLinkedList<>();
        try(var sc = new Scanner(System.in)) {
            while(sc.hasNext()) {
                var command = sc.nextLine().split("\\s+");
                switch(command[0].toLowerCase()) {
                    case "add" -> queue.add(command[1]);
                    case "del" -> {if(!queue.isEmpty()) queue.remove(Integer.parseInt(command[1]));}
                    case "insert" -> queue.insert(Integer.parseInt(command[1]), command[2]);
                }
                System.out.println(queue);
            }
        }
        List<String> list = DataStructure.convert(queue);
        System.out.println(!list.isEmpty() ? list.get((list.size() - 1) / 2) : "empty");
    }
}
