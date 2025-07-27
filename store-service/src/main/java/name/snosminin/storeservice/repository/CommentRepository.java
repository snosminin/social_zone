package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Modifying
    @Query(value = """
                 INSERT INTO comment (
                     user_id,
                     post_id,
                     text)
                 VALUES (
                     :#{#comment.userId},
                     :#{#comment.postId},
                     :#{#comment.text})
            """, nativeQuery = true)
    @Transactional
    void add(@Param("comment") Comment comment);
}
