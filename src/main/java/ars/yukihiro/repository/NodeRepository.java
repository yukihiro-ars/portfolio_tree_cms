package ars.yukihiro.repository;

import ars.yukihiro.entity.Node;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NodeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Node findNode(String nodeId) {
        return entityManager.find(Node.class, nodeId);
    }
}
