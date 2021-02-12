package ars.yukihiro.service;

/**
 * @param <T>
 * @author yukihiro adachi
 */
public interface INodeService<T> {

    T getNodeForm(Integer nodeId);

    void upsertNodeByForm(T form);
}
