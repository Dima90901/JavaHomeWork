package com.pb.PasichnyiDima.hw13;

import java.util.List;

public class Writer implements Runnable {
    private final List<Message> msgs;

    public Writer(List<Message> msgs) {
        this.msgs = msgs;
    }

    @Override
    public void run() {
        synchronized (msgs) {
            try {
                if (!msgs.isEmpty()) {
                    msgs.wait();
                }
                if (msgs.isEmpty()) {
                    msgs.add(new Message("Помещаем данные 1 раз"));
                    msgs.add(new Message("Помещаем данные 2 раз"));
                    msgs.add(new Message("Помещаем данные 3 раз"));
                    msgs.add(new Message("Помещаем данные 4 раз"));
                    msgs.add(new Message("Помещаем данные 5 раз"));
                    for (Message m : msgs) {
                        System.out.println("Поток writer: " + m.toString());
                    }
                    msgs.wait();
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
