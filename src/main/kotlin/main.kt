fun main() {
    var service = ChatService
    service.addUser(User(1, "Vasya"))
    service.addUser(User(100, "Petya"))
    service.addUser(User(1200, "Vanya"))
    service.users.print()
    println()
    service.sendMessage(1, 2, "Vasya пишет Petyе 1")
    service.sendMessage(2, 1, "Petya пишет Vasyе 1")
    service.sendMessage(1, 2, "Vasya пишет Petyе 2")
    service.sendMessage(2, 1, "Petya пишет Vasyе 2")
    service.users[0].chats[0].messages.print()
    println()

    val chat = service.getChatById(1, 2)
    chat!!.deleteMessage(1)
    chat.editMessage(2, "Petya пишет Vasye 1 исправлено")
    chat.printMessages()
    println()


}


fun <E> List<E>.print() {
    if (this.size > 0) {
        for (index in this.indices) {
            println(this[index])
        }
    } else println("empty")
}

