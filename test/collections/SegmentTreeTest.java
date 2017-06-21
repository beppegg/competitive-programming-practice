package collections;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentTreeTest {

    @Test
    @Tag("rmq")
    void testBuildAndQueryRMQ() {
        // given
        Integer[] baseArray = {18, 17, 13, 19, 15, 11, 20};
        Integer[] secondArray = {10, 2, 47, 3, 7, 9, 1, 98, 21};

        // when
        SegmentTreeRangeMinimumQuery<Integer> segmentTree = new SegmentTreeRangeMinimumQuery<>(baseArray, null);
        SegmentTreeRangeMinimumQuery<Integer> secondTree = new SegmentTreeRangeMinimumQuery<>(secondArray, null);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(5);

        assertThat(secondTree.query(1, 7)).isEqualTo(6);
        assertThat(secondTree.query(3, 8)).isEqualTo(6);
    }

    @Test
    @Tag("rmq")
    void testUpdateAndQueryRMQ() {
        // given
        Integer[] baseArray = {18, 17, 13, 19, 15, 11, 20};
        SegmentTreeRangeMinimumQuery<Integer> segmentTree = new SegmentTreeRangeMinimumQuery<>(baseArray, null);

        // when
        segmentTree.update(5, 99);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(4);
    }

    @Test
    @Tag("rmq")
    void testRangeUpdateAndQueryRMQ() {
        // given
        SegmentTreeRangeMinimumQuery<Integer> segmentTree =
                new SegmentTreeRangeMinimumQuery<>(new Integer[]{18, 17, 13, 19, 15, 11, 20}, null);

        // when
        segmentTree.update(3, 5, 32);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(6);
    }

    @Test
    @Tag("rsq")
    void testBuildAndQueryRSQ() {
        // given
        int[] baseArray = {18, 17, 13, 19, 15, 11, 20};
        int[] secondArray = {10, 2, 47, 3, 7, 9, 1, 98, 21};

        // when
        SegmentTreeRangeSumQuery segmentTree = new SegmentTreeRangeSumQuery(baseArray);
        SegmentTreeRangeSumQuery secondTree = new SegmentTreeRangeSumQuery(secondArray);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(49);
        assertThat(segmentTree.query(4, 6)).isEqualTo(46);

        assertThat(secondTree.query(1, 7)).isEqualTo(167);
        assertThat(secondTree.query(3, 8)).isEqualTo(139);
    }

    @Test
    @Tag("rsq")
    void testUpdateAndQueryRSQ() {
        // given
        SegmentTreeRangeSumQuery segmentTree = new SegmentTreeRangeSumQuery(new int[]{18, 17, 13, 19, 15, 11, 20});

        // when
        segmentTree.update(5, 21);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(49);
        assertThat(segmentTree.query(4, 6)).isEqualTo(67);
    }

    @Test
    @Tag("rsq")
    void testRangeUpdateAndQueryRSQ() {
        // given
        SegmentTreeRangeSumQuery segmentTree = new SegmentTreeRangeSumQuery(new int[]{18, 17, 13, 19, 15, 11, 20});

        // when
        segmentTree.update(3, 5, 21);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(70);
        assertThat(segmentTree.query(4, 6)).isEqualTo(88);
    }
}