package com.pb.PasichnyiDima.hw13;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String[] args) {
        List<Message> msgs = new ArrayList<>();
        Reader reader = new Reader(msgs);
        Writer writer = new Writer(msgs);
        for (int i=0; i<5; i++) {
            new Thread(writer).start();
            new Thread(reader).start();
        }
    }
}
