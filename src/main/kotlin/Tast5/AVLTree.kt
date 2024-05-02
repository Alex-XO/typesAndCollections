package Tast5
/*
The AVLTree<T : Comparable<T>> class for creating a balanced binary search tree
 */

class Node<T : Comparable<T>>(var key: T) {
    var height: Int = 1
    var left: Node<T>? = null
    var right: Node<T>? = null
}

class AVLTree<T : Comparable<T>>() {
    var root: Node<T>? = null

    fun insert(key: T) {
        root = insert(root, key)
    }

    fun delete(key: T) {
        root = delete(root, key)
    }

    fun displayInOrder() {
        displayInOrder(root)
        println()
    }

    private fun insert(node: Node<T>?, key: T): Node<T> {
        if (node == null) return Node(key)

        if (key < node.key) {
            node.left = insert(node.left, key)
        } else if (key > node.key) {
            node.right = insert(node.right, key)
        } else {
            return node
        }

        updateHeight(node)
        return balance(node, key)
    }

    private fun delete(node: Node<T>?, key: T): Node<T>? {
        if (node == null) return null

        if (key < node.key) {
            node.left = delete(node.left, key)
        } else if (key > node.key) {
            node.right = delete(node.right, key)
        } else {
            if (node.left == null || node.right == null) {
                return node.left ?: node.right
            }
            val temp = minValueNode(node.right!!)
            node.key = temp.key
            node.right = delete(node.right, temp.key)
        }

        updateHeight(node)
        return balance(node, node.key)
    }

    private fun displayInOrder(node: Node<T>?) {
        if (node != null) {
            displayInOrder(node.left)
            print("${node.key} ")
            displayInOrder(node.right)
        }
    }

    private fun height(node: Node<T>?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: Node<T>) {
        node.height = 1 + maxOf(height(node.left), height(node.right))
    }

    private fun balanceFactor(node: Node<T>?): Int {
        return height(node?.left) - height(node?.right)
    }

    private fun leftRotate(x: Node<T>): Node<T> {
        val y = x.right!!
        val T2 = y.left
        y.left = x
        x.right = T2
        updateHeight(x)
        updateHeight(y)
        return y
    }

    private fun rightRotate(y: Node<T>): Node<T> {
        val x = y.left!!
        val T2 = y.right
        x.right = y
        y.left = T2
        updateHeight(y)
        updateHeight(x)
        return x
    }

    private fun balance(node: Node<T>, key: T): Node<T> {
        val balance = balanceFactor(node)
        if (balance > 1 && key < node.left!!.key) {
            return rightRotate(node)
        }
        if (balance < -1 && key > node.right!!.key) {
            return leftRotate(node)
        }
        if (balance > 1 && key > node.left!!.key) {
            node.left = leftRotate(node.left!!)
            return rightRotate(node)
        }
        if (balance < -1 && key < node.right!!.key) {
            node.right = rightRotate(node.right!!)
            return leftRotate(node)
        }
        return node
    }

    private fun minValueNode(node: Node<T>): Node<T> {
        var current = node
        while (current.left != null) current = current.left!!
        return current
    }

}

fun main() {
    val avlTree = AVLTree<Int>()
    avlTree.insert(10)
    avlTree.insert(20)
    avlTree.insert(30)
    avlTree.insert(40)
    avlTree.insert(50)
    avlTree.insert(25)

    println("Inorder traversal of the constructed AVL tree is:")
    avlTree.displayInOrder()

    avlTree.delete(30)

    println("Inorder traversal after deletion of 30:")
    avlTree.displayInOrder()
}
