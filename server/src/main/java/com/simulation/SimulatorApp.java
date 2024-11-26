package com.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.simulation"})
public class SimulatorApp {
    public static void main(String[] args) {
        SpringApplication.run(SimulatorApp.class, args);
    }
    private static int AddOne() {
        //Volodya sonce
        return 1;
    }
}

