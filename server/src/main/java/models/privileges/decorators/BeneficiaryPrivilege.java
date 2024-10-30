package models.privileges.decorators;

import models.privileges.PrivilegeCategory;
import models.privileges.PrivilegeType;

public class BeneficiaryPrivilege extends PrivilegeCategoryDecorator {
    public BeneficiaryPrivilege(PrivilegeCategory decoratedPrivilege) {
        super(decoratedPrivilege);
    }
    @Override
    public String getName() {
        return PrivilegeType.Beneficiary.name();
    }
    @Override
    public int getPriority() {
        return super.getPriority() * 2;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
