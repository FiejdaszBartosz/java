package client;

import clientHandler.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket mClientSocket;
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;
    private String mUsername;
    public String outputMessage;

    public Client(Socket socket, String username) {
        try {
            this.mClientSocket = socket;
            this.mBufferedWriter = new BufferedWriter(new OutputStreamWriter(mClientSocket.getOutputStream()));
            this.mBufferedReader = new BufferedReader(new InputStreamReader(mClientSocket.getInputStream()));
            this.mUsername = username;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }
    }

    public void sendMessage(String message) {
        try {
            mBufferedWriter.write(mUsername);
            mBufferedWriter.newLine();
            mBufferedWriter.flush();

            while (mClientSocket.isConnected()) {
                mBufferedWriter.write("[" + mUsername + "] " + message);
                mBufferedWriter.newLine();
                mBufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
        }
    }

    public void waitForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String input;
                try {
                    outputMessage =  mBufferedReader.readLine();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    logOutUser(mClientSocket, mBufferedReader, mBufferedWriter);
                }
            }
        }).start();
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
}
