package ars.yukihiro.portfolio.tree.cms.repository;

import ars.yukihiro.portfolio.tree.cms.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Integer> { }
