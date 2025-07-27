package name.snosminin.storeservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @OneToMany(mappedBy = "user")
    public Collection<Post> posts;

    @OneToMany(mappedBy = "user")
    public Collection<Comment> comments;

    @OneToMany(mappedBy = "follower")
    public Collection<Follow> followsFollower;

    @OneToMany(mappedBy = "followee")
    public Collection<Follow> followsFollowee;

    @OneToMany(mappedBy = "user")
    public Collection<Like> likes;
}
