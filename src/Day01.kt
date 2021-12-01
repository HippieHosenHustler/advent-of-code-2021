fun main() {

    fun parseList(input: List<String>): List<Int> {
        return input.map { it.toInt() }
    }

    fun getIncreases(input: List<Int>): Int {
        val iterator = input.listIterator()
        var counter = 0


        while (iterator.hasNext()) {
            val next = input[iterator.nextIndex()]
//            println("next:: $next")
            if (iterator.hasPrevious()) {
                val previous = input[iterator.previousIndex()]
//                println("previous:: $previous")
                if (next > previous) {
                    counter++
                }
            }
            iterator.next()
        }

        return counter
    }

    fun getBlockIncreases(input: List<Int>): Int {
        val iterator = input.listIterator()

        val blockList = mutableListOf<Int>()

        while (iterator.hasNext() && iterator.nextIndex() <= (input.size - 3)) {
            val nextIndex = iterator.nextIndex()
            val block = input[nextIndex] + input[nextIndex + 1] + input[nextIndex + 2]
            blockList.add(block)
            iterator.next()
        }

        return getIncreases(blockList)
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    val intList = parseList(input)
    println(getIncreases(intList))
    println(getBlockIncreases(intList))
}
