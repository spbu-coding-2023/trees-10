package trees

import nodes.RBNode.Colors
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RBTreeTest {
    private lateinit var t: RBTree<Int, Int>

    @BeforeEach
    fun setup() {
        t = RBTree()
    }

    @Test
    fun `test insertion into void tree`() {
        t.insert(1, 0)
        assertEquals(1, t.getRoot()?.key)
    }

    @Test
    fun `test insertion of three elements to the right`() {
        t.insert(1, 0)
        t.insert(2, 0)
        t.insert(3, 0)

        assertEquals(2, t.getRoot()?.key)
        assertEquals(1, t.getRoot()?.left?.key)
        assertEquals(3, t.getRoot()?.right?.key)
        assertEquals(Colors.RED, t.getRoot()?.left?.color)
        assertEquals(Colors.RED, t.getRoot()?.right?.color)
    }

    @Test
    fun `test insertion of three elements to the left`() {
        t.insert(3, 0)
        t.insert(2, 0)
        t.insert(1, 0)

        assertEquals(2, t.getRoot()?.key)
        assertEquals(1, t.getRoot()?.left?.key)
        assertEquals(3, t.getRoot()?.right?.key)
        assertEquals(Colors.RED, t.getRoot()?.left?.color)
        assertEquals(Colors.RED, t.getRoot()?.right?.color)
    }

    @Test
    fun `test parent is right child and node has red uncle`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(35, 0)

        assertEquals(20, t.getRoot()?.key)
        assertEquals(10, t.getRoot()?.left?.key)
        assertEquals(30, t.getRoot()?.right?.key)
        assertEquals(35, t.getRoot()?.right?.right?.key)
        assertEquals(Colors.BLACK, t.getRoot()?.left?.color)
        assertEquals(Colors.BLACK, t.getRoot()?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.right?.right?.color)
    }

    @Test
    fun `test parent is left child and node has red uncle`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)

        assertEquals(20, t.getRoot()?.key)
        assertEquals(10, t.getRoot()?.left?.key)
        assertEquals(30, t.getRoot()?.right?.key)
        assertEquals(5, t.getRoot()?.left?.left?.key)
        assertEquals(Colors.BLACK, t.getRoot()?.left?.color)
        assertEquals(Colors.BLACK, t.getRoot()?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.left?.left?.color)
    }

    @Test
    fun `test parent is right child and node has not red uncle and node is left child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(40, 0)
        t.insert(35, 0)

        assertEquals(20, t.getRoot()?.key)
        assertEquals(10, t.getRoot()?.left?.key)
        assertEquals(35, t.getRoot()?.right?.key)
        assertEquals(30, t.getRoot()?.right?.left?.key)
        assertEquals(40, t.getRoot()?.right?.right?.key)
        assertEquals(Colors.BLACK, t.getRoot()?.left?.color)
        assertEquals(Colors.BLACK, t.getRoot()?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.right?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.right?.left?.color)
    }

    @Test
    fun `test parent is left child and node has not red uncle and node is right child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)
        t.insert(7, 0)

        assertEquals(20, t.getRoot()?.key)
        assertEquals(7, t.getRoot()?.left?.key)
        assertEquals(30, t.getRoot()?.right?.key)
        assertEquals(10, t.getRoot()?.left?.right?.key)
        assertEquals(5, t.getRoot()?.left?.left?.key)
        assertEquals(Colors.BLACK, t.getRoot()?.left?.color)
        assertEquals(Colors.BLACK, t.getRoot()?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.left?.right?.color)
        assertEquals(Colors.RED, t.getRoot()?.left?.left?.color)
    }


    @Test
    fun `test deletion of node without any children`() {
        t.insert(1, 0)
        t.insert(2, 0)
        t.insert(3, 0)

        t.delete(3)

        assertEquals(null, t.getRoot()?.right)
    }

    @Test
    fun `test deletion of node with right child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(35, 0)

        t.delete(30)

        assertEquals(35, t.getRoot()?.right?.key)
    }

    @Test
    fun `test deletion of node with left child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(25, 0)

        t.delete(30)

        assertEquals(25, t.getRoot()?.right?.key)
    }

    @Test
    fun `test deletion of node with both children`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(25, 0)
        t.insert(35, 0)

        t.delete(30)

        assertEquals(35, t.getRoot()?.right?.key)
        assertEquals(25, t.getRoot()?.right?.left?.key)
        assertEquals(null, t.getRoot()?.right?.right?.key)
    }
}
