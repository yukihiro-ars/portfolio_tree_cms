package ars.yukihiro.service;

import ars.yukihiro.constants.ApplicationConstant;
import ars.yukihiro.entity.NodeRelationship;
import ars.yukihiro.repository.NodeRelationshipRepository;
import ars.yukihiro.response.json.TreeJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
     * @return TreeJson
     */
    public TreeJson getTree() {
        List<NodeRelationship> list =
                nodeRelationshipRepository.findAll();
        // NodeRelationshipRepositoryHelper.isRootNode(0));
        TreeJson root = new TreeJson(ApplicationConstant.ROOT_NODE_ID);
        setChildTreeRecurse(list, root);
        return root;
    }

    private void setChildTreeRecurse(List<NodeRelationship> list, TreeJson parentTree) {

        // 親ノードID判定
        Predicate<NodeRelationship> isParentNode =
                e -> e.getParentNodeId() == parentTree.getNodeId();

        // childNodeの設定
        List<TreeJson> children = list.stream()
                .filter(isParentNode)
                .map(e -> new TreeJson(e.getChildNodeId()))
                .collect(Collectors.toList());
        parentTree.setChildren(children);
;
        // 親IDを除いたリスト
        List<NodeRelationship> nextList = list.stream()
                        .filter(Predicate.not(isParentNode))
                        .collect(Collectors.toList());

        // 孫Node設定の再帰呼び出し
        parentTree.getChildren().stream()
                .forEach(tree -> {
                    setChildTreeRecurse(nextList, tree);
                });
    }
}
