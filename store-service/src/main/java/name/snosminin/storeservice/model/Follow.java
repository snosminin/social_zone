package name.snosminin.storeservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_id", insertable = false, updatable = false)
    private Long followerId;

    @Column(name = "followee_id", insertable = false, updatable = false)
    private Long followeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_id")
    private User followee;
}
