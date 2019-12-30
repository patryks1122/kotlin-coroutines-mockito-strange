package basics

import kotlinx.coroutines.delay

// name is a param
// msg is a local param

suspend
fun hello(name: String = "world") : String { // label = 0
    var msg = "Hello"
    print(msg)
    delay(1_000) // label = 1
    msg += " $name"
    print(" $name")
    delay(5_00) // label = 2
    msg += "!"
    println("!")
    return msg
}