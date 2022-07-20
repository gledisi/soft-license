package city.ac.security.entity;

public enum RoleType {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    FINANCE("FINANCE"),
    PROCUREMENT("PROCUREMENT");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleType of(String value){
        for (RoleType roleType:values()){
            if(value.equalsIgnoreCase(roleType.getValue())){
                return roleType;
            }
        }
        throw new IllegalArgumentException("Value for role type is not correct!");
    }
}
