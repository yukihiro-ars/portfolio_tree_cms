package ars.yukihiro.message;

public enum SystemMessageConstants {

    SYS_E_01("SYS_E_01"),
    SYS_I_01("SYS_I_01");

    private String key;

    private SystemMessageConstants(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }

    public static SystemMessageConstants convertByValue(String value) {
        for (SystemMessageConstants msg : SystemMessageConstants.values()) {
            if (msg.getKey() == value) {
                return msg;
            }
        }
        throw new IllegalArgumentException();
    }

}
