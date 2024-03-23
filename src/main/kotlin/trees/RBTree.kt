package trees

import primitives.Tree
import nodes.RBNode
import nodes.RBNode.Colors

class RBTree<K : Comparable<K>, V>: Tree<K, V, RBNode<K, V>>() {
    override fun toString() = diagram(root)
    private fun diagram(node: RBNode<K, V>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.left == null && node.right == null) {
                "$root${node.key}(${node.color})\n"
            } else {
                diagram(node.right, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.key}(${node.color})\n" + diagram(node.left,
                    "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }

    private fun insertNode(key: K, value: V,
                          node: RBNode<K, V>?): RBNode<K, V>? {
        node?.let {
            if (key < node.key) {
                if (node.left == null) {
                    node.left = RBNode(key, value)
                    node.left?.parent = node
                    return node.left
                } else return insertNode(key, value, node.left)
            } else {
                if (node.right == null) {
                    node.right = RBNode(key, value)
                    node.right?.parent = node
                    return node.right
                } else {
                    return insertNode(key, value, node.right)
                }
            }
        }
        return null
    }

    private fun leftRotate(node: RBNode<K, V>): RBNode<K, V>? {
        val pivot = node.right
        var returnedValue: RBNode<K, V>? = null

        pivot?.parent = node.parent
        if (node.parent == null) returnedValue = pivot
        if (node.parent != null) {
            if (node.parent?.left == node)
                node.parent?.left = pivot
            else
                node.parent?.right = pivot
        }

        node.right = pivot?.left
        if (pivot?.left != null)
            pivot.left?.parent = node

        node.parent = pivot
        pivot?.left = node
        return returnedValue
    }

    private fun rightRotate(node: RBNode<K, V>): RBNode<K, V>? {
        val pivot = node.left
        var returnedValue: RBNode<K, V>? = null

        pivot?.parent = node.parent
        if (node.parent == null) returnedValue = pivot
        if (node.parent != null) {
            if (node.parent?.left == node)
                node.parent?.left = pivot
            else
                node.parent?.right = pivot
        }

        node.left = pivot?.right
        if (pivot?.right != null)
            pivot.right?.parent = node

        node.parent = pivot
        pivot?.right = node
        return returnedValue
    }

    private fun getNodeGrandParent(node: RBNode<K, V>?) =
        node?.parent?.parent

    private fun getNodeUncle(node: RBNode<K, V>?): RBNode<K, V>? {
        val grandParent = getNodeGrandParent(node) ?: return null
        return if (node?.parent == grandParent.left)
            grandParent.right
        else
            grandParent.left
    }

    override fun insert(key: K, value: V) {
        if (root == null) {
            root = RBNode(key, value)
            return
        }
        var newNode = insertNode(key, value, root)
        newNode?.color = Colors.RED

        while (newNode?.parent?.color == Colors.RED) {
            if (getNodeGrandParent(newNode)?.left == newNode.parent) { // parent is left child
                if (getNodeUncle(newNode)?.color == Colors.RED) { // has red uncle
                    newNode.parent?.color = Colors.BLACK
                    getNodeUncle(newNode)?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    newNode = getNodeGrandParent(newNode)
                } else {
                    if (newNode.parent?.right == newNode) {
                        newNode = newNode.parent
                        newNode?.let { leftRotate(it) }
                    }
                    newNode?.parent?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    getNodeGrandParent(newNode)?.let {
                        val result = rightRotate(it)
                        if (result != null) root = result
                    }
                }
            } else { // parent is right child
                if (getNodeUncle(newNode)?.color == Colors.RED) { // has red uncle
                    newNode.parent?.color = Colors.BLACK
                    getNodeUncle(newNode)?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    newNode = getNodeGrandParent(newNode)
                } else {
                    if (newNode.parent?.left == newNode) {
                        newNode = newNode.parent
                        newNode?.let { rightRotate(it) }
                    }
                    newNode?.parent?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    getNodeGrandParent(newNode)?.let {
                        val result = leftRotate(it)
                        if (result != null) root = result
                    }
                }
            }
        }
        root?.color = Colors.BLACK
    }
    override fun delete(key: K) {}
}
