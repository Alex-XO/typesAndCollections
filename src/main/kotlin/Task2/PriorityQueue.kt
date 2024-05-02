package Task2

/*
The PriorityQueue<T> class for managing elements with different priorities.
The items with the highest priority must be retrieved from the queue first.
 */

data class PriorityQueueItem<T>(val value: T, val priority: Int)

class PriorityQueue<T> {
    private val heap = mutableListOf<PriorityQueueItem<T>>()

    fun enqueue (value: T, priority: Int) {
        heap.add(PriorityQueueItem(value, priority))
        heap.sortBy { it.priority }
    }

    fun dequeue (): T? {
        if (isEmpty()) {
            println("Queue is empty")
            return null
        }
        return heap.removeAt(0).value
    }

    fun peek (): T? {
        return heap.firstOrNull()?.value
    }

    fun isEmpty(): Boolean {
        return heap.isEmpty()
    }
}

fun main() {
    val queue = PriorityQueue<String>()
    queue.enqueue("Low priority", priority = 5)
    queue.enqueue("Medium priority", priority = 2)
    queue.enqueue("High priority", priority = 1)

    println("First: ${queue.dequeue()}")
    println("Next: ${queue.dequeue()}")
    println("Last: ${queue.peek()}")
}