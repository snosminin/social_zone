package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {


    @Modifying
    @Query(value = """
                 INSERT INTO follow (
                     follower_id,
                     followee_id)
                 VALUES (
                     :#{#follow.followerId},
                     :#{#follow.followeeId})
            """, nativeQuery = true)
    @Transactional
    void add(@Param("follow") Follow follow);
}
