package clientHandler;

import message.Message;
import synchMessage.SynchMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private Socket mClientSocket;
    private ObjectInputStream mInputStream;
    private ObjectOutputStream mOutputStream;
    private String mUsername;
    private SynchMessage mOutput;

    public static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();

    public ClientHandler(Socket socket, SynchMessage synchMessage) {
        try {
            this.mClientSocket = socket;
            this.mOutputStream = new ObjectOutputStream(mClientSocket.getOutputStream());
            this.mInputStream = new ObjectInputStream(mClientSocket.getInputStream());
            this.mOutput = synchMessage;
            this.mOutput.add(mOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logOutUser(mClientSocket, mInputStream, mOutputStream);
        }
    }

    public void sendMessage(Message message) {
            try {
                if (message.name.equals("@first")) {
                    this.mUsername = message.message;
                    clientHandlerArrayList.add(this);
                    message.message = mUsername + " has joined\n";
                    message.name = "Server";
                }
                for (ClientHandler i : clientHandlerArrayList) {
                    if (i != this && (message.toWho.equals("To Everyone") || message.toWho.equals(i.mUsername))) {
                        for (int j = 0; j < mOutput.size(); j++) {
                            mOutput.get(j).writeObject(message);
                            mOutput.get(j).flush();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                logOutUser(mClientSocket, mInputStream, mOutputStream);
            }
    }

    public void disconnectClient() {
        clientHandlerArrayList.remove(this);
        String temp = this.mUsername + " log out\n";
        sendMessage(new Message(this.mUsername, temp));
    }

    public void logOutUser(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        disconnectClient();
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

    @Override
    public void run() {
        Message input;

        while (mClientSocket.isConnected()) {
            try {
                input = (Message) mInputStream.readObject();

                if(input != null) {
                    if(!Objects.equals(input.name, "@first"))
                        System.out.println(input.name + " send mess");
                    sendMessage(input);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                logOutUser(mClientSocket, mInputStream, mOutputStream);
                break;
            }
        }
    }
}
