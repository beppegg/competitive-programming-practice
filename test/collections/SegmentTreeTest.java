package collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentTreeTest {

    @Test
    void testBuildAndQuery() {
        // given
        Integer[] baseArray = {18, 17, 13, 19, 15, 11, 20};

        // when
        SegmentTree<Integer> segmentTree = new SegmentTree<>(baseArray, null);

        // then
        assertThat(segmentTree.query(1, 3)).isEqualTo(2);
        assertThat(segmentTree.query(4, 6)).isEqualTo(5);
    }
}