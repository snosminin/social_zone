package name.snosminin.storeservice.repository;

import jakarta.validation.constraints.NotNull;
import name.snosminin.storeservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query(value = """
                 INSERT INTO post (
                     user_id,
                     text)
                 VALUES (
                     :#{#post.userId},
                     :#{#post.text})
            """, nativeQuery = true)
    @Transactional
    void add(@Param("post") Post post);

    List<Post> getPostsByUserId(@NotNull Long userId);
}
