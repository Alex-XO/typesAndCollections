package Task1

/*
The GenericStack<T> class,
which supports basic stack operations (push, pop, isEmpty, peek).
 */

class GeneralStack<T>(private val capacity: Int = 5) {
    private var data: Array<T?> = arrayOfNulls<Any?>(capacity) as Array<T?>
    private var top: Int = -1

    fun peek(): T? {
        if (top == -1) {
            println("Underflow")
            return null
        }
        return data[top]
    }

    fun pop(): T? {
        if (top == -1) {
            println("Underflow")
            return null
        }
        val element = data[top]
        data[top] = null
        top--
        return element
    }

    fun push(element: T) {
        require(top < data.size - 1) { "Overflow" }
        data[++top] = element
    }

    fun isEmpty(): Boolean {
        return top == -1
    }

    fun size(): Int {
        return top + 1
    }

    fun display() {
        println(data.copyOfRange(0, top + 1).joinToString(" "))
    }
}

fun main() {
    val stack = GeneralStack<String>()
    stack.push("Hello")
    stack.push("World")
    stack.push("!")
    stack.display()
    println("Pop element: ${stack.pop()}")
    stack.display()
    println("Top element: ${stack.peek()}")
    println("Size: ${stack.size()}")
    println("isEmpty: ${stack.isEmpty()}")
}
