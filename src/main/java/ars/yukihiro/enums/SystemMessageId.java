package ars.yukihiro.enums;

/**
 * SystemMessage用ID定数クラス
 * @atuher yukihiro adachi
 */
public enum SystemMessageId {

    SYS_E_01("SYS_E_01"),
    SYS_I_01("SYS_I_01");

    private String key;

    private SystemMessageId(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }
}
