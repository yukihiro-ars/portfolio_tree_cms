package ars.yukihiro.service;

import ars.yukihiro.repository.NodeRepository;
import ars.yukihiro.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;
    /**
     * @return
     */
    public Node findNode(String nodeId) {
        return nodeRepository.findNode(nodeId);
    }
}
