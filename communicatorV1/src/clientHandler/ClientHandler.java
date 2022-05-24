package clientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket mClientSocket;
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;
    private String mUsername;

    public static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();

    public ClientHandler(Socket socket) {
        try {
            this.mClientSocket = socket;
            this.mBufferedWriter = new BufferedWriter(new OutputStreamWriter(mClientSocket.getOutputStream()));
            this.mBufferedReader = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            this.mUsername = mBufferedReader.readLine();

            clientHandlerArrayList.add(this);
            sendMessage(this.mUsername + " is new user");
            System.out.println("New client connected");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }
    }

    public void sendMessage(String message) {
        for (ClientHandler i : clientHandlerArrayList) {
            try {
                i.mBufferedWriter.write(message);
                i.mBufferedWriter.newLine();
                i.mBufferedWriter.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
            }
        }
    }

    public void disconnectClient() {
        sendMessage(this.mUsername + " disconnected");
        System.out.println("Client disconnected");
        clientHandlerArrayList.remove(this);
    }

    public void logOutUser(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        disconnectClient();
        try {
            if (socket != null)
                socket.close();
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        String input;

        while (mClientSocket.isConnected()) {
            try {
                input = mBufferedReader.readLine();
                if (input != null)
                    sendMessage(input);
                else {
                    disconnectClient();
                    break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
                break;
            }
        }
    }
}
