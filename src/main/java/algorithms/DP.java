package algorithms;

import java.util.List;

public class DP {

    /**
     * Returns a sublist view with the largest sum.(mutable!)<br/>
     * If we iterate through a collection and keep track of the sum so far, if it is negative,
     * it makes no sense to keep the previous elements as it is already negative.
     * So a valid left pointer for a sub-list would be a positive element, and from it, as long as the sum is non-negative
     * the right pointer should be updated.
     * The reason to keep track of the global sum is to not re-update the left/right pointers for smaller sum sub-lists.
     * The algorithm works as follows:
     * <ol>
     *     <li>add current element to the local sum</li>
     *     <li>keep track of maximum element</li>
     *     <li>if the <B>local sum</B> is negative, reset it to 0</li>
     *     <li>if the <B>local sum</B> is greater than the <B>global sum</B>, set <B>global sum</B> to <B>local sum</B></li>
     *     <li>auxiliary left is updated every time current element is negative, true left (to aux left) and right (to current index + 1) update when global maximum increases</li>
     *     <li>if the maximum element is non-positive at the end, return a view of itself</li>
     * </ol>
     * @param list list to be used.
     * @return view of sublist with the maximum sum.(mutable!)
     */
    public static List<Integer> KadaneMaxSumSubList(List<Integer> list) {
        if(list.isEmpty())
            return list.subList(0,0);
        int globalMax = 0, localMax = 0, left = 0, right = 0, auxLeft = 0, maxIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            localMax += list.get(i);
            if(localMax < 0) {
                auxLeft = i + 1;
                localMax = 0;
            }
            if(localMax > globalMax) {
                right = i + 1;
                left = auxLeft;
                globalMax = localMax;
            }
            maxIndex = list.get(i) > list.get(maxIndex) ? i : maxIndex;
        }
        return list.get(maxIndex) <= 0 ? list.subList(maxIndex, maxIndex + 1) : list.subList(left, right);
    }
}
