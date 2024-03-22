package trees

import primitives.Tree
import nodes.BSNode
import primitives.Node
class BSTree<K : Comparable<K>, V>: Tree<K, V, BSNode<K, V>>(){
    override fun insert(key: K, value: V) {
        root = insertRec(root, key, value)
    }
    private fun insertRec(current: BSNode<K, V>?, key:K, value: V): BSNode<K,V> {
        if (current == null)
            return BSNode(key,value)
        if (current.key > key)
            current.left = insertRec(current.left, key, value)
        else if (current.key < key)
            current.right = insertRec(current.right, key, value)
        else if (current.key == key)
            throw Exception("You cannot add a key that is already contained in the tree")
        return current
    }
    private fun getMin(current: BSNode<K, V>?): BSNode<K,V> {
        if (current?.left == null && current != null)
            return current
        else
            return getMin(current?.left)
    }
    override fun delete(key: K){
        root = deleteRec(root,key)
    }
    private fun deleteRec(current: BSNode<K, V>?, key:K) : BSNode<K, V>?{
        if (current == null)
            return null
        if (current.key == key) {
            if (current.left == null && current.right == null)
                return null
            else if (current.right == null)
                return current.left
            else if (current.left == null)
                return current.right
            val minValue = getMin(current.right)
            current.value = minValue.value
            current.right = deleteRec(current.right, minValue.key)
            return current
        }
        if (current.key > key) {
            current.left = deleteRec(current.left, key)
            return current
        }
        current.right = deleteRec(current.right, key)
        return current

    }
}
