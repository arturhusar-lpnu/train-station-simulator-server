package com.simulation.event_listeners.log;

import com.simulation.events.Event;
import com.simulation.events.MoveEvent;

import java.io.FileWriter;
import java.io.IOException;

public class MoveLogListener extends LogListener{
    public MoveLogListener (String fileName) { super(fileName); }

    @Override
    public void update(Event event) {
        if(event instanceof MoveEvent) {
            try (FileWriter writer = new FileWriter(super.log, true)) {
                writer.write(event.convert() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}