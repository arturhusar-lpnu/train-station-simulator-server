import generators.TicketSystem;

public class SimulatorApp {
    public static void main(String[] args) {
        TicketSystem ts = TicketSystem.getInstance();
        ts.startSystem();
    }
}
