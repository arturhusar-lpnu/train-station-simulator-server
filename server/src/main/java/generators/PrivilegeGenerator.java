package generators;

import models.privileges.PrivilegeType;

import java.util.Random;

public class PrivilegeGenerator {
    private double probabilityOfPrivilege;

    public PrivilegeGenerator(double probabilityOfPrivilege) {
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
