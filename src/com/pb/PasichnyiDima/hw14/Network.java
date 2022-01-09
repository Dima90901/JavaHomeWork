package com.pb.PasichnyiDima.hw14;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class Network {
    private final Socket socket;
    private final Thread thread;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final NetworkListener listener;

    public Network (NetworkListener listener, String ipAddr, int port) throws IOException {
        this(new Socket(ipAddr, port), listener);
    }


    public Network (Socket socket, NetworkListener listener) throws IOException {
        this.listener = listener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listener.onConnetctionReady(Network.this);
                    while (!thread.isInterrupted()) {
                        listener.onReciveString(Network.this, in.readLine());
                    }
                }
                catch (IOException ex) {
                    listener.onExceptoin(Network.this, ex);
                }
                finally {
                    listener.onDisconnect(Network.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void sendMesage (String msg) {
        try {
            out.write(msg + "\r\n");
            out.flush();
        } catch (IOException e) {
            listener.onExceptoin(Network.this, e);
            disconnetect();
        }
    }

    public synchronized void disconnetect () {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            listener.onExceptoin(Network.this, e);
        }
    }

    @Override
    public String toString() {
        return "Connection: " + socket.getInetAddress() + ": " + socket.getPort();
    }
}
