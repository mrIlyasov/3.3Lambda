import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun getUnreadChatsOfUser() {
        var serviceTest = ChatService
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        serviceTest.addUser(user)
        serviceTest.addUser(user2)
        serviceTest.addUser(user3)
        serviceTest.sendMessage(1, 2, "HI")
        serviceTest.sendMessage(3, 2, "HI")
        var unreadChats = serviceTest.getUnreadChatsOfUser(2)
        assertEquals(2, unreadChats.size)
    }


    @Test
    fun readChatById() {
        var serviceTest = ChatService
        var user: User = User(1, "Kolya")
        var user2: User = User(2, "Vasya")
        var user3: User = User(3, "Petya")
        serviceTest.addUser(user)
        serviceTest.addUser(user2)
        serviceTest.addUser(user3)
        serviceTest.sendMessage(1, 2, "HI")
        serviceTest.sendMessage(3, 2, "HI")
        serviceTest.readChatById(2, 1)
        var unreadChats = serviceTest.getUnreadChatsOfUser(2)
        assertEquals(1, unreadChats.size)
    }
}