package primitives

abstract class Tree<K : Comparable<K>, V, N : Node<K, V, N>> {
    var root: N? = null
    abstract fun insert(key: K, value: V)
    abstract fun delete(key: K)
    fun update(key: K, value: V) {
        val node = searchRec(root, key)
        if (node == null)
            throw Exception("This key is not in the tree")
        node.value = value
    }

    fun search(key: K): V? {
        return searchRec(root, key)?.value
    }

    private fun searchRec(current: N?, key: K): N? {
        if (current == null)
            return null
        if (current.key == key)
            return current
        else if (current.key > key)
            return searchRec(current.left, key)
        else
            return searchRec(current.right, key)

    }
}

