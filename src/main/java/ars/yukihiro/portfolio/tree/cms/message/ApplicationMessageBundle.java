package ars.yukihiro.portfolio.tree.cms.message;

import ars.yukihiro.portfolio.tree.cms.enums.ApplicationMessageId;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ApplicationMessage用バンドルクラス
 * @atuher yukihiro adachi
 */
public class ApplicationMessageBundle {

    private static String basename = "ApplicationMessage";
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(basename, Locale.JAPAN);
    }

    private ApplicationMessageBundle(){}

    public static String getMessage(ApplicationMessageId msgKey) {
        return bundle.getString(msgKey.getKey());
    }

    public static String getMessage(ApplicationMessageId msgKey, Object... params) {
        return MessageFormat.format(bundle.getString(msgKey.getKey()), params);
    }
}
