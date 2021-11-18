import datastructures.lists.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5);
        list.remove(4);
        list.set(0, 25);
        list.add(25);
        System.out.println(list);
        list.removeAll(25);
        System.out.println(list);
        System.out.println(List.of(1,2,3).hashCode() == List.of(1,3,2).hashCode());
    }
}
