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

    private fun leftRotate(node: RBNode<K, V>?): RBNode<K, V>? {
        node ?: throw IllegalStateException("Node value can not bo null!")
        val pivot = node.right
        var newRoot = root

        pivot?.parent = node.parent
        if (node.parent == null) newRoot = pivot
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
        return newRoot
    }

    private fun rightRotate(node: RBNode<K, V>?): RBNode<K, V>? {
        node ?: throw IllegalStateException("Node value can not bo null!")
        val pivot = node.left
        var newRoot = root

        pivot?.parent = node.parent
        if (node.parent == null) newRoot = pivot
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
        return newRoot
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
        val node = insertNode(key, value, root)
        node?.color = Colors.RED
        fixAfterInsertion(node)
    }

    private fun insertNode(key: K, value: V,
                           node: RBNode<K, V>?): RBNode<K, V>? {
        node?.let {
            if (key <= node.key) {
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

    private fun fixAfterInsertion(node: RBNode<K, V>?) {
        var newNode = node
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
                        root = leftRotate(newNode)
                    }
                    newNode?.parent?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    root = rightRotate(
                        getNodeGrandParent(newNode)
                    )
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
                        root = rightRotate(newNode)
                    }
                    newNode?.parent?.color = Colors.BLACK
                    getNodeGrandParent(newNode)?.color = Colors.RED
                    root = leftRotate(
                        getNodeGrandParent(newNode)
                    )
                }
            }
        }
        root?.color = Colors.BLACK
    }

    override fun delete(key: K) {
        var current = findNode(root, key) ?: return

        if (current.left != null && current.right != null) {
            val successor = minValueNode(
                current.right
                    ?: throw IllegalStateException("Impossible to find minValueNode")
            )
            current.key = successor.key
            current.value = successor.value
            current = successor
        }

        val child = if (current.left != null) current.left else current.right
        if (child != null) {
            child.parent = current.parent
            if (current.parent == null) {
                root = child
                return
            }
            if (current == current.parent?.left) {
                current.parent?.left = child
            } else {
                current.parent?.right = child
            }

            if (current.color == Colors.BLACK) {
                fixAfterDeletion(child)
            }
        } else if (current.parent == null) {
            root = null
            return
        } else {
            if (current.color == Colors.BLACK) {
                fixAfterDeletion(current)
            }
            if (current.parent?.left == current) {
                current.parent?.left = null
            } else {
                current.parent?.right = null
            }
        }
    }

    private fun fixAfterDeletion(node: RBNode<K, V>?) {
        var current = node
        var sibling: RBNode<K, V>?

        while (current !== root && takeColor(current) == Colors.BLACK) {
            if (current === current?.parent?.left) {
                sibling = current?.parent?.right
                if (takeColor(sibling) == Colors.RED) {
                    sibling?.color = Colors.BLACK
                    current?.parent?.color = Colors.RED
                    root = leftRotate(current?.parent)
                    sibling = current?.parent?.right
                }
                if (takeColor(sibling?.left) == Colors.BLACK && takeColor(sibling?.right) == Colors.BLACK) {
                    sibling?.color = Colors.RED
                    current = current?.parent
                } else {
                    if (takeColor(sibling?.right) == Colors.BLACK) {
                        sibling?.left?.color = Colors.BLACK
                        sibling?.color = Colors.RED
                        root = rightRotate(sibling)
                        sibling = current?.parent?.right
                    }
                    sibling?.color = current?.parent?.color ?: Colors.BLACK
                    current?.parent?.color = Colors.BLACK
                    sibling?.right?.color = Colors.BLACK
                    root = leftRotate(current?.parent)
                    current = root
                }
            } else {
                sibling = current?.parent?.left
                if (takeColor(sibling) == Colors.RED) {
                    sibling?.color = Colors.BLACK
                    current?.parent?.color = Colors.RED
                    root = rightRotate(current?.parent)
                    sibling = current?.parent?.left
                }
                if (takeColor(sibling?.right) == Colors.BLACK && takeColor(sibling?.left) == Colors.BLACK) {
                    sibling?.color = Colors.RED
                    current = current?.parent
                } else {
                    if (takeColor(sibling?.left) == Colors.BLACK) {
                        sibling?.right?.color = Colors.BLACK
                        sibling?.color = Colors.RED
                        root = leftRotate(sibling)
                        sibling = current?.parent?.left
                    }
                    sibling?.color = current?.parent?.color ?: Colors.BLACK
                    current?.parent?.color = Colors.BLACK
                    sibling?.left?.color = Colors.BLACK
                    root = rightRotate(current?.parent)
                    current = root
                }
            }
        }
        current?.color = Colors.BLACK
        root?.parent = null
    }

    private fun findNode(node: RBNode<K, V>?, key: K): RBNode<K, V>? {
        node ?: return null
        if (key == node.key) {
            return node
        }
        return findNode(if (key < node.key) node.left else node.right, key)
    }

    private fun minValueNode(node: RBNode<K, V>): RBNode<K, V> {
        var current = node
        while (true) {
            current = current.left ?: break
        }
        return current
    }
    private fun takeColor(node: RBNode<K, V>?): Colors {
        return node?.color ?: Colors.BLACK
    }
}
