package ars.yukihiro.portfolio.tree.cms.repository;

import ars.yukihiro.portfolio.tree.cms.entity.ContentsPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface ContentsPlaneRepository extends JpaRepository<ContentsPlane, Integer> {
}
