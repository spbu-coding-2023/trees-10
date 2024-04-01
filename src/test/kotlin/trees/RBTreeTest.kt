package trees

import nodes.RBNode.Colors
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RBTreeTest {

    @Test
    fun `test insertion into void tree`() {
        val t = RBTree<Int, Int>()
        t.insert(1, 0)
        assertEquals(1, t.root?.key)
    }

    @Test
    fun `test insertion of three elements to the right`() {
        val t = RBTree<Int, Int>()
        t.insert(1, 0)
        t.insert(2, 0)
        t.insert(3, 0)

        assertEquals(2, t.root?.key)
        assertEquals(1, t.root?.left?.key)
        assertEquals(3, t.root?.right?.key)
        assertEquals(Colors.RED, t.root?.left?.color)
        assertEquals(Colors.RED, t.root?.right?.color)
    }

    @Test
    fun `test insertion of three elements to the left`() {
        val t = RBTree<Int, Int>()
        t.insert(3, 0)
        t.insert(2, 0)
        t.insert(1, 0)

        assertEquals(2, t.root?.key)
        assertEquals(1, t.root?.left?.key)
        assertEquals(3, t.root?.right?.key)
        assertEquals(Colors.RED, t.root?.left?.color)
        assertEquals(Colors.RED, t.root?.right?.color)
    }

    @Test
    fun `test parent is right child and node has red uncle`() {
        val t = RBTree<Int, Int>()
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(35, 0)

        assertEquals(20, t.root?.key)
        assertEquals(10, t.root?.left?.key)
        assertEquals(30, t.root?.right?.key)
        assertEquals(35, t.root?.right?.right?.key)
        assertEquals(Colors.BLACK, t.root?.left?.color)
        assertEquals(Colors.BLACK, t.root?.right?.color)
        assertEquals(Colors.RED, t.root?.right?.right?.color)
    }

    @Test
    fun `test parent is left child and node has red uncle`() {
        val t = RBTree<Int, Int>()
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)

        assertEquals(20, t.root?.key)
        assertEquals(10, t.root?.left?.key)
        assertEquals(30, t.root?.right?.key)
        assertEquals(5, t.root?.left?.left?.key)
        assertEquals(Colors.BLACK, t.root?.left?.color)
        assertEquals(Colors.BLACK, t.root?.right?.color)
        assertEquals(Colors.RED, t.root?.left?.left?.color)
    }

    @Test
    fun `test parent is right child and node has not red uncle and node is left child`() {
        val t = RBTree<Int, Int>()
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(40, 0)
        t.insert(35, 0)

        assertEquals(20, t.root?.key)
        assertEquals(10, t.root?.left?.key)
        assertEquals(35, t.root?.right?.key)
        assertEquals(30, t.root?.right?.left?.key)
        assertEquals(40, t.root?.right?.right?.key)
        assertEquals(Colors.BLACK, t.root?.left?.color)
        assertEquals(Colors.BLACK, t.root?.right?.color)
        assertEquals(Colors.RED, t.root?.right?.right?.color)
        assertEquals(Colors.RED, t.root?.right?.left?.color)
    }

    @Test
    fun `test parent is left child and node has not red uncle and node is right child`() {
        val t = RBTree<Int, Int>()
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)
        t.insert(7, 0)

        assertEquals(20, t.root?.key)
        assertEquals(7, t.root?.left?.key)
        assertEquals(30, t.root?.right?.key)
        assertEquals(10, t.root?.left?.right?.key)
        assertEquals(5, t.root?.left?.left?.key)
        assertEquals(Colors.BLACK, t.root?.left?.color)
        assertEquals(Colors.BLACK, t.root?.right?.color)
        assertEquals(Colors.RED, t.root?.left?.right?.color)
        assertEquals(Colors.RED, t.root?.left?.left?.color)
    }
}
