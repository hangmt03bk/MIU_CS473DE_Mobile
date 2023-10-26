//------------3a--------------------
fun printLastFirst(num: Int) {
    val s = num.toString()
    println("${s.first()}${s.last()}")
}

//------------3b--------------------
fun sumOddSqrt(arr: Array<Int>): Int {
    var sum = 0
    for(num in arr) {
        if(num % 2 == 1) sum += num*num
    }
    return sum
}

//------------3c--------------------
fun getWeight(w: Double, planet: Int): Double {
    var relGrav: Double = when (planet){
        1 -> 0.78
        2 -> 0.39
        3 -> 2.65
        4 -> 1.17
        5 -> 1.05
        6 -> 1.23
        else -> 1.0
    }
    return w*relGrav
}

//------------4--------------------
open class Book (var title: String, var author: String, var price: Double) {
    open fun read() {
        println("Reading Paper book")
    }
}

class EBook(title: String, author: String, price: Double,
            var pdf: String, var epub: String):Book (title, author, price) {
   
    override fun read() {
        println("Read from Electronic Device")
    }
}
//------------main--------------------
fun main() {
    val sc = Scanner(System.`in`)
    val num = sc.nextInt()
    //val num = 12345

    print("3a. Print the last digit and first digit of $num -> ")
    printLastFirst(num)
    
    val arr = arrayOf(1,2,3,4,6,5)
    val sum = sumOddSqrt(arr)
    println("3b. Sum of odd squared values in ${arr.contentToString()} -> $sum")
    
    val w = 50.0; val planet = 2
    val weight: Double = getWeight(w, planet)
    println("3c. The weight of a person ($w) in planet $planet -> $weight")
    
    println("4. Practice OO Concepts")
    val book = Book("Kotlin in Action", "Dmitry Jemerov", 66.66)
    book.read()
    println("Book title: ${book.title}")
    val ebook = EBook("Kotlin in Action", "Dmitry Jemerov", 66.66,"Kotlin.pdf","Kotlin.epub")
    ebook.read()
    println("EBook author: ${ebook.author}")
    
    
}