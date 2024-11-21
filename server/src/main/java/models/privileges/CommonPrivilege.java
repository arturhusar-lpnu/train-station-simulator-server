package models.privileges;

public class CommonPrivilege extends PrivilegeCategory{

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "Common Privilege";
    }
}
