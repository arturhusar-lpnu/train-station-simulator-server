package events;

import generators.TicketSystem;

class EndSystemEvent implements Event {
    private TicketSystem system;

    public EndSystemEvent(TicketSystem system) {
        this.system = system;
    }

    @Override
    public String convert() {
        return "System ended: " + system.toString();
    }
}