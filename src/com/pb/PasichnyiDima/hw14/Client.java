package com.pb.PasichnyiDima.hw14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class Client extends JFrame implements ActionListener, NetworkListener {
    private final String IP_ADDR = "127.0.0.1";
    private final int port = 1059;
    private final int width = 600;
    private final int height = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });
    }

    private final JTextArea log = new JTextArea();
    private final JTextField name = new JTextField();
    private final JTextField input = new JTextField();
    private Network connect;

    private Client () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);


        log.setEditable(false);
        log.setLineWrap(true);

        add(log, BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
        add(name, BorderLayout.NORTH);

        input.addActionListener(this);

        setVisible(true);
        try {
            connect = new Network(this, IP_ADDR,port);
        } catch (IOException e) {
            printMsg("Connection exception: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = input.getText();
        if (msg.equals("")) return;
        connect.sendMesage(name.getText() + ": " + msg + ", date message: " + LocalDateTime.now());
    }

    @Override
    public void onConnetctionReady(Network net) {
        printMsg("Connection ready");
    }

    @Override
    public void onReciveString(Network net, String str) {
        printMsg(str);
    }

    @Override
    public void onDisconnect(Network net) {
        printMsg("Connection close");
    }

    @Override
    public void onExceptoin(Network net, Exception e) {
        printMsg("Connection exception: " + e);
    }
    private synchronized void printMsg (String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
