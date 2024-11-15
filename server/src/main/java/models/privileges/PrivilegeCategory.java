package models.privileges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class PrivilegeCategory {
    protected int priority;
    protected PrivilegeType type = PrivilegeType.Beneficiary;

    public PrivilegeCategory(PrivilegeCategory privilege) {
        this.priority = privilege.priority;
        this.type = privilege.type;
    }

    public abstract int getPriority();
    public abstract String getName();
    @Override
    public String toString() {
        return "PrivilegeCategory [priority=" + priority + ", type=" + type.name() + "]";
    }
}

