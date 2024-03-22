package nodes

import primitives.Node

class AVLNode<K : Comparable<K>, V>(k: K, v: V) : Node<K, V, AVLNode<K, V>>(k, v) {
    var height: Int = 1
}
