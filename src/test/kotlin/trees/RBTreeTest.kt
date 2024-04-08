package trees

import nodes.RBNode
import nodes.RBNode.Colors
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.reflect.Field
import kotlin.test.assertEquals

class RBTreeTest {
    private lateinit var t: RBTree<Int, Int>
    private var root: RBNode<*, *>? = null
    private lateinit var f: Field

    @BeforeEach
    fun setup() {
        t = RBTree()
        f = RBTree::class.java.superclass.getDeclaredField("root")
        f.trySetAccessible()
    }

    @Test
    fun `test insertion into void tree`() {
        t.insert(1, 0)
        root = f.get(t) as RBNode<*, *>?
        assertEquals(1, root?.key)
    }

    @Test
    fun `test insertion of three elements to the right`() {
        t.insert(1, 0)
        t.insert(2, 0)
        t.insert(3, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(2, root?.key)
        assertEquals(1, root?.left?.key)
        assertEquals(3, root?.right?.key)
        assertEquals(Colors.RED, root?.left?.color)
        assertEquals(Colors.RED, root?.right?.color)
    }

    @Test
    fun `test insertion of three elements to the left`() {
        t.insert(3, 0)
        t.insert(2, 0)
        t.insert(1, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(2, root?.key)
        assertEquals(1, root?.left?.key)
        assertEquals(3, root?.right?.key)
        assertEquals(Colors.RED, root?.left?.color)
        assertEquals(Colors.RED, root?.right?.color)
    }

    @Test
    fun `test parent is right child and node has red uncle`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(35, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(20, root?.key)
        assertEquals(10, root?.left?.key)
        assertEquals(30, root?.right?.key)
        assertEquals(35, root?.right?.right?.key)
        assertEquals(Colors.BLACK, root?.left?.color)
        assertEquals(Colors.BLACK, root?.right?.color)
        assertEquals(Colors.RED, root?.right?.right?.color)
    }

    @Test
    fun `test parent is left child and node has red uncle`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(20, root?.key)
        assertEquals(10, root?.left?.key)
        assertEquals(30, root?.right?.key)
        assertEquals(5, root?.left?.left?.key)
        assertEquals(Colors.BLACK, root?.left?.color)
        assertEquals(Colors.BLACK, root?.right?.color)
        assertEquals(Colors.RED, root?.left?.left?.color)
    }

    @Test
    fun `test parent is right child and node has not red uncle and node is left child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(40, 0)
        t.insert(35, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(20, root?.key)
        assertEquals(10, root?.left?.key)
        assertEquals(35, root?.right?.key)
        assertEquals(30, root?.right?.left?.key)
        assertEquals(40, root?.right?.right?.key)
        assertEquals(Colors.BLACK, root?.left?.color)
        assertEquals(Colors.BLACK, root?.right?.color)
        assertEquals(Colors.RED, root?.right?.right?.color)
        assertEquals(Colors.RED, root?.right?.left?.color)
    }

    @Test
    fun `test parent is left child and node has not red uncle and node is right child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(5, 0)
        t.insert(7, 0)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(20, root?.key)
        assertEquals(7, root?.left?.key)
        assertEquals(30, root?.right?.key)
        assertEquals(10, root?.left?.right?.key)
        assertEquals(5, root?.left?.left?.key)
        assertEquals(Colors.BLACK, root?.left?.color)
        assertEquals(Colors.BLACK, root?.right?.color)
        assertEquals(Colors.RED, root?.left?.right?.color)
        assertEquals(Colors.RED, root?.left?.left?.color)
    }


    @Test
    fun `test deletion of node without any children`() {
        t.insert(1, 0)
        t.insert(2, 0)
        t.insert(3, 0)

        t.delete(3)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(null, root?.right)
    }

    @Test
    fun `test deletion of node with right child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(35, 0)

        t.delete(30)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(35, root?.right?.key)
    }

    @Test
    fun `test deletion of node with left child`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(25, 0)

        t.delete(30)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(25, root?.right?.key)
    }

    @Test
    fun `test deletion of node with both children`() {
        t.insert(10, 0)
        t.insert(20, 0)
        t.insert(30, 0)
        t.insert(25, 0)
        t.insert(35, 0)

        t.delete(30)

        root = f.get(t) as RBNode<*, *>?
        assertEquals(35, root?.right?.key)
        assertEquals(25, root?.right?.left?.key)
        assertEquals(null, root?.right?.right?.key)
    }
}
