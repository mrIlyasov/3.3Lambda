object ChatService {
    var users = emptyList<User>()


    fun findUserById(id: Int): User? {
        var user: User? = null
        if (users.size > 0) {
            for (index in users.indices) {
                if (id == users[index].id)
                    user = users[index]
            }
        }
        return user
    }

    fun addUser(user: User) {
        users += user.copy(id = users.size + 1)
    }

    fun printUsers() {
        for (index in users.indices) {
            println(users[index])
        }
    }


    fun getChatsOfUser(userId: Int): List<Chat> {
        var user = users.find {  it.id == userId }
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
        var user = users.find {  it.id == userId }
        if (user != null) {
            unreadChats = user.chats.filter { chat -> chat.unread == true }
        }

        return unreadChats
    }

    fun getUnreadChatsCount(userId: Int) = getChatsOfUser(userId).size


    fun sendMessage(senderId: Int, receiverId: Int, text: String) {
        var sender = users.find {  it.id == senderId }
        var receiver = users.find {  it.id == receiverId }
        if (sender != null && receiver != null) {
            sender.sendMessage(receiver, text)
        }
    }

    fun getChatById(userId: Int, receiverId: Int): Chat? {
        var chat: Chat? = null
        var user = users.find {  it.id == userId }
        var receiver = findUserById(receiverId)
        if (user != null && receiver != null) {
            chat = user.findChatWithUser(receiver)
        }
        return chat
    }

    fun readChatById(userId: Int, receiverId: Int) {
        var chat: Chat
        var user = users.find {  it.id == userId }
        var receiver = users.find {  it.id == receiverId }
        if (user != null && receiver != null) {
            chat = user.findChatWithUser(receiver)
            if (chat != null)
                user.readChat(chat)
            else println("Чат не найден")
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

}


