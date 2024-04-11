package nodes

import primitives.Node

class RBNode<K : Comparable<K>, V>(k: K, v: V) : Node<K, V, RBNode<K, V>>(k, v) {
    enum class Colors {
        RED, BLACK
    }
    var color = Colors.BLACK
    var parent: RBNode<K, V>? = null
}
