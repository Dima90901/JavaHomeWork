package com.pb.PasichnyiDima.hw14;

public interface NetworkListener {
    void onConnetctionReady(Network net);
    void onReciveString(Network net, String str);
    void onDisconnect(Network net);
    void onExceptoin(Network net, Exception e);
}
