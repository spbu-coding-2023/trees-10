package primitives

abstract class Tree<K : Comparable<K>, V, N : Node<K, V, N>> {
    var root: N? = null
    abstract fun insert(key: K, value: V)
    abstract fun delete(key: K)
    fun update(key: K, value: V) {}
    fun search(key: K): V? {return null}
}
