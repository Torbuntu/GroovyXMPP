package io.github.torbuntu

class Main {
    static void main(String[] args) {
        def running = true, login = false
        GroovyClient client
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                if (!login) {
                    println("Login required.")
                    print("Domain: ")
                    def domain = scanner.nextLine()
                    print("Username: ")
                    def user = scanner.nextLine()
                    print("Password: ")
                    def pass = scanner.nextLine()
                    println("Logging in...")
                    client = new GroovyClient(domain, user, pass)
                    client.login()
                    if (client.getConnection().isConnected()) {
                        println("Connected. Continue to send message.")
                        login = true
                    }
                } else {
                    print("To: ")
                    def to = scanner.nextLine()
                    print("Message: ")
                    def message = scanner.nextLine()
                    println("Sending message...")
                    client.send(message, to)
                }
            }
        }
    }
}