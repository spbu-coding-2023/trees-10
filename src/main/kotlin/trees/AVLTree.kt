package trees

import primitives.Tree
import nodes.AVLNode

class AVLTree<K : Comparable<K>, V>: Tree<K, V, AVLNode<K, V>>() {
    override fun insert(key: K, value: V) {}
    override fun delete(key: K) {}
}
