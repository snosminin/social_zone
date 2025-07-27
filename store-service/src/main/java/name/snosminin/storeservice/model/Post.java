package name.snosminin.storeservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    public Collection<Comment> comments;

    @OneToMany(mappedBy = "post")
    public Collection<Like> likes;
}
