package primitives

abstract class Node<K : Comparable<K>, V, N : Node<K, V, N>>(var key: K, var value: V) {
    var right: N? = null
    var left: N? = null
    var parent: N? = null
}
