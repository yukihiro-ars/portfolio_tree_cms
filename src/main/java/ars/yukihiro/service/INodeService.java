package ars.yukihiro.service;

public interface INodeService<T> {

    T getNodeForm(Integer nodeId);

    void upsertNodeByForm(T form);
}
