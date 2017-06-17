package collections;

import java.util.Arrays;

/**
 * Segment tree implementation.
 */
public class SegmentTreeRangeSumQuery {

    private final int[] array;

    private final int[] segmentTree;

    public SegmentTreeRangeSumQuery(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.segmentTree =
                build(new int[4 * array.length], this.array, 1, 0, array.length - 1);
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
        this.array[position] = value;
        this.updatePosition(1, 0, array.length - 1, position);
    }

    private void updatePosition(int position, int left, int right, int targetPosition) {
        if (left == targetPosition && targetPosition == right) {
            segmentTree[position] = array[targetPosition];
        } else if (left <= targetPosition && targetPosition <= right) {
            // update subtrees
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            updatePosition(leftPosition, left, middle, targetPosition);
            updatePosition(rightPosition, middle + 1, right, targetPosition);

            int sumLeft = segmentTree[leftPosition];
            int sumRight = segmentTree[rightPosition];

            segmentTree[position] = sumLeft + sumRight;
        }
    }

    public int query(int minPos, int maxPos) {
        return query(1, 0, array.length - 1, minPos, maxPos);
    }

    private int leftSubtree(int index) {
        return index * 2;
    }

    private int rightSubtree(int index) {
        return index * 2 + 1;
    }
}
