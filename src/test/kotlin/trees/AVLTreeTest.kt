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
}