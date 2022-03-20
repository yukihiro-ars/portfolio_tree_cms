package ars.yukihiro.portfolio.tree.cms.repository;

import ars.yukihiro.portfolio.tree.cms.entity.NodeRelationship;
import ars.yukihiro.portfolio.tree.cms.entity.NodeRelationshipPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface NodeRelationshipRepository extends JpaRepository<NodeRelationship, NodeRelationshipPK>, JpaSpecificationExecutor<NodeRelationship> {
}
