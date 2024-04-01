# trees-10
The **`trees`** library contains 3 types of trees: [BSTree](https://en.wikipedia.org/wiki/Binary_tree) (Binary Search Tree), [AVLTree](https://en.wikipedia.org/wiki/AVL_tree) and [RBTree](https://en.wikipedia.org/wiki/Red–black_tree) (Red–Black Tree)
___
# How to launch?
```
./gradlew build
```
# How to create a tree?
``` kotlin
var bstree = BSTree<Int, Int>() // Created an empty Binary Search Tree
var avltree = BSTree<Int, Int>() // Created an empty AVLTree
var rbtree = BSTree<Int, Int>() // Created an empty Red–Black Tree
```
The first type parameter(**`key`**) is a comparable key

The second parameter(**`value`**) can take any form


# What a tree can do?
All types of trees can perform 3 actions: 

1)`insert` (The tree adds a key and writes a value to it)

2)`update` (The tree updates the value by key)

3)`delete` (The tree deletes the node with the key that the user specified)

4)`search` (The tree is looking for a node with the key that the user specified)



**`Example:`**
``` kotlin
    var bstree = BSTree<String, Int>()
    bstree.insert("kotlin <3", 19)
    bstree.update("kotlin <3", 666)
    bstree.delete("kotlin <3")
    bstree.search("Windows > Linux")
```
