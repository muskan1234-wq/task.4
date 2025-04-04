"COMPANY" :CODTECH IT SOLOTIONS

"NAME" : PATHAN MUSKAN IBRAHIM

"INTERN ID" : CT04WZ15

"DOMAIN" : JAVA

"DURATION" : 4 WEEKS

"MENTOR" : NEELA SANTOSH

# Multithreaded Chat Application

This is a simple client-server chat application built using Java sockets and multithreading.

## Features

- Server can handle multiple client connections simultaneously
- Real-time messaging between clients
- Server broadcasts messages to all connected clients
- Notification when users join or leave the chat

## How to Run

1. Compile all Java files:
   ```
   javac Server.java ClientHandler.java Client.java
   ```

2. Start the server:
   ```
   java Server
   ```

3. In separate terminal windows, start multiple clients:
   ```
   java Client
   ```

4. For each client, enter a username when prompted.

5. Start chatting! All messages will be broadcast to all connected clients.

## Usage

- Type messages in the client terminal and press Enter to send
- To disconnect a client, close the terminal window or press Ctrl+C

## Implementation Details

- **Server**: Accepts client connections and uses multithreading to handle multiple clients
- **ClientHandler**: Manages each client connection in its own thread
- **Client**: Connects to the server and provides a simple console interface for sending/receiving messages

## Requirements

- Java Development Kit (JDK) 8 or higher 
