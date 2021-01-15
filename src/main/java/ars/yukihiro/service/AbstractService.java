package ars.yukihiro.service;

import java.sql.Timestamp;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractService {
    protected static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
