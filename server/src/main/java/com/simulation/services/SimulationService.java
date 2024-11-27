package com.simulation.services;

import com.simulation.controllers.TicketSystemInitializer;
import com.simulation.generators.TicketSystem;
import com.simulation.generators.TicketSystemConfig;
import com.simulation.models.WorkingTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
        private TicketSystemInitializer ticketSystemInitializer;
        private TicketSystem ticketSystem;
        private WorkingTimer workingTimer;

        @Autowired
        public SimulationService(TicketSystemInitializer ticketSystemInitializer) {
            this.ticketSystemInitializer = ticketSystemInitializer;
        }

        public synchronized void startSimulation(TicketSystemConfig config) {
            ticketSystem = ticketSystemInitializer.initializeTicketSystem(config);
            workingTimer = new WorkingTimer(config.getDurationOfDay(), ticketSystem);
            workingTimer.startTimer();
        }

        public synchronized void stopSimulation() {
            if (workingTimer != null) {
                workingTimer.stopTimer();
            }
        }
}
