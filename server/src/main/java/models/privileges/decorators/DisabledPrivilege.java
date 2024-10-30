package models.privileges.decorators;

import models.privileges.PrivilegeCategory;
import models.privileges.PrivilegeType;

public class DisabledPrivilege extends PrivilegeCategoryDecorator {

    public DisabledPrivilege(PrivilegeCategory decoratedPrivilege) {
        super(decoratedPrivilege);
    }
    @Override
    public String getName() {
        return PrivilegeType.Disabled.name();
    }
    @Override
    public int getPriority() {
        return super.getPriority() * 4;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}