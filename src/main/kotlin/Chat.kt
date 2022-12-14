class Chat(val id: Int, val owner: User, val receiver: User, var unread: Boolean = false) {
    var messages = mutableListOf<Message>()


    fun addMessage(text: String) {
        if (this.unread) {
            this.unread = false
        }
        val message: Message
        if (messages.size > 0) {
            message = Message(messages.last().id + 1, text, owner, receiver, false)
        } else {
            message = Message(1, text, owner, receiver, false)
        }
        messages.add(message)
        var receiverChat: Chat = receiver.findChatWithUser(owner)
        receiverChat.receiveMessage(message)
        receiverChat.unread = true
    }


    fun receiveMessage(message: Message) {
        messages.add(message.copy(incomming = true, unread = true))
    }


    fun findMessage(id: Int): Message? {
        return  messages.find { it.id == id }
    }


    fun findMessageIndex(id: Int): Int? {
        var indexToReturn: Int? = null
        indexToReturn = messages.find { it.id == id }?.id
        return indexToReturn
    }

    fun editMessage(id: Int, text: String) {
        var messageToEdit = findMessage(id)
        if (messageToEdit != null) {
            messageToEdit.text = text
            receiver.findChatWithUser(owner).editReceiverMessage(id, text)
        }
    }

    fun editReceiverMessage(id: Int, text: String) {
        var messageToEdit = findMessage(id)
        if (messageToEdit != null)
            messageToEdit.text = text
    }


    fun deleteMessage(id: Int) {
        var index = findMessageIndex(id)
        if (index != null) {
            deleteReceiverMessage(id)
            messages.removeAt(index)
            if (messages.size == 0) {
                owner.deleteChat(this.id)
            }
        }
    }

    fun deleteReceiverMessage(id: Int) {
        val message = findMessage(id)
        if (message != null) {
            val receiver = message.receiver
            if (receiver != null) {
                val chat = receiver.findChatWithUser(this.owner)
                if (chat != null) {
                    if (chat.findMessageIndex(id) != null) {
                        chat.deleteMessage(id)
                    }
                }
            }
        }
    }

    fun getUnreadMessages(): List<Message> {
        var unreadMessages = emptyList<Message>()
        unreadMessages = messages.filter { message -> message.unread == true }
        if (unreadMessages.size == 0) {
            this.unread = false
        }
        return unreadMessages
    }

    fun getUnreadMessagesCount() = getUnreadMessages().size


    fun printMessages() {
        println("????: ${owner.name} ???????????????? ??: ${receiver.name}")
        val printableMessages = messages.map { it.text }
            .joinToString(separator = "\n")
        println(printableMessages)
    }

    override fun toString(): String {
        return "$id, ????????????????????????: $owner, c ?????? ??????????????: $receiver, ${this.unread}"
    }


}