package com.pb.PasichnyiDima.hw14;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements NetworkListener{
    public static void main(String[] args) {
        new Server();
    }

    private final ArrayList<Network> connections = new ArrayList<>();

    private Server () {
        System.out.println("Server running");
        try (ServerSocket serverSocket = new ServerSocket(1059)) {
            while (true) {
                try {
                    new Network(serverSocket.accept(), this);
                }
                catch (IOException e) {
                    System.out.println("Connection exce[topn: " + e);
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public synchronized void onConnetctionReady(Network net) {
        connections.add(net);
        sendToAllClients("Client connetcted: " + net);
    }

    @Override
    public synchronized void onReciveString(Network net, String str) {
        sendToAllClients(str);
    }

    @Override
    public synchronized void onDisconnect(Network net) {
        connections.remove(net);
        sendToAllClients("Client disconnetcted: " + net);
    }

    @Override
    public synchronized void onExceptoin(Network net, Exception e) {
        System.out.println("Connection exception: " + e);
    }
    private void sendToAllClients (String msg) {
        System.out.println(msg);
        for (int i = 0; i < connections.size(); i++) connections.get(i).sendMesage(msg);
    }
}
