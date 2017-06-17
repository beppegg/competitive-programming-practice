package collections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentTreeTest {

    @Test
    void testBuildAndQuery() {
        // given
        Integer[] baseArray = {18, 17, 13, 19, 15, 11, 20};
        Integer[] secondArray = {10, 2, 47, 3, 7, 9, 1, 98, 21};

        // when
        SegmentTree<Integer> segmentTree = new SegmentTree<>(baseArray, null);
        SegmentTree<Integer> secondTree = new SegmentTree<>(secondArray, null);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(5);

        assertThat(secondTree.query(1, 7)).isEqualTo(6);
        assertThat(secondTree.query(3, 8)).isEqualTo(6);
    }

    @Test
    void testUpdateAndQuery() {
        // given
        Integer[] baseArray = {18, 17, 13, 19, 15, 11, 20};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(baseArray, null);

        // when
        baseArray[5] = 99;
        segmentTree.updatePosition(5);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(4);
    }
}