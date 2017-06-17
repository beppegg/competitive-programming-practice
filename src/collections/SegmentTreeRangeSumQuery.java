package collections;

import java.util.Arrays;

/**
 * Segment tree implementation.
 */
public class SegmentTreeRangeSumQuery {


    private final int[] segmentTree;
    private final int elements;

    public SegmentTreeRangeSumQuery(int[] array) {
        this.elements = array.length - 1;
        this.segmentTree =
                build(new int[4 * array.length], array, 1, 0, array.length - 1);
    }

    private int[] build(int[] segmentTree, int[] array, int position, int left, int right) {
        // minimum of portions with length one is the same element
        if (left == right) {
            segmentTree[position] = array[left];
        } else {
            // build left subtree
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            build(segmentTree, array, leftPosition, left, middle);
            build(segmentTree, array, rightPosition, middle + 1, right);

            int sumLeft = segmentTree[leftPosition];
            int sumRight = segmentTree[rightPosition];

            segmentTree[position] = sumLeft + sumRight;
        }

        return segmentTree;
    }

    /**
     * Range Minimum Query
     */
    private int query(int position, int left, int right, int targetLeft, int targetRight) {
        if (targetRight < left || right < targetLeft) {
            // completely outside target range
            return 0;
        }
        if (targetLeft <= left && right <= targetRight) {
            // contains target range
            return segmentTree[position];
        }

        int middle = left + (right - left) / 2;
        int sumLeft = query(leftSubtree(position), left, middle, targetLeft, targetRight);
        int sumRight = query(rightSubtree(position), middle + 1, right, targetLeft, targetRight);

        return sumLeft + sumRight;
    }

    public void update(int position, int value) {
        this.updatePosition(1, value, 0, elements, position, position);
    }

    public void update(int from, int to, int value) {
        this.updatePosition(1, value, 0, elements, from, to);
    }

    private void updatePosition(int position, int value, int left, int right, int targetFrom, int targetTo) {
        if (left == right && targetFrom <= left && right <= targetTo) {
            segmentTree[position] = value;
        } else if (Math.max(targetFrom, left) <= Math.min(targetTo, right)) {
            // update subtrees
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            updatePosition(leftPosition, value, left, middle, targetFrom, targetTo);
            updatePosition(rightPosition, value, middle + 1, right, targetFrom, targetTo);

            int sumLeft = segmentTree[leftPosition];
            int sumRight = segmentTree[rightPosition];

            segmentTree[position] = sumLeft + sumRight;
        }
    }

    public int query(int minPos, int maxPos) {
        return query(1, 0, elements, minPos, maxPos);
    }

    private int leftSubtree(int index) {
        return index * 2;
    }

    private int rightSubtree(int index) {
        return index * 2 + 1;
    }
}
