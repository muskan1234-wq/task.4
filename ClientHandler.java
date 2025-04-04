import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private Server server;
    
    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            server.broadcastMessage("SERVER: " + clientUsername + " has joined the chat!", this);
        } catch (IOException e) {
            closeEverything();
        }
    }
    
    @Override
    public void run() {
        String messageFromClient;
        
        try {
            while (socket.isConnected()) {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient == null) {
                    break;
                }
                server.broadcastMessage(clientUsername + ": " + messageFromClient, this);
            }
        } catch (IOException e) {
            // Client disconnected
        } finally {
            closeEverything();
        }
    }
    
    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything();
        }
    }
    
    private void closeEverything() {
        server.removeClient(this);
        server.broadcastMessage("SERVER: " + clientUsername + " has left the chat!", this);
        
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 