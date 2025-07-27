package name.snosminin.storeservice.repository;

import name.snosminin.storeservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = """
                 INSERT INTO "user" (
                     first_name,
                     middle_name,
                     last_name,
                     username,
                     mobile,
                     email,
                     password_hash)
                 VALUES (
                     :#{#user.firstName},
                     :#{#user.middleName},
                     :#{#user.lastName},
                     :#{#user.username},
                     :#{#user.mobile},
                     :#{#user.email},
                     :#{#user.passwordHash})
            """, nativeQuery = true)
    @Transactional
    void add(@Param("user") User user);
}
