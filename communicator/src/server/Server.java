package server;

import clientHandler.ClientHandler;

import java.io.IOException;
import java.net.*;

public class Server implements IServer {
    private ServerSocket mServerSocket;

    /**
     * Creates server on specify port
     * @param port given port
     */
    public Server(int port) throws IOException {
        this.mServerSocket = new ServerSocket(port);
    }

    public void startServer() throws IOException {
        while (!mServerSocket.isClosed()) {
            Socket socket = mServerSocket.accept();
            System.out.println("New client");
            ClientHandler clientHandler = new ClientHandler(socket);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

    /**
     * Stops server
     */
    public void stopServer() {
        try {
            if(mServerSocket != null)
                this.mServerSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
