package ars.yukihiro.service;

import ars.yukihiro.constants.ApplicationConstant;
import ars.yukihiro.entity.NodeRelationship;
import ars.yukihiro.repository.NodeRelationshipRepository;
import ars.yukihiro.repository.helper.NodeRelationshipRepositoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
     * @return TODO 何らかの戻り値
     */
    public List<Object> getTree() {
        List<NodeRelationship> list =
                nodeRelationshipRepository.findAll();
        // NodeRelationshipRepositoryHelper.isRootNode(0));
        List<Predicate<NodeRelationship>> predicateList = new ArrayList<>();
        predicateList.add(e -> e.getParentNodeId() == ApplicationConstant.ROOT_NODE_ID);
        // TODO NodeRelationshipからのTree構造作成DEMO
        Tree root = new Tree(ApplicationConstant.ROOT_NODE_ID);
        setChildTreeRecurse(list, root);
        printTree(root, " ");
        return null;
    }

    // TODO DEMOコード console確認
    private void printTree(Tree tree, String beforeSpacer) {
        String spacer = beforeSpacer + " ";
        System.out.println(spacer + tree.nodeId);
        if (tree.children.size() > 0) {
            tree.children.forEach(t -> {
                printTree(t, spacer);
            });
        }
    }

    // TODO DEMOコード
    private void setChildTreeRecurse(List<NodeRelationship> list, Tree parentTree) {
        // 親ノードID判定
        Predicate<NodeRelationship> isParentNode =
                e -> e.getParentNodeId() == parentTree.nodeId;
        // childNodeの設定
        parentTree.children = list.stream()
                .filter(isParentNode)
                .map(e -> new Tree(e.getChildNodeId()))
                .collect(Collectors.toList());
        // 孫Node設定の再帰呼び出し用のリスト
        List<NodeRelationship> nextList =
                list.stream().filter(Predicate.not(isParentNode)).collect(Collectors.toList());
        // 孫Node設定用の再帰呼び出し
        parentTree.children.stream().forEach(t -> {
           t.parent = parentTree;
           setChildTreeRecurse(nextList, t);
        });
    }

    // TODO DEMOコード
    private class Tree {
        public Tree parent = null;
        public Integer nodeId;
        public List<Tree> children = new ArrayList<>();
        Tree(Integer id) {
            nodeId = id;
        }
    }
}
