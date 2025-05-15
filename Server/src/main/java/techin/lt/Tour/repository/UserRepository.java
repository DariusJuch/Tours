package techin.lt.Tour.repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.Tour.model.Role;
import techin.lt.Tour.model.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    List<User> findByUsernameContainingIgnoreCaseAndIdNot(String username, Long notId, Pageable pageable);

    List<User> findByIdIn(List<Long> ids);

}
