package models.privileges;

public enum PrivilegeType {
    Beneficiary,
    Military,
    Disabled,
    Interrupted;

    public static PrivilegeType fromInt(int value) {
        PrivilegeType[] values = PrivilegeType.values();
        if (value < 0 || value > values.length) {
            throw new IllegalArgumentException("Invalid value for PrivilegeType: " + value);
        }
        return values[value];
    }
}

