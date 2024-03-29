package ars.yukihiro.portfolio.tree.cms.enums;

/**
 * ApplicationMessage用ID定数クラス
 * @atuher yukihiro adachi
 */
public enum ApplicationMessageId {

    SYS_E_01("SYS_E_01"),
    SYS_I_01("SYS_I_01"),
    M10_E_01("M10_E_01");

    private String key;

    private ApplicationMessageId(String k) {
        key = k;
    }

    public String getKey() {
        return key;
    }
}
