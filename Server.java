import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            System.out.println("Waiting for clients...");
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
            System.exit(1);
        }
    }
    
    public void start() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);
                
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clients.add(clientHandler);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }
    
    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
    
    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
    
    private void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        server.start();
    }
} 