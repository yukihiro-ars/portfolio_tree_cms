package ars.yukihiro.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Repositoryの抽象クラス
 * @auther yukihiro adachi
 */
public abstract class AbstractRepository {
    protected static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
