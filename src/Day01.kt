fun main() {
    fun part1(input: List<String>): Int {
        val intList = input.map { it.toInt() }
        val iterator = intList.listIterator()
        var counter = 0


        while (iterator.hasNext()) {
            val next = intList[iterator.nextIndex()]
            println("next:: $next")
            if (iterator.hasPrevious()) {
                val previous = intList[iterator.previousIndex()]
                println("previous:: $previous")
                if (next > previous) {
                    counter++
                }
            }
            iterator.next()
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
