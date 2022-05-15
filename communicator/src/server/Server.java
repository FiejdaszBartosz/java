package server;

import clientHandler.ClientHandler;
import synchMessage.SynchMessage;

import java.io.IOException;
import java.net.*;

public class Server implements IServer {
    private ServerSocket mServerSocket;
    private SynchMessage mOutput;

    /**
     * Creates server on specify port
     *
     * @param port given port
     */
    public Server(int port) throws IOException {
        this.mServerSocket = new ServerSocket(port);
        this.mOutput = new SynchMessage();
    }

    public void startServer() throws IOException {
        while (!mServerSocket.isClosed()) {
            Socket socket = mServerSocket.accept();
            System.out.println("New client");
            ClientHandler clientHandler = new ClientHandler(socket, mOutput);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

    /**
     * Stops server
     */
    public void stopServer() {
        try {
            if (mServerSocket != null)
                this.mServerSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = null;

        try {
            server = new Server(6666);
            server.startServer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (server != null)
            server.stopServer();
    }
}
