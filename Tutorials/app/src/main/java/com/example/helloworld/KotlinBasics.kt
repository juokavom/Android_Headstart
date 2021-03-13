package com.example.helloworld

fun main() {
    var denis = Person("Denis", "Panjuta", 31)
    var jokubas = Person("Jokubas")
    var unnamed = Person()
    unnamed.stateHobby()
    var anthony = Person("Anthony", "Kiedis")
//    for(el in 10 downTo 1 step 2) println(el)
    anthony.Sub()
}

class Person(firstname: String = "John", lastname: String = "Doe") {
    var age: Int = 18
        private set(value){
            field = if(value > 0) value else throw IllegalArgumentException("Age must be positive")
        }
    inner class Sub(address: String = "Maple street"){
        init {
            println("Inner class, out class is $firstname")
        }
    }
    private var firstname: String? = null
    var hobby: String = "Watch Netflix"

    init {
        this.firstname = firstname
        println("Object initialized with fname = $firstname " +
                "and lname = $lastname")
    }

    constructor(firstname: String, lastname: String, age: Int) : this(firstname, lastname) {
        this.age = age
    }

    fun stateHobby(){
        println("$firstname hobby is $hobby")
    }
}