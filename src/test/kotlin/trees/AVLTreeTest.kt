package trees

import nodes.AVLNode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AVLTreeTest {

    @Test
    fun `test 1 insertion in empty tree`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        assertEquals(Pair(1, "A"), Pair(tree.root?.key, tree.root?.value))
    }

    @Test
    fun `test 2 insertion and search`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        assertEquals("A", tree.search(1))
    }

    @Test
    fun `test 3 insertion and deletion`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.delete(1)
        assertNull(tree.root)
    }

    @Test
    fun `test 4 root deletion`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A") // root
        tree.insert(2, "B") // right child
        tree.delete(1)
        assertEquals(Pair(2, "B"), Pair(tree.root?.key, tree.root?.value))
    }

    @Test
    fun `test 5 insertion and height balance`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.insert(2, "B")
        tree.insert(3, "C")
        assertEquals(2, tree.root?.height)
    }

    @Test
    fun `test 6 tree balancing`() {
        val tree = AVLTree<Int, String>()
        tree.insert(4, "A")
        tree.insert(2, "B")
        tree.insert(6, "C")
        tree.insert(1, "D")
        tree.insert(3, "E")
        tree.insert(5, "F")
        tree.insert(7, "G")

        assertEquals(4, tree.root?.key)
        assertEquals(2, tree.root?.left?.key)
        assertEquals(1, tree.root?.left?.left?.key)
        assertEquals(3, tree.root?.left?.right?.key)
        assertEquals(6, tree.root?.right?.key)
        assertEquals(7, tree.root?.right?.right?.key)
        assertEquals(5, tree.root?.right?.left?.key)
    }

    @Test
    fun `test 7 insert duplicate key`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.insert(1, "B")
        assertEquals("B", tree.search(1))
    }
}