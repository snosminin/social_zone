package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;


@Tag("testcontainers")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:14.7-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.withInitScript("init_scheme.sql");
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LikeRepository likeRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testEntities() {
        testGetUsers();
        testGetPosts();
        testGetLikes();
        testGetFollows();
        testGetComments();
    }

    void testGetUsers() {
        var user1 = new User();
        user1.setUsername("user1");
        user1.setFirstName("user1");
        user1.setMiddleName("user1");
        user1.setLastName("user1");
        user1.setMobile("user1");
        user1.setEmail("user1");
        user1.setPasswordHash("user1");
        user1.setRole(Role.ROLE_USER);

        var user2 = new User();
        user2.setUsername("user2");
        user2.setFirstName("user2");
        user2.setMiddleName("user2");
        user2.setLastName("user2");
        user2.setMobile("user2");
        user2.setEmail("user2");
        user2.setPasswordHash("user2");
        user2.setRole(Role.ROLE_USER);

        var users = List.of(user1, user2);
        userRepository.saveAll(users);
    }

    void testGetPosts() {
        var users = userRepository.findAll();

        var post1 = new Post();
        post1.setUser(users.get(0));
        post1.setUserId(users.get(0).getId());
        post1.setText("Post 1 made by user 1");

        var post2 = new Post();
        post2.setUser(users.get(1));
        post2.setUserId(users.get(1).getId());
        post2.setText("Post 2 made by user 2");

        var posts = List.of(post1, post2);
        postRepository.saveAll(posts);
    }

    void testGetLikes() {
        var users = userRepository.findAll();
        var posts = postRepository.findAll();

        var like1 = new Like();
        like1.setUser(users.get(0));
        like1.setPost(posts.get(1));

        var like2 = new Like();
        like2.setUser(users.get(1));
        like2.setPost(posts.get(0));

        var likes = List.of(like1, like2);
        likeRepository.saveAll(likes);
    }

    void testGetFollows() {
        var users = userRepository.findAll();

        var follow1 = new Follow();
        follow1.setFollower(users.get(0));
        follow1.setFollowee(users.get(1));

        var follow2 = new Follow();
        follow2.setFollower(users.get(1));
        follow2.setFollowee(users.get(0));

        var follows = List.of(follow1, follow2);
        followRepository.saveAll(follows);
    }

    void testGetComments() {
        var users = userRepository.findAll();
        var posts = postRepository.findAll();

        var comment1 = new Comment();
        comment1.setUser(users.get(0));
        comment1.setPost(posts.get(1));
        comment1.setText("Comment from user1 for post2");

        var comment2 = new Comment();
        comment2.setUser(users.get(1));
        comment2.setPost(posts.get(0));
        comment2.setText("Comment from user2 for post1");

        var comments = List.of(comment1, comment2);
        commentRepository.saveAll(comments);
    }
}