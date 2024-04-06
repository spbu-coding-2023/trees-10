package trees

import primitives.Tree
import nodes.BSNode

class BSTree<K : Comparable<K>, V>: Tree<K, V, BSNode<K, V>>(){

    override fun insert(key: K, value: V) {
        root = insertRec(root, key, value)
    }

    private fun insertRec(currentNode: BSNode<K, V>?, key:K, value: V): BSNode<K,V> {
        if (currentNode == null)
            return BSNode(key,value)

        if (currentNode.key > key)
            currentNode.left = insertRec(currentNode.left, key, value)

        else if (currentNode.key < key)
            currentNode.right = insertRec(currentNode.right, key, value)

        else if (currentNode.key == key)
            currentNode.value = value

        return currentNode
    }

    private fun getMinKey(current: BSNode<K, V>?): BSNode<K,V> {

        return if (current?.left == null && current != null) current else getMinKey(current?.left)
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

            val minKey = getMinKey(current.right)

            current.key = minKey.key
            current.value = minKey.value

            current.right = deleteRec(current.right, minKey.key)

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
