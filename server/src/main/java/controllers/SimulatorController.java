package controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimulatorController {
    @MessageMapping("/start-simulation")
    @SendTo("/topic/simulation-status")
    public void startSimulation() {

    }
}
