package hello.repository;


import hello.persistance.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findOneByUsernameAndEnabledTrue(String username);
    User findOneByUsernameOrEmail(String username, String email);
    User getUserById(long id);
}