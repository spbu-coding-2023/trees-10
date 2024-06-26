package trees

import nodes.BSNode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BSTreeTest {
    private lateinit var tree: BSTree<Int,Int>
    private var root: BSNode<*, *>? = null


    private var randomizer = Random(24)
    private val keys = Array(100000) { randomizer.nextInt() }.distinct()
    private val keysCount = keys.size

    fun checkInvariant(tree : BSTree<Int, Int>, length: Int) : Boolean {
        val treeNode = mutableListOf<Int>()

        for (pair in tree)
            treeNode.add(pair.second)


        for (i in 1 until length) {
            if (treeNode[i] <= treeNode[i - 1])
                return false
        }
        return true
    }

    @BeforeEach
    fun createTree() {
        tree = BSTree()
        val f = BSTree::class.java.superclass.getDeclaredField("root")
        f.trySetAccessible()
        root = f.get(tree) as BSNode<*, *>?
    }

    @Test
    fun `the null tree must be null`() {
        assertNull(root, "tree must be null")
    }

    @Test
    fun `all unique keys must be added in the appropriate order`() {

        keys.forEach { key -> tree.insert(key, key) }

        assertTrue(checkInvariant(tree, keysCount))
    }

    @Test
    fun `delete key not contains in tree`() {

        keys.forEach { key -> tree.insert(key, key) }

        val randomDeleteKey =  keys.random()

        tree.delete(randomDeleteKey)

        var treeSize = 0

        tree.forEach { treeSize ++ }

        assertTrue(checkInvariant(tree, treeSize), "After deleting the key, the binary tree lost its properties")

        assertTrue(!tree.contains(Pair(randomDeleteKey, randomDeleteKey)), "Tree contains deleted key")

        assertEquals(treeSize, keysCount - 1, "The size of the tree with the deleted node should be 1 less than the original one")
    }

    @Test
    fun `the updated key must be updated`() {

        keys.forEach { key -> tree.insert(key, key) }

        val randomUpdateKey =  keys.random()
        var randomValue : Int

        do {
            randomValue = Random.nextInt(1,10000001)
        } while (tree.search(randomUpdateKey) == randomValue)

        tree.update(randomUpdateKey, randomValue)

        var countUpdate = 0

        for (pair in tree.zip(keys.sorted())) {
            countUpdate += if (pair.first.second != pair.second) 1 else 0
        }

        assertEquals(countUpdate, 1, "Only one node in the tree should be changed")


    }
    @Test
    fun `tree should not contain duplicate elements`() {
        keys.forEach { key -> tree.insert(key, key) }

        keys.forEach { key -> tree.insert(key, key) }

        var countBeforeDoubleInsert = 0

        tree.forEach { countBeforeDoubleInsert ++ }

        assertEquals(countBeforeDoubleInsert, keysCount, "Tree must contain unique keys")
    }

    @Test
    fun `tree should be empty after deleting all the keys`() {

        keys.forEach { key -> tree.insert(key, key) }

        keys.forEach {tree.delete(it) }

        assertNull(root, "Tree was be null")
    }
    @Test
    fun `search must return value`() {
        keys.forEach { key -> tree.insert(key, key) }

        keys.forEach {
            assertEquals(it, tree.search(it), "The element was not found, although it exists in the tree")
        }
    }

    @Test
    fun `if the element is not found, then search should return null`() {
        var randomKey : Int

        do {
            randomKey = Random.nextInt(1,10000001)
        } while (keys.contains(randomKey))

        assertNull(tree.search(randomKey), "The element is not contained in the tree")

    }
}