package io.github.torbuntu

import org.jivesoftware.smack.chat2.Chat
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.jid.EntityBareJid
import org.jxmpp.jid.impl.JidCreate

class GroovyClient {

    XMPPTCPConnection connection
    def config

    GroovyClient(String domain, user, pass) {
        // Create a connection to the jabber.org server on a specific port.
        config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(user, pass)
                .setXmppDomain(domain)
                .build()
        connection = new XMPPTCPConnection(config)
    }

    void login() {
        connection.connect().login()
    }

    void send(String message, String to) {
        // Assume we've created an XMPPConnection name "connection"._
        ChatManager chatManager = ChatManager.getInstanceFor(connection)
        EntityBareJid jid = JidCreate.entityBareFrom(to)
        Chat chat = chatManager.chatWith(jid)
        chat.send(message)
    }
}
