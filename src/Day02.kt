fun main() {

    fun makeResultMap(): MutableMap<String, Int> {
        val map = mutableMapOf<String, Int>()
        map.put("horizontal", 0)
        map.put("depth", 0)
        return map
    }

    fun getPostion(input: List<String>): Map<String, Int> {
        val map = makeResultMap()

        for (line in input) {
            val split = line.split(' ')
            val number = split[1].toInt()

            when (split[0]) {
                "forward" -> {
                    val current = map["horizontal"]
                    if (current != null) {
                        map["horizontal"] = current + number
                    }
                }
                "down" -> {
                    val current = map["depth"]
                    if (current != null) {
                        map["depth"] = current + number
                    }
                }
                "up" -> {
                    val current = map["depth"]
                    if (current != null) {
                        map["depth"] = current - number
                    }
                }
            }
        }

        return map
    }

    fun calculateResult(map: Map<String, Int>): Int? {
        return map["horizontal"]?.times(map["depth"]!!)

    }

    fun getPositionWithAim(input: List<String>): Int? {
        val resultMap = makeResultMap()

        var aim = 0

        for (line in input) {
            val split = line.split(' ')
            val number = split[1].toInt()

            when (split[0]) {
                "forward" -> {
                    val currentHorizontal = resultMap["horizontal"]
                    if (currentHorizontal != null) {
                        resultMap["horizontal"] = currentHorizontal + number
                    }

                    val currentDepth = resultMap["depth"]
                    if (currentDepth != null) {
                        resultMap["depth"] = currentDepth + (number * aim)
                    }

                }
                "down" -> {
                    aim += number
                }
                "up" -> {
                    aim -= number
                }
            }
        }

        return calculateResult(resultMap)

    }

    val input = readInput("Day02")
    val map = getPostion(input)
    val part1 = calculateResult(map)
    println("Part 1:: $part1")
    val part2 = getPositionWithAim(input)
    println("Part 2:: $part2")

}