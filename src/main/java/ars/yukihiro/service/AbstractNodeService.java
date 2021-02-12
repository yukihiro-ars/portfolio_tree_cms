package ars.yukihiro.service;

import ars.yukihiro.response.form.AbstractNodeForm;

import java.sql.Timestamp;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractNodeService<T extends AbstractNodeForm> extends AbstractService implements INodeService<T> { }
