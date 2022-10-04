data class User(val id: Int, var name: String) {
    var chats = mutableListOf<Chat>()

    override fun toString(): String {
        return "$id, $name"
    }

    fun sendMessage(receiver: User, text: String) {
        var chat: Chat = findChatWithUser(receiver)
        chat.addMessage(text)
        val message = chat.messages.last()
    }


    fun findChatWithUser(user: User): Chat {
        var chatToReturn: Chat? = null
        for (index in chats.indices) {
            if (user == chats[index].receiver) {
                chatToReturn = chats[index]

            }
        }
        if (chatToReturn == null) {
            chatToReturn = newChatWithUser(user)
        }
        return chatToReturn
    }

    fun newChatWithUser(user: User): Chat {
        chats.add(Chat(this.chats.size + 1, owner = this, receiver = user, unread = true))
        return chats.last()
    }



    fun readChat(chat: Chat) {
        chat.unread = false
        for (index in chat.messages.indices) {
            chat.messages[index].unread = false
        }
    }


    fun readMessage(message: Message) {
        message.unread = false
    }

    fun deleteChat(chatId: Int) {
        var chatToDeleteIndex: Int? = null
        for (index in chats.indices) {
            if (chats[index].id == chatId) {
                chatToDeleteIndex = index
            }
        }
        if (chatToDeleteIndex != null) {
            chats.removeAt(chatToDeleteIndex)
        }
    }


    fun printChats() {
        for (index in chats.indices) {
            println(chats[index])
        }
    }




}
