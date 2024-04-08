package trees

import nodes.AVLNode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.reflect.Field
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AVLTreeTest {
    private lateinit var tree: AVLTree<Int, String>
    private var root: AVLNode<*, *>? = null
    private lateinit var f: Field

    @BeforeEach
    fun setup() {
        tree = AVLTree()
        f = AVLTree::class.java.superclass.getDeclaredField("root")
        f.trySetAccessible()
    }

    @Test
    fun `test 1 insertion in empty tree`() {
        tree.insert(1, "A")
        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(Pair(1, "A"), Pair(root?.key, root?.value))
    }

    @Test
    fun `test 2 insertion and search`() {
        tree.insert(1, "A")
        root = f.get(tree) as AVLNode<*, *>?
        assertEquals("A", tree.search(1))
    }

    @Test
    fun `test 3 insertion and deletion`() {
        tree.insert(1, "A")
        tree.delete(1)
        root = f.get(tree) as AVLNode<*, *>?
        assertNull(root)
    }

    @Test
    fun `test 4 root deletion`() {
        tree.insert(1, "A") // root
        tree.insert(2, "B") // right child
        tree.delete(1)
        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(Pair(2, "B"), Pair(root?.key, root?.value))
    }

    @Test
    fun `test 5 insertion and height balance`() {
        tree.insert(1, "A")
        tree.insert(2, "B")
        tree.insert(3, "C")
        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(2, root?.height)
    }

    @Test
    fun `test 6 tree balancing`() {
        tree.insert(4, "A")
        tree.insert(2, "B")
        tree.insert(6, "C")
        tree.insert(1, "D")
        tree.insert(3, "E")
        tree.insert(5, "F")
        tree.insert(7, "G")

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(4, root?.key)
        assertEquals(2, root?.left?.key)
        assertEquals(1, root?.left?.left?.key)
        assertEquals(3, root?.left?.right?.key)
        assertEquals(6, root?.right?.key)
        assertEquals(7, root?.right?.right?.key)
        assertEquals(5, root?.right?.left?.key)
    }

    @Test
    fun `test 7 insert duplicate key`() {
        tree.insert(1, "A")
        tree.insert(1, "B")
        root = f.get(tree) as AVLNode<*, *>?
        assertEquals("B", tree.search(1))
    }

    @Test
    fun `test 8 left rotation`() {
        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(4, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(5, root?.right?.key)
        assertEquals(4, root?.right?.left?.key)
        assertEquals(6, root?.right?.right?.key)
    }

    @Test
    fun `test 9 right rotation`() {
        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(7, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(6, root?.right?.key)
        assertEquals(5, root?.right?.left?.key)
        assertEquals(7, root?.right?.right?.key)
    }

    @Test
    fun `test 10 delete node with 2 children`() {
        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(7, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        tree.delete(6)

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(7, root?.right?.key)
        assertEquals(5, root?.right?.left?.key)
    }

    @Test
    fun `test 11 balancing after deletion`() {
        tree.insert(15, "A")
        tree.insert(10, "B")
        tree.insert(20, "C")
        tree.insert(1, "D")
        tree.insert(14, "E")
        tree.insert(30, "F")
        tree.insert(2, "G")

        tree.delete(30)

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(10, root?.key)
        assertEquals(15, root?.right?.key)
        assertEquals(1, root?.left?.key)
    }

    @Test
    fun `test 12 right and left rotation (big left rotation)`() {
        tree.insert(20, "A")
        tree.insert(10, "B")
        tree.insert(40, "C")
        tree.insert(30, "D")
        tree.insert(2, "E")
        tree.insert(50, "F")
        tree.insert(25, "G")

        tree.delete(2)

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(30, root?.key)
        assertEquals(40, root?.right?.key)
        assertEquals(20, root?.left?.key)
    }

    @Test
    fun `test 13 left and right rotation (big right rotation)`() {
        tree.insert(20, "A")
        tree.insert(10, "B")
        tree.insert(30, "C")
        tree.insert(30, "D")
        tree.insert(40, "E")
        tree.insert(7, "F")
        tree.insert(14, "G")
        tree.insert(15, "H")
        tree.insert(12, "L")

        tree.delete(40)

        root = f.get(tree) as AVLNode<*, *>?
        assertEquals(14, root?.key)
        assertEquals(20, root?.right?.key)
        assertEquals(10, root?.left?.key)
    }
}