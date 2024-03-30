package primitives

abstract class Node<K : Comparable<K>, V, N : Node<K, V, N>>(k: K, v:V) {
    var key: K = k
    var value: V = v
    var right: N? = null
    var left: N? = null
    var parent: N? = null
}
