package trees

import primitives.Tree
import nodes.RBNode

class RBTree<K : Comparable<K>, V>: Tree<K, V, RBNode<K, V>>() {
    override fun toString() = diagram(root)
    private fun diagram(node: RBNode<K, V>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.left == null && node.right == null) {
                "$root${node.key}\n"
            } else {
                diagram(node.right, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.key}\n" + diagram(node.left,
                    "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }

    private fun recInsert(key: K, value: V, node: RBNode<K, V>?): RBNode<K, V>? {
        node?.let {
            if (key < node.key) {
                if (node.left == null) {
                    node.left = RBNode(key, value)
                } else  recInsert(key, value, node.left)
            } else {
                if (node.right == null) {
                    node.right = RBNode(key, value)
                } else {
                    recInsert(key, value, node.right)
                }
            }
        }
        return node
    }

    override fun insert(key: K, value: V) {
        if (root == null) {
            root = RBNode(key, value)
            return
        }
        val newNode = recInsert(key, value, root)
    }
    override fun delete(key: K) {}
}
