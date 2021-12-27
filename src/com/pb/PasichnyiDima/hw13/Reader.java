package com.pb.PasichnyiDima.hw13;

import java.util.List;

public class Reader implements Runnable {
    private final List<Message> msgs;

    public Reader(List<Message> msgs) {
        this.msgs = msgs;
    }


    @Override
    public void run() {
        synchronized (msgs) {
            try {
                if (msgs.isEmpty()) {
                    msgs.wait();
                }
                if (!msgs.isEmpty()) {
                        for (Message m: msgs) {
                            System.out.println("Данные, которые считалсь: " + m.getMsg());
                        }
                    msgs.clear();
                    //msgs.wait();
                }
            }
            catch (Exception e) {
                e.getStackTrace();
            }
            finally {
                msgs.notifyAll();
            }
        }
    }
}
