class AVLTree<K : Comparable<K>, V>: Tree<K, V, AVLNode<K, V>>() {
    override fun insert(key: K, value: V) {
        root = insertRec(root, key, value)
    }

    override fun delete(key: K) {
        root = deleteRec(root, key)
    }

    private fun insertRec(current: AVLNode<K, V>?, key: K, value: V): AVLNode<K, V>? {
        if (current == null)
            return AVLNode(key, value)

        if (key < current.key)
            current.left = insertRec(current.left, key, value)
        else if (key > current.key)
            current.right = insertRec(current.right, key, value)
        else {
            current.value = value
            return current
        }

        return balance(current)
    }

    private fun deleteRec(node: AVLNode<K, V>?, key: K): AVLNode<K, V>? {
        if (node == null)
            return null
        if (key < node.key)
            node.left = deleteRec(node.left, key)
        else if (key > node.key)
            node.right = deleteRec(node.right, key)
        else {
            if (node.left == null || node.right == null) {
                val temp = if (node.left != null)node.left else node.right
                return temp
            }
            else {
                val t = findMin(node.right!!)
                node.key = t.key
                node.value = t.value
                node.right = deleteRec(node.right, t.key)
            }
        }

        return balance(node)
    }

    private fun findMin(node: AVLNode<K, V>): AVLNode<K, V> {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    private fun updateNodeHeight(node: AVLNode<K, V>?) {
        node?.height = 1 + maxOf((node?.left?.height?: 0), (node?.right?.height?: 0))
    }

    private fun balanceFactor(node: AVLNode<K, V>?): Int{
        return (node?.left?.height?: 0) - (node?.right?.height?: 0)
    }

    private fun balance(node: AVLNode<K, V>?): AVLNode<K, V>? {
        updateNodeHeight(node)
        val t = balanceFactor(node)

        if (t > 1) {
            if (balanceFactor(node?.left) < 0) {
                node?.left = rotateLeft(node?.left)
            }
            return rotateRight(node)
        }
        if (t < -1) {
            if (balanceFactor(node?.right) > 0) {
                node?.right = rotateRight(node?.right)
            }
            return rotateLeft(node)
        }
        return node
    }

    private fun rotateRight(node: AVLNode<K, V>?): AVLNode<K, V>? {
        val leftChild = node?.left
        node?.left = leftChild?.right
        leftChild?.right = node
        updateNodeHeight(node)
        updateNodeHeight(leftChild)
        return leftChild
    }

    private fun rotateLeft(node: AVLNode<K, V>?): AVLNode<K, V>? {
        val rightChild = node?.right
        node?.right = rightChild?.left
        rightChild?.left = node
        updateNodeHeight(node)
        updateNodeHeight(rightChild)
        return rightChild
    }
}
