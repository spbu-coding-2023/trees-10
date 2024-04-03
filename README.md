# trees-10
The **`trees`** library contains 3 types of trees: [BSTree](https://en.wikipedia.org/wiki/Binary_tree) (Binary Search Tree), [AVLTree](https://en.wikipedia.org/wiki/AVL_tree) and [RBTree](https://en.wikipedia.org/wiki/Red–black_tree) (Red–Black Tree)
___
# How to launch?
```
./gradlew build
```
# How to create a tree?
``` kotlin
var bstree = BSTree<Int, Int>()
var avltree = AVLTree<Int, Int>()
var rbtree = RBTree<Int, Int>()
```
The first type parameter(**`key`**) is a comparable key

The second parameter(**`value`**) can take any form


# Basic usage
All types of trees support all CRUD operations:
``` kotlin
    var tree = BSTree<String, Int>()
    tree.insert("kotlin <3", 19)
    tree.update("kotlin <3", 666)
    tree.delete("kotlin <3")
    tree.search("Windows > Linux")
```

You can iterate over a tree values:
``` kotlin
    var tree = BSTree<String, Int>()
    // iterate like this
    for (t in tree) {
        println(t)
    }
    // or like this
    tree.forEach {
        println(it)
    }
```
