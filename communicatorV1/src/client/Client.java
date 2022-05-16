package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket mClientSocket;
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;
    private String mUsername;

    public Client(Socket socket, String username) {
        try {
            this.mClientSocket = socket;
            this.mUsername = username;
            this.mBufferedWriter = new BufferedWriter(new OutputStreamWriter(mClientSocket.getOutputStream()));
            this.mBufferedReader = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            sendMessage(mUsername);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }
    }

    public void sendMessage(String message) {
        try {
                mBufferedWriter.write("[" + mUsername + "] " + message);
                mBufferedWriter.newLine();
                mBufferedWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }
    }

    public String waitForMessage() {
        String input;
        input = null;
        try {
            input = mBufferedReader.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }

        return input;
    }

    public void logOutUser(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
    public void logOutUser() {
        try {
            if (mClientSocket != null)
                mClientSocket.close();
            if (mBufferedReader != null)
                mBufferedReader.close();
            if (mBufferedWriter != null)
                mBufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
