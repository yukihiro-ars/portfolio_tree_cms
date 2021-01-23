package ars.yukihiro.message;

import ars.yukihiro.enums.SystemMessageId;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * SystemMessage用バンドルクラス
 * @atuher yukihiro adachi
 */
public class SystemMessageBundle {

    private static String basename = "SystemMessage";
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(basename, Locale.JAPAN);
    }

    private SystemMessageBundle(){}

    public static String getMessage(SystemMessageId msgKey) {
        return bundle.getString(msgKey.getKey());
    }

    public static String getMessage(SystemMessageId msgKey, Object... params) {
        return MessageFormat.format(bundle.getString(msgKey.getKey()), params);
    }
}
