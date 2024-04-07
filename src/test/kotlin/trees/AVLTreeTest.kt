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
        assertEquals(Pair(1, "A"), Pair(tree.getRoot()?.key, tree.getRoot()?.value))
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
        assertNull(tree.getRoot())
    }

    @Test
    fun `test 4 root deletion`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A") // root
        tree.insert(2, "B") // right child
        tree.delete(1)
        assertEquals(Pair(2, "B"), Pair(tree.getRoot()?.key, tree.getRoot()?.value))
    }

    @Test
    fun `test 5 insertion and height balance`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.insert(2, "B")
        tree.insert(3, "C")
        assertEquals(2, tree.getRoot()?.height)
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

        assertEquals(4, tree.getRoot()?.key)
        assertEquals(2, tree.getRoot()?.left?.key)
        assertEquals(1, tree.getRoot()?.left?.left?.key)
        assertEquals(3, tree.getRoot()?.left?.right?.key)
        assertEquals(6, tree.getRoot()?.right?.key)
        assertEquals(7, tree.getRoot()?.right?.right?.key)
        assertEquals(5, tree.getRoot()?.right?.left?.key)
    }

    @Test
    fun `test 7 insert duplicate key`() {
        val tree = AVLTree<Int, String>()
        tree.insert(1, "A")
        tree.insert(1, "B")
        assertEquals("B", tree.search(1))
    }

    @Test
    fun `test 8 left rotation`() {
        val tree = AVLTree<Int, String>()

        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(4, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        assertEquals(5, tree.getRoot()?.right?.key)
        assertEquals(4, tree.getRoot()?.right?.left?.key)
        assertEquals(6, tree.getRoot()?.right?.right?.key)
    }

    @Test
    fun `test 9 right rotation`() {
        val tree = AVLTree<Int, String>()

        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(7, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        assertEquals(6, tree.getRoot()?.right?.key)
        assertEquals(5, tree.getRoot()?.right?.left?.key)
        assertEquals(7, tree.getRoot()?.right?.right?.key)
    }

    @Test
    fun `test 10 delete node with 2 children`() {
        val tree = AVLTree<Int, String>()

        tree.insert(3, "A")
        tree.insert(1, "B")
        tree.insert(7, "C")
        tree.insert(5, "D")
        tree.insert(6, "E")

        tree.delete(6)

        assertEquals(7, tree.getRoot()?.right?.key)
        assertEquals(5, tree.getRoot()?.right?.left?.key)
    }

    @Test
    fun `test 11 balancing after deletion`() {
        val tree = AVLTree<Int, String>()

        tree.insert(15, "A")
        tree.insert(10, "B")
        tree.insert(20, "C")
        tree.insert(1, "D")
        tree.insert(14, "E")
        tree.insert(30, "F")
        tree.insert(2, "G")

        tree.delete(30)

        assertEquals(10, tree.getRoot()?.key)
        assertEquals(15, tree.getRoot()?.right?.key)
        assertEquals(1, tree.getRoot()?.left?.key)
    }

    @Test
    fun `test 12 right and left rotation (big left rotation)`() {
        val tree = AVLTree<Int, String>()

        tree.insert(20, "A")
        tree.insert(10, "B")
        tree.insert(40, "C")
        tree.insert(30, "D")
        tree.insert(2, "E")
        tree.insert(50, "F")
        tree.insert(25, "G")

        tree.delete(2)

        assertEquals(30, tree.getRoot()?.key)
        assertEquals(40, tree.getRoot()?.right?.key)
        assertEquals(20, tree.getRoot()?.left?.key)
    }

    @Test
    fun `test 13 left and right rotation (big right rotation)`() {
        val tree = AVLTree<Int, String>()

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

        assertEquals(14, tree.getRoot()?.key)
        assertEquals(20, tree.getRoot()?.right?.key)
        assertEquals(10, tree.getRoot()?.left?.key)
    }
}