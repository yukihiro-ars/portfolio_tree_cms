package ars.yukihiro.service;

import ars.yukihiro.constants.ApplicationConstant;
import ars.yukihiro.entity.Contents;
import ars.yukihiro.entity.Node;
import ars.yukihiro.entity.NodeRelationship;
import ars.yukihiro.enums.NodeType;
import ars.yukihiro.repository.ContentsRepository;
import ars.yukihiro.repository.NodeRelationshipRepository;
import ars.yukihiro.repository.NodeRepository;
import ars.yukihiro.response.json.TreeJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author yukihiro adachi
 */
@Service
public class TreeService extends AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(TreeService.class);

    // TODO viewに切替後は、下記のrepositoryはviewRepositoryのみとなる想定
    @Autowired
    private NodeRelationshipRepository nodeRelationshipRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private ContentsRepository contentsRepository;
    /**
     * @return TreeJson
     */
    public TreeJson getTree() {
        // TODO viewを取得するようにしたい
        List<NodeRelationship> list =
                nodeRelationshipRepository.findAll();

        // node
        Set<Integer> nodeIds =
                list.stream()
                        .map(e -> e.getChildNodeId())
                        .distinct()
                        .collect(Collectors.toSet());
        List<Node> nodeList = nodeRepository.findAllById(nodeIds);
        // contents
        Set<Integer> contentsIds =
                nodeList.stream()
                        .filter(e -> Objects.isNull(e.getContentsId()) == false)
                        .map(e -> e.getContentsId())
                        .distinct()
                        .collect(Collectors.toSet());

        List<Contents> contentsList = contentsRepository.findAllById(contentsIds);

        // TODO ルートをDBで持つか、定数で定義するか、プロパティで定義するか.
        TreeJson root = new TreeJson(ApplicationConstant.ROOT_NODE_ID);
        root.setNodeName(ApplicationConstant.ROOT_NODE_NM);
        setChildTreeRecurse(root, list);
        // TODO viewができるまでの仮実装
        applyColumns(root, nodeList, contentsList);
        return root;
    }

    private void setChildTreeRecurse(TreeJson parentTree, List<NodeRelationship> list) {

        // 親ノードID判定
        Predicate<NodeRelationship> isParentNode =
                e -> e.getParentNodeId() == parentTree.getNodeId();

        // childNodeの設定
        List<TreeJson> children = list.stream()
                .filter(isParentNode)
                .map(e -> new TreeJson(e.getChildNodeId()))
                .collect(Collectors.toList());
        parentTree.setChildren(children);

        // 親IDを除いたリスト
        List<NodeRelationship> nextList = list.stream()
                        .filter(Predicate.not(isParentNode))
                        .collect(Collectors.toList());

        // 孫Node設定の再帰呼び出し
        parentTree.getChildren().stream()
                .forEach(t -> {
                    setChildTreeRecurse(t, nextList);
                });
    }

    // TODO viewができるまでの仮実装
    private void applyColumns(TreeJson tree, List<Node> nodeList, List<Contents> contentsList) {

        Optional<Node> optNode = nodeList.stream()
                .filter(e -> e.getNodeId() == tree.getNodeId())
                .findFirst();

        if (optNode.isPresent()) {
            Node node = optNode.get();
            tree.setNodeName(node.getNodeNmLgc());
            tree.setType(node.getNodeType());
            if (NodeType.LEAF.equals(NodeType.convertByValue(node.getNodeType()))) {
                Optional<Contents> optContents = contentsList.stream()
                        .filter(e -> e.getContentsId() == node.getContentsId())
                        .findFirst();
                if (optContents.isPresent()) {
                    tree.setType(node.getNodeType() + "_" + optContents.get().getContentsType());
                }
            }
        }

        tree.getChildren().stream()
                .forEach(t -> applyColumns(t, nodeList, contentsList));
    }
}
