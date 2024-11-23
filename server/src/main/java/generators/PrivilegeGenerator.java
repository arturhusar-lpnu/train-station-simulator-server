package generators;

import exceptions.InvalidArgumentException;
import models.privileges.PrivilegeType;

import java.util.Random;

public class PrivilegeGenerator {
    private double probabilityOfPrivilege;

    public PrivilegeGenerator(double probabilityOfPrivilege) throws InvalidArgumentException {
        if (probabilityOfPrivilege < 0.0 || probabilityOfPrivilege > 1.0) {
            throw new InvalidArgumentException("Probability of privilege must be between 0.0 and 1.0");
        }

        this.probabilityOfPrivilege = probabilityOfPrivilege;
    }

    public PrivilegeType getPrivilege() {
        Random rand = new Random();

        if (rand.nextDouble() < probabilityOfPrivilege ? true : false)
            return PrivilegeType.fromInt(new Random().nextInt(1, 4));
        else
            return PrivilegeType.None;
    }
}
