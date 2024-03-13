package trees

import primitives.Tree
import nodes.BSNode

class BSTree<K : Comparable<K>, V>: Tree<K, V, BSNode<K, V>>(){
    override fun insert(key: K, value: V){}
    override fun delete(key: K){}
}
