package collections;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Segment tree implementation.
 */
public class SegmentTreeRangeMinimumQuery<T extends Comparable> {

    private final T[] array;

    private final Comparator<T> comparator;

    private final int[] segmentTree;

    public SegmentTreeRangeMinimumQuery(T[] array, Comparator<T> comparator) {
        this.array = Arrays.copyOf(array, array.length);
        this.comparator = comparator != null ? comparator : Comparator.naturalOrder();
        this.segmentTree = new int[4 * array.length];
        build(this.segmentTree, this.array, this.comparator, 1, 0, array.length - 1);
    }

    private int[] build(int[] segmentTree, T[] array, Comparator<T> comparator, int position, int left, int right) {
        // minimum of portions with length one is the same element
        if (left == right) {
            segmentTree[position] = left;
        } else {
            // build left subtree
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            build(segmentTree, array, comparator, leftPosition, left, middle);
            build(segmentTree, array, comparator, rightPosition, middle + 1, right);

            int minPosLeft = segmentTree[leftPosition];
            int minPosRight = segmentTree[rightPosition];

            if (comparator.compare(array[minPosLeft], array[minPosRight]) <= 0) {
                segmentTree[position] = minPosLeft;
            } else {
                segmentTree[position] = minPosRight;
            }
        }

        return segmentTree;
    }

    /**
     * Range Minimum Query
     */
    private int query(int position, int left, int right, int targetLeft, int targetRight) {
        if (targetRight <= left || right < targetLeft) {
            // completely outside target range
            return -1;
        }
        if (targetLeft <= left && right <= targetRight) {
            // contains target range
            return segmentTree[position];
        }

        int middle = left + (right - left) / 2;
        int minPosLeft = query(leftSubtree(position), left, middle, targetLeft, targetRight);
        int minPosRight = query(rightSubtree(position), middle + 1, right, targetLeft, targetRight);

        final int minPos;
        if (-1 == minPosLeft) {
            minPos = minPosRight;
        } else if (-1 == minPosRight) {
            minPos = minPosLeft;
        } else if (comparator.compare(array[minPosLeft], array[minPosRight]) <= 0) {
            minPos = minPosLeft;
        } else {
            minPos = minPosRight;
        }

        return minPos;
    }

    public void update(int position, T value) {
        this.array[position] = value;
        this.updatePosition(1, 0, array.length - 1, position, position);
    }

    public void update(int from, int to, T value) {
        Arrays.fill(this.array, from, to + 1, value);
        this.updatePosition(1, 0, array.length - 1, from, to);
    }

    private void updatePosition(int position, int left, int right, int targetFrom, int targetTo) {
        if (left == right && targetFrom <= left && right <= targetTo) {
            return;
        } else if (Math.max(targetFrom, left) <= Math.min(targetTo, right)) {
            // update subtrees
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            updatePosition(leftPosition, left, middle, targetFrom, targetTo);
            updatePosition(rightPosition, middle + 1, right, targetFrom, targetTo);

            int minPosLeft = segmentTree[leftPosition];
            int minPosRight = segmentTree[rightPosition];

            if (comparator.compare(array[minPosLeft], array[minPosRight]) <= 0) {
                segmentTree[position] = minPosLeft;
            } else {
                segmentTree[position] = minPosRight;
            }
        }
    }

    public int query(int minPos, int maxPos) {
        return query(1, 1, array.length - 1, minPos, maxPos);
    }

    private int leftSubtree(int index) {
        return index * 2;
    }

    private int rightSubtree(int index) {
        return index * 2 + 1;
    }
}
