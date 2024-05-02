package Task3

/*
The filterByType<T> function,
which takes a list of Any objects and returns a list
of objects that are instances of type T.
 */

inline fun <reified T> filterByType(list: List<Any>): List<T> {
    val filteredList = mutableListOf<T>()
    for (item in list) {
        if (item is T) {
            filteredList.add(item)
        }
    }
    return filteredList
}

fun main() {
    val mixedList = listOf<Any>("More", 666, 0, "eroM", 21)

    val stringList: List<String> = filterByType(mixedList)
    println("Strings: $stringList")

    val intList: List<Int> = filterByType(mixedList)
    println("Integers: $intList")
}