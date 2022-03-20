package ars.yukihiro.portfolio.tree.cms.repository;

import ars.yukihiro.portfolio.tree.cms.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface ContentsRepository extends JpaRepository<Contents, Integer> {
}
