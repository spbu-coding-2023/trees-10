package trees

import nodes.AVLNode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AVLTreeTest {
    @Test
    fun `insertion in empty tree`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        val testNode = AVLNode(1, "A")
        assertEquals(testNode, tree.root)
    }

    @Test
    fun `insertion and search`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        assertEquals("A", tree.search(1))
    }

    @Test
    fun `insertion and deletion`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.delete(1)
        assertNull(tree.root)
    }

    @Test
    fun `root deletion`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A") // root
        tree.insert(2, "B") // right child
        tree.delete(1)
        val newRoot = AVLNode(2, "B")
        assertEquals(newRoot, tree.root)
    }
}