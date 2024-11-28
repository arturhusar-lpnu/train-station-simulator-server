package com.simulation.services;

import com.simulation.models.TicketSystem;
import com.simulation.config.TicketSystemConfig;
import com.simulation.models.WorkingTimer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {
        private TicketSystemInitializer ticketSystemInitializer;
        @Getter
        private TicketSystem ticketSystem;
        @Getter
        private WorkingTimer workingTimer;

        @Autowired
        public SimulationService(TicketSystemInitializer ticketSystemInitializer) {
            this.ticketSystemInitializer = ticketSystemInitializer;
        }

        public synchronized void startSimulation(TicketSystemConfig config) {
            ticketSystem = ticketSystemInitializer.initializeTicketSystem(config);
            workingTimer = new WorkingTimer(config.getDurationOfDay(), ticketSystem);
            workingTimer.startTimer();
            //add start-event
        }

        public synchronized void stopSimulation() {
            if (workingTimer != null) {
                workingTimer.stopTimer();
            }
        }
}
