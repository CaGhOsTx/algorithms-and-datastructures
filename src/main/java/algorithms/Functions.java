package algorithms;

import java.util.*;

public class Functions {

    public Set<List<Integer>> twoSum(List<Integer> space, int dimension) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<List<Integer>> points = new HashSet<>();
        for (int i = 0; i < space.size(); i++) {
            map.computeIfAbsent(space.get(i), j -> map.put(j, space.get(j)));
            if(map.containsKey(-space.get(i)) && map.get(-space.get(i)) != i)
                points.add(List.of(i, map.get(-space.get(i))));
        }
        return points;
    }

    public static int zigzag(int x, int bound) {
        return - Math.abs(x % (bound << 1) - bound) + bound;
    }
}
