package com.simulation.generators;

import com.simulation.exceptions.InvalidArgumentException;
import com.simulation.models.privileges.PrivilegeType;

import java.util.Random;

public class PrivilegeGenerator {
    private final double probabilityOfPrivilege;

    public PrivilegeGenerator(double probabilityOfPrivilege) throws InvalidArgumentException {
        if (probabilityOfPrivilege < 0.0 || probabilityOfPrivilege > 1.0) {
            throw new InvalidArgumentException("Probability of privilege must be between 0.0 and 1.0");
        }

        this.probabilityOfPrivilege = probabilityOfPrivilege;
    }

    public PrivilegeType getPrivilege() {
        Random rand = new Random();

        if (rand.nextDouble() < probabilityOfPrivilege)
            return PrivilegeType.fromInt(new Random().nextInt(1, 4));
        else
            return PrivilegeType.None;
    }
}
