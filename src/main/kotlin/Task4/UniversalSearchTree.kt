package Task4

class Node<T : Comparable<T>>(var data: T) {
    var left: Node<T>? = null
    var right: Node<T>? = null
}

class BinarySearchTree<T : Comparable<T>>() {
    var root: Node<T>? = null

    fun insert(value: T) {
        root = insertRecursive(root, value)
    }

    private fun insertRecursive(current: Node<T>?, value: T): Node<T> {
        if (current == null) {
            return Node(value)
        }
        if (value < current.data) {
            current.left = insertRecursive(current.left, value)
        } else if (value > current.data) {
            current.right = insertRecursive(current.right, value)
        }
        return current
    }

    fun search(value: T): Boolean {
        return searchRecursive(root, value)
    }

    private fun searchRecursive(current: Node<T>?, value: T): Boolean {
        if (current == null) {
            return false
        }
        if (value == current.data) {
            return true
        }
        return if (value < current.data) {
            searchRecursive(current.left, value)
        } else {
            searchRecursive(current.right, value)
        }
    }

    fun delete(value: T) {
        root = deleteRecursive(root, value)
    }

    private fun deleteRecursive(current: Node<T>?, value: T): Node<T>? {
        if (current == null) {
            return null
        }
        when {
            value == current.data -> {
                if (current.left == null && current.right == null) return null
                if (current.right == null) return current.left
                if (current.left == null) return current.right

                val smallestValue = findSmallestValue(current.right!!)
                current.data = smallestValue
                current.right = deleteRecursive(current.right, smallestValue)
            }
            value < current.data -> current.left = deleteRecursive(current.left, value)
            value > current.data -> current.right = deleteRecursive(current.right, value)
        }
        return current
    }

    private fun findSmallestValue(root: Node<T>): T {
        return root.left?.let { findSmallestValue(it) } ?: root.data
    }
}

fun main() {
    val binarySearchTree = BinarySearchTree<Int>()
    binarySearchTree.insert(3)
    binarySearchTree.insert(2)
    binarySearchTree.insert(5)
    binarySearchTree.insert(1)
    binarySearchTree.delete(3)
    println("Search for 3: ${binarySearchTree.search(3)}")
    println("Search for 2: ${binarySearchTree.search(2)}")
}