package models.privileges.decorators;

import models.privileges.PrivilegeCategory;
import models.privileges.PrivilegeType;

public class Interrupted extends PrivilegeCategoryDecorator{
    public Interrupted(PrivilegeCategory category) {
        super(category);
    }
    @Override
    public String getName() {
        return PrivilegeType.Interrupted.name();
    }
    @Override
    public int getPriority() {
        return super.getPriority() * 10;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
