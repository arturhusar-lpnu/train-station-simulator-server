package models.privileges.decorators;

import models.privileges.PrivilegeCategory;
import models.privileges.PrivilegeType;

public class MilitaryPrivilege extends PrivilegeCategoryDecorator{
    public MilitaryPrivilege(PrivilegeCategory category) {
        super(category);
    }
    @Override
    public String getName() {
        return PrivilegeType.Military.name();
    }
    @Override
    public int getPriority() {
        return super.getPriority() * 2;
    }
}
