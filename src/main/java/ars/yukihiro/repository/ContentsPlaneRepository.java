package ars.yukihiro.repository;

import ars.yukihiro.entity.Contents;
import ars.yukihiro.entity.ContentsPlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface ContentsPlaneRepository extends JpaRepository<ContentsPlane, Integer> {
}
