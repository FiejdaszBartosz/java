package client;

import clientHandler.ClientHandler;
import message.Message;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket mClientSocket;
    private ObjectInputStream mInputStream;
    private ObjectOutputStream mOutputStream;
    private String mUsername;
    public String outputMessage;

    public Client(Socket socket, String username) {
        try {
            this.mUsername = username;
            this.mClientSocket = socket;
            this.mOutputStream = new ObjectOutputStream(mClientSocket.getOutputStream());
            this.mInputStream = new ObjectInputStream(mClientSocket.getInputStream());
            sendMessage(new Message("@first",mUsername));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mInputStream, mOutputStream);
        }
    }

    public void sendMessage(Message message) {
        try {
            mOutputStream.writeObject(message);
            mOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mInputStream, mOutputStream);
        }
    }

    public Message waitForMessage() {
        Message input;
            input = null;
            try {
                input = (Message) mInputStream.readObject();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                logOutUser(mClientSocket, mInputStream, mOutputStream);
            }

            return input;
    }

    public void logOutUser(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        try {
            if (socket != null)
                socket.close();
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logOutUser() {
        try {
            if (mClientSocket != null)
                mClientSocket.close();
            if (mInputStream != null)
                mInputStream.close();
            if (mOutputStream != null)
                mOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
