data class Message(
    val id: Int,
    var text: String,
    val sender: User,
    val receiver: User,
    val incomming: Boolean,
    var unread: Boolean = false
) {


    override fun toString(): String {
        if (incomming)
            return "входящее: $text "
        else
            return "                           отправленное: $text "
    }
}