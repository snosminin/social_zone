package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Modifying
    @Query(value = """
                 INSERT INTO like (
                     user_id,
                     post_id)
                 VALUES (
                     :#{#like.userId},
                     :#{#like.postId})
            """, nativeQuery = true)
    @Transactional
    void add(@Param("like") Like like);

    Long countAllByPostId(Long postId);
}
