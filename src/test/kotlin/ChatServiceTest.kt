import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }


    @Test
    fun getUnreadChatsOfUser() {
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        ChatService.addUser(user)
        ChatService.addUser(user2)
        ChatService.addUser(user3)
        ChatService.sendMessage(1, 2, "HI")
        ChatService.sendMessage(3, 2, "HI")
        var unreadChats = ChatService.getUnreadChatsOfUser(2)
        assertEquals(2, unreadChats.size)
    }


    @Test
    fun readChatById() {
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        ChatService.addUser(user)
        ChatService.addUser(user2)
        ChatService.addUser(user3)
        ChatService.sendMessage(1, 2, "HI")
        ChatService.sendMessage(3, 2, "HI")
        ChatService.readChatById(2, 1)
        var unreadChats = ChatService.getUnreadChatsOfUser(2)
        assertEquals(1, unreadChats.size)
    }

    @Test
    fun readMessagesInReadChat() {
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        ChatService.addUser(user)
        ChatService.addUser(user2)
        ChatService.addUser(user3)
        ChatService.sendMessage(1, 2, "HI")
        ChatService.sendMessage(1, 2, "HI2")
        ChatService.sendMessage(1, 2, "HI3")
        ChatService.sendMessage(1, 2, "HI4")
        ChatService.readChatById(2, 1)
        var chat = ChatService.getChatById(2, 1)
        ChatService.readChatById(2, 1)
        assertEquals(false, chat!!.findMessage(1)!!.unread)


    }

    @Test
    fun findUserById() {
        ChatService.addUser(User(1, "Kolya"))
        ChatService.addUser(User(2, "Sasha"))
        ChatService.addUser(User(3, "Borya"))
        assertEquals(User(1, "Kolya"), ChatService.findUserById(1))
    }

    @Test
    fun sendMessage() {
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        ChatService.addUser(user)
        ChatService.addUser(user2)
        ChatService.addUser(user3)
        ChatService.sendMessage(1, 2, "HI")
        val messageText = ChatService.findUserById(2)!!.chats[0].findMessage(1)!!.text
        assertEquals("HI", messageText)

    }

    @Test
    fun readMessage() {

        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        ChatService.addUser(user)
        ChatService.addUser(user2)
        ChatService.addUser(user3)
        ChatService.sendMessage(1, 2, "HI")
        ChatService.sendMessage(3, 2, "HI")
        ChatService.readMessage(2, 1, 1)
        var chat = ChatService.findUserById(2)?.chats?.find { it.receiver == user }
        var message = chat?.findMessage(1)
        assertEquals(false, message?.unread)
    }


}