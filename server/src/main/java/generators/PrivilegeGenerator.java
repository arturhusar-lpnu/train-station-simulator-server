package generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.privileges.CommonPrivilege;
import models.privileges.PrivilegeCategory;
import models.privileges.PrivilegeType;
import models.privileges.decorators.BeneficiaryPrivilege;
import models.privileges.decorators.DisabledPrivilege;
import models.privileges.decorators.MilitaryPrivilege;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
public class PrivilegeGenerator {
    private double probabilityOfPrivilege;
    private double probabilityOfSeveralPrivileges;
    private int minPrivilegesCount;
    private int maxPrivilegesCount;

    public List<PrivilegeCategory> getPrivilegeList() {
        Random rand = new Random();
        int size;
        if (rand.nextDouble() < probabilityOfSeveralPrivileges) {
            size = rand.nextInt(maxPrivilegesCount - minPrivilegesCount + 1) + minPrivilegesCount;
        } else {
            size = rand.nextDouble() < probabilityOfPrivilege ? 1 : 0;
        }

        List<PrivilegeCategory> privilegeList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            privilegeList.add(getRandomPrivilege());
        }
        return privilegeList;
    }

    public PrivilegeCategory getRandomPrivilege() {
        Random rand = new Random();
        int randPriv = rand.nextInt(3);
        PrivilegeType privilegeType = PrivilegeType.fromInt(randPriv);
        PrivilegeCategory privilege;
        switch (privilegeType) {
            case Beneficiary -> privilege = new BeneficiaryPrivilege(new CommonPrivilege());
            case Disabled -> privilege = new DisabledPrivilege(new CommonPrivilege());
            case Military -> privilege = new MilitaryPrivilege(new CommonPrivilege());
            default -> privilege = new CommonPrivilege();
        }

        return privilege;
    }
}
