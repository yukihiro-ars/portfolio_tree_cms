package ars.yukihiro.repository;

import ars.yukihiro.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Integer> { }
