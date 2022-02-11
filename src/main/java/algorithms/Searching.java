package algorithms;

import java.util.Comparator;
import java.util.List;

public class Searching {

    public <T extends Comparable<T>> int binarySearch(List<T> list, T element) {
        return binarySearch(list, element, Comparator.naturalOrder());
    }

    public <T> int binarySearch(List<T> list, T element, Comparator<T> comp) {
        return binarySearch(list, element, 0, list.size() - 1, comp);
    }

    public <T> int binarySearch(List<T> list, T element, int l, int r, Comparator<T> comp) {
        if(l <= r) {
            int m = (l + r) >>> 1;
            if(comp.compare(list.get(m), element) < 0)
                return binarySearch(list, element, m + 1, r, comp);
            if(comp.compare(list.get(m), element) > 0)
                return binarySearch(list, element, l, m - 1, comp);
            return m;
        }
        return -1;
    }
}
