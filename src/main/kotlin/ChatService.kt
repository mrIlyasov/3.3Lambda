object ChatService {
    var users = emptyList<User>()


    fun findUserById(id: Int): User? {
        var user: User? = null
        if (users.size > 0) {
            user = users.find { it.id == id }
        }

        return user
    }

    fun addUser(user: User) {
        if (users.size > 0) users += user.copy(id = users.last().id + 1)
        else users += user.copy(id = 1)
    }

    fun printUsers() {
        users.forEach { println(it) }
    }


    fun getChatsOfUser(userId: Int): List<Chat> {
        var user = users.find { it.id == userId }
        var chats = mutableListOf<Chat>()
        if (user != null) {
            if (user.chats.size > 0) {
                chats = user.chats
            }
        }
        return chats
    }

    fun getUnreadChatsOfUser(userId: Int): List<Chat> {
        var unreadChats: List<Chat> = emptyList()
        var user = users.find { it.id == userId }
        if (user != null) {
            unreadChats = user.chats.filter { chat -> chat.unread == true }
        }

        return unreadChats
    }

    fun getUnreadChatsCount(userId: Int) = getChatsOfUser(userId).size


    fun sendMessage(senderId: Int, receiverId: Int, text: String) {
        var sender = users.find { it.id == senderId }
        var receiver = users.find { it.id == receiverId }
        if (sender != null && receiver != null) {
            var receiverChat = receiver.findChatWithUser(sender)
            receiverChat.unread = true
            sender.sendMessage(receiver, text)
        }
    }

    fun getChatById(userId: Int, receiverId: Int): Chat? {
        var chat: Chat? = null
        var user = users.find { it.id == userId }
        var receiver = findUserById(receiverId)
        if (user != null && receiver != null) {
            chat = user.findChatWithUser(receiver)
        }
        return chat
    }

    fun readChatById(userId: Int, receiverId: Int) {
        var chat: Chat
        var user = users.find { it.id == userId }
        var receiver = users.find { it.id == receiverId }
        if (user != null && receiver != null) {
            chat = user.findChatWithUser(receiver)
            if (chat != null) {
                user.readChat(chat)
                chat.messages.filter { it.receiver == receiver }.asSequence().forEach { it.unread = false }
            } else println("Чат не найден")
        }
    }


    fun readMessage(userId: Int, receiverId: Int, messageId: Int) {
        var chat = getChatById(userId, receiverId)
        if (chat != null) {
            var message = chat.findMessage(messageId)
            if (message != null) {
                message.unread = false
            }
        }
    }


    fun clear() {
        var emptyMutableList = mutableListOf<User>()
        this.users = emptyMutableList
    }

}


