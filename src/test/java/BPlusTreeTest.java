import org.junit.jupiter.api.*;

public class BPlusTreeTest {
    private BPlusTree tree;

    @BeforeEach
    void init() {
        tree = new BPlusTree(3);
    }

    @Test
    void illegalDegreeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BPlusTree(2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BPlusTree(7));
    }

    @Test
    void treeWithZeroNodeTest() {
        Assertions.assertEquals("{[]}", tree.toString());
    }

    @Test
    void treeWithOneNodeTest() {
        tree.insert(1);
        Assertions.assertEquals("{[1]}", tree.toString());
    }

    @Test
    void treeWithManyNodesTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        Assertions.assertEquals("{[5]} -> {[3], [7]} -> {[1, 2], [3, 4]}, {[5, 6], [7]}", tree.toString());
    }

    @Test
    void deleteLastNodeTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.delete(8);
        Assertions.assertEquals("{[5]} -> {[3], [7]} -> {[1, 2], [3, 4]}, {[5, 6], [7]}", tree.toString());
    }

    @Test
    void deleteMidNodeTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.delete(6);
        Assertions.assertEquals("{[5]} -> {[3], [7]} -> {[1, 2], [3, 4]}, {[5], [7, 8]}", tree.toString());
    }

    @Test
    void deleteSeveralNodeTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.delete(1);
        tree.delete(2);
        Assertions.assertEquals("{[5, 7]} -> {[3, 4], [5, 6], [7, 8]}", tree.toString());
    }

    @Test
    void deleteAllNodeTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.delete(1);
        tree.delete(2);
        tree.delete(3);
        tree.delete(4);
        tree.delete(5);
        Assertions.assertEquals("{[]}", tree.toString());

    }

    @Test
    void deleteNonExistingNodeTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.delete(10);
        Assertions.assertEquals("{[3, 5]} -> {[1, 2], [3, 4], [5]}", tree.toString());
    }

    @Test
    void insertNotInOrderTest() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(5);
        tree.insert(4);
        Assertions.assertEquals("{[3, 5]} -> {[1, 2], [3, 4], [5]}", tree.toString());
    }

}
