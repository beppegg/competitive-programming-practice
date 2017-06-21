package collections;

import java.util.Arrays;

/**
 * Segment tree implementation.
 */
public class SegmentTreeRangeSumQuery {

    private final int[] segmentTree;
    private final int[] pending;
    private final int[] base;

    public SegmentTreeRangeSumQuery(int[] array) {
        this(array, true);
    }

    public SegmentTreeRangeSumQuery(int[] array, boolean safeCopy) {
        this.base = safeCopy ? Arrays.copyOf(array, array.length) : array;
        final int segmentTreeDimension = 2 * (int) Math.pow(2, Math.ceil(Math.log(array.length) / Math.log(2))) - 1;
        this.pending = new int[segmentTreeDimension];
        this.segmentTree =
                build(new int[segmentTreeDimension], array, 1, 0, array.length - 1);
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
        if (pending[position] != 0) {
            lazyUpdate(position, left, right, pending[position]);
            pending[position] = 0;
        }

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

    public void update(int position, int relativeValue) {
        this.updatePosition(1, relativeValue, 0, base.length - 1, position, position);
    }

    public void update(int from, int to, int relativeValue) {
        this.updatePosition(1, relativeValue, 0, base.length - 1, from, to);
    }

    private void updatePosition(int position, int relativeValue, int left, int right, int targetFrom, int targetTo) {
        if (pending[position] != 0) {
            lazyUpdate(position, left, right, pending[position]);
            pending[position] = 0;
        }

        if (left == right && targetFrom <= left && right <= targetTo) {
            // reached a leaf, update the value
            base[left] += relativeValue;
            segmentTree[position] += relativeValue;
            pending[position] = 0;
        } else if (targetFrom <= left && right <= targetTo) {
            // this node is completly contained in target range: we can pospone updates
            lazyUpdate(position, left, right, relativeValue);
        } else if (Math.max(targetFrom, left) <= Math.min(targetTo, right)) {
            // update subtrees
            int middle = left + (right - left) / 2;
            final int leftPosition = leftSubtree(position);
            final int rightPosition = rightSubtree(position);

            updatePosition(leftPosition, relativeValue, left, middle, targetFrom, targetTo);
            updatePosition(rightPosition, relativeValue, middle + 1, right, targetFrom, targetTo);

            int sumLeft = segmentTree[leftPosition];
            int sumRight = segmentTree[rightPosition];

            segmentTree[position] = sumLeft + sumRight;
        }
    }

    private void lazyUpdate(int position, int left, int right, int amount) {
        segmentTree[position] += (right - left + 1) * amount;
        if (left != right) {
            pending[leftSubtree(position)] += amount;
            pending[rightSubtree(position)] += amount;
        }

    }

    public int query(int minPos, int maxPos) {
        return query(1, 0, base.length - 1, minPos, maxPos);
    }

    private int leftSubtree(int index) {
        return index * 2;
    }

    private int rightSubtree(int index) {
        return index * 2 + 1;
    }
}
