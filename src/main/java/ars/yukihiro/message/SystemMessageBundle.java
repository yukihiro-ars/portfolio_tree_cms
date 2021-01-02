package ars.yukihiro.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SystemMessageBundle {

    private static String basename = "SystemMessage";
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle(basename, Locale.JAPAN);
    }

    public static String getMessage(SystemMessageConstants msgKey) {
        return bundle.getString(msgKey.getKey());
    }

    public static String getMessage(SystemMessageConstants msgKey, Object... params) {
        return MessageFormat.format(bundle.getString(msgKey.getKey()), params);
    }
}
