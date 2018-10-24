import java.math.BigDecimal

fun main(args: Array<String>) {
    beerCalculator {
        repeat(4) {item("Гримберген Блонд", 1980/6) }
        repeat(2) {item("Гримберген Дабл Амбре", 1320/4) }
        applyDiscount(30)
        item("Бургер", 2160/4)
        total()
    }
}

fun beerCalculator(function: BeerCalculator.() -> Unit) = BeerCalculator().apply(function)

class BeerCalculator {
    private var items = mutableListOf<Item>()
    fun item(title: String, sum: Int) = items.add(Item(title, BigDecimal(sum)))
    fun item(title: String, sum: Double) = items.add(Item(title, BigDecimal(sum)))
    fun applyDiscount(discount: Int) = items.forEach { it.sum = it.sum.multiply(
        BigDecimal(100 - discount).divide(
            BigDecimal(100)
        )) }
    fun total() {
        var total = BigDecimal.ZERO
        items.forEach {
            println("${it.title}\t${it.sum}")
            total = total.add(it.sum)
        }
        println("Total: $total")
    }
}

data class Item(val title: String, var sum: BigDecimal)
