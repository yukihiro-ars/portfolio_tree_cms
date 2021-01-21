package ars.yukihiro.repository;

import ars.yukihiro.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yukihiro adachi
 */
@Repository
public interface ContentsRepository extends JpaRepository<Contents, Integer> {
}
