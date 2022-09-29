fun main() {
    var service = ChatService
    service.addUser(User(1, "Vasya"))
    service.addUser(User(100, "Petya"))
    service.addUser(User(1200, "Vanya"))
    service.users.print()

    service.sendMessage(1, 2, "Vasya пишет Petyе 1")
    service.sendMessage(2, 1, "Petya пишет Vasyе 1")
    service.users[0].chats.print()
    service.users[0].deleteChat(1)
    println()
    service.users[0].chats.print()
}

fun <E> List<E>.print() {
    if (this.size>0){
    for (index in this.indices) {
        println(this[index])
    }
    }
    else println("empty")
}

