package techin.lt.Tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techin.lt.Tour.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
