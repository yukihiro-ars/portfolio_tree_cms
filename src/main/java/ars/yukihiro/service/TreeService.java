package ars.yukihiro.service;

import ars.yukihiro.entity.NodeRelationship;
import ars.yukihiro.repository.NodeRelationshipRepository;
import ars.yukihiro.repository.helper.NodeRelationshipRepositoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yukihiro adachi
 */
@Service
public class TreeService extends AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(TreeService.class);

    @Autowired
    private NodeRelationshipRepository nodeRelationshipRepository;

    /**
     * @return TODO 何らかの戻り値
     */
    public List<Object> getTree() {
        List<NodeRelationship> list =
                nodeRelationshipRepository.findAll(
                        NodeRelationshipRepositoryHelper.isRootNode());
        list.stream().forEach(e -> {
            System.out.println(e.getSort());
        });
        return null;
    }
}
