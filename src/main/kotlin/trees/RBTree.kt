package trees

import primitives.Tree
import nodes.RBNode

class RBTree<K : Comparable<K>, V>: Tree<K, V, RBNode<K, V>>() {
    override fun insert(key: K, value: V) {}
    override fun delete(key: K) {}
}
