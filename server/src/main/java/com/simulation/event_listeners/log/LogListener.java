package com.simulation.event_listeners.log;

import com.simulation.event_listeners.EventListener;
import com.simulation.events.Event;

import java.io.File;

public abstract class LogListener implements EventListener {
    protected final File log;

    public LogListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(Event event) {
        System.out.println("Save to log " + log);
    }
}
