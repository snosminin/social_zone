package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.Like;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:social_zone_db"
})
class RepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        postRepository.deleteAll();
        likeRepository.deleteAll();
        followRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    void shouldGetPendingTodos() {
    }
}