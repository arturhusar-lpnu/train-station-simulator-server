package models.privileges.decorators;

import models.privileges.PrivilegeCategory;

public abstract class PrivilegeCategoryDecorator extends PrivilegeCategory {
    protected PrivilegeCategory decoratedPrivilege;

    public PrivilegeCategoryDecorator(PrivilegeCategory decoratedPrivilege) {
        super(decoratedPrivilege);
        this.decoratedPrivilege = decoratedPrivilege;
    }

    @Override
    public int getPriority() {
        return decoratedPrivilege.getPriority();
    }

    @Override
    public String getName() {
        return decoratedPrivilege.getType().name();
    }

    @Override
    public String toString() {
        return "PrivilegeCategory [priority=" + getPriority() + ", type=" + getName() + "]";
    }
}

