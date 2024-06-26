package iterator

import primitives.Node
import java.util.Stack

/*
    InOrderIterator
 */
open class TreeIterator<K : Comparable<K>, V, N : Node<K, V, N>>(root: N?) : Iterator<Pair<K, V>> {
    private val stack = Stack<N>()

    init {
        var node = root
        while (node != null) {
            stack.push(node)
            node = node.left
        }
    }
    override fun hasNext(): Boolean {
        return !stack.empty()
    }
    override fun next(): Pair<K, V> {
        val node = stack.pop()
        var nextNode = node.right
        while (nextNode != null) {
            stack.push(nextNode)
            nextNode = nextNode.left
        }
        return Pair(node.key, node.value)
    }
}
